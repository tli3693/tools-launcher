package com.troy.tool.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FilenameUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.troy.model.Tool;

public class ToolService {
	private static File configFile = new File("D:\\Downloads\\path_of_exile_addons\\test.json");
	private static final String ahkPath = "C:\\Program Files\\AutoHotkey\\AutoHotkey.exe";
	private static final String ahkExt = "ahk";
	private static final String exeExt = "exe";
	private Gson gson = new GsonBuilder().setPrettyPrinting().create();

	public Process openTool(Tool tool) throws IOException {
		Runtime runtime = Runtime.getRuntime();
		File toolFile = new File(tool.getPath() + tool.getFileName());
		if (!toolFile.exists()) {
			System.out.println("File does not exist: " + toolFile.getPath());
			return null;
		}
		String fileExt = FilenameUtils.getExtension(toolFile.getPath());
		if (ahkExt.equals(fileExt)) {
			return runtime.exec(new String[]{ahkPath, toolFile.getPath()});
		} else if (exeExt.equals(fileExt)) {
			return runtime.exec(toolFile.getPath(), null, toolFile.getParentFile());
		} else {
			System.out.println("Extension not recognized: " + fileExt);
			return null;
		}
	}

	public void saveTools(List<Tool> tools) {
		FileWriter fileWriter = null;
		String toolJson = gson.toJson(tools);
		System.out.println(toolJson);
		if (!configFile.exists()) {
			try {
				boolean createFailure = configFile.createNewFile();
				if (createFailure) {
					return;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try {
			fileWriter = new FileWriter(configFile);
			fileWriter.write(toolJson);
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
}
