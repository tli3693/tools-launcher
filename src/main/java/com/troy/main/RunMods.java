package com.troy.main;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.troy.model.Mod;
import com.troy.mod.service.ModService;

public class RunMods {
	private static File file = new File("D:\\Downloads\\path_of_exile_addons\\test.json");
	private static ModService modService = new ModService();
	private static List<Process> activeProcesses = new LinkedList<>();

	public static void main(String[] args) {
		Gson gson = new Gson();
		FileReader reader = null;

		try {
			reader = new FileReader(file);
			JsonReader jsonReader = new JsonReader(reader);

			List<Mod> mods = gson.fromJson(jsonReader, new TypeToken<List<Mod>>() {
			}.getType());
			for (Mod mod : mods) {
				Process process = modService.openMod(mod);
				activeProcesses.add(process);
			}
			while (true) {
				int x = System.in.read();
				if (x == 10) {
					break;
				}
			}
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
}
