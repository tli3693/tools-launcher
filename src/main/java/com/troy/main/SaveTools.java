package com.troy.main;

import java.util.LinkedList;
import java.util.List;

import com.troy.model.Tool;
import com.troy.tool.service.ToolService;

public class SaveTools {
	private static ToolService toolService = new ToolService();

	public static void main(String[] args) {

		List<Tool> tools = new LinkedList<>();
		tools.add(new Tool("poe-trade-macro", "D:\\Downloads\\path_of_exile_addons\\poe_trademacro\\", "https://github.com/PoE-TradeMacro/POE-TradeMacro", "Run_TradeMacro.ahk"));
		tools.add(new Tool("poe-trades-companion", "D:\\Downloads\\path_of_exile_addons\\POE-Trades-Companion\\", "https://github.com/lemasato/POE-Trades-Companion", "POE Trades Companion.ahk"));
		tools.add(new Tool("lab-compass", "D:\\Downloads\\path_of_exile_addons\\LabCompass\\", "https://www.poelab.com/lab-compass/", "LabCompass.exe"));
		toolService.saveTools(tools);
	}
}
