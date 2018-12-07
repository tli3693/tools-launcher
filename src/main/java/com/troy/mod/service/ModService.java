package com.troy.mod.service;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.troy.constants.ModConstants;
import org.apache.commons.io.FilenameUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.troy.model.Mod;

public class ModService {
	private static File modConfig = new File(ModConstants.modConfigLocation);
	private static List<Process> activeProcesses = new LinkedList<>();

	private Gson gson = new GsonBuilder().setPrettyPrinting().create();
	public Process openMod(Mod mod) throws IOException {
		Runtime runtime = Runtime.getRuntime();
		File modFile = new File(mod.getPath() + mod.getFileName());
		if (!modFile.exists()) {
			System.out.println("File does not exist: " + modFile.getPath());
			return null;
		}
		String fileExt = FilenameUtils.getExtension(modFile.getPath());
		if (ModConstants.ahkExt.equals(fileExt)) {
			return runtime.exec(new String[]{ModConstants.ahkPath, modFile.getPath()});
		} else if (ModConstants.exeExt.equals(fileExt)) {
			return runtime.exec(modFile.getPath(), null, modFile.getParentFile());
		} else {
			System.out.println("Extension not recognized: " + fileExt);
			return null;
		}
	}

	public void openMods(List<Mod> mods) throws IOException {
		for (Mod mod : mods) {
			Process process = openMod(mod);
			activeProcesses.add(process);
		}
	}
	public List<Mod> getSavedMods() {
		FileReader reader = null;
		List<Mod> mods = null;
		try {
			reader = new FileReader(modConfig);
			JsonReader jsonReader = new JsonReader(reader);
			mods = gson.fromJson(jsonReader, new TypeToken<List<Mod>>() {
			}.getType());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return mods;
	}

	public void saveMods(List<Mod> mods) {
		FileWriter fileWriter = null;
		String modJson = gson.toJson(mods);
		System.out.println(modJson);
		if (!modConfig.exists()) {
			try {
				boolean createFailure = modConfig.createNewFile();
				if (createFailure) {
					return;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try {
			fileWriter = new FileWriter(modConfig);
			fileWriter.write(modJson);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fileWriter != null) {
					fileWriter.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void closeMods() {
		if (!activeProcesses.isEmpty()) {
			for (Process p : activeProcesses) {
				try {
					p.destroy();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
