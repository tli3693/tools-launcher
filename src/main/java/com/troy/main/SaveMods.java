package com.troy.main;

import java.util.LinkedList;
import java.util.List;

import com.troy.model.Mod;
import com.troy.mod.service.ModService;

public class SaveMods {
	private static ModService modService = new ModService();

	public static void main(String[] args) {

		List<Mod> mods = new LinkedList<>();
		mods.add(new Mod("poe-trade-macro", "D:\\Downloads\\path_of_exile_addons\\poe_trademacro\\", "https://github.com/PoE-TradeMacro/POE-TradeMacro", "Run_TradeMacro.ahk"));
		mods.add(new Mod("poe-trades-companion", "D:\\Downloads\\path_of_exile_addons\\POE-Trades-Companion\\", "https://github.com/lemasato/POE-Trades-Companion", "POE Trades Companion.ahk"));
		mods.add(new Mod("lab-compass", "D:\\Downloads\\path_of_exile_addons\\LabCompass\\", "https://www.poelab.com/lab-compass/", "LabCompass.exe"));
		modService.saveMods(mods);
	}
}
