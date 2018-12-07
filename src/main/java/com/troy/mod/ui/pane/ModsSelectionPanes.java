package com.troy.mod.ui.pane;

import com.troy.mod.service.ModService;
import com.troy.model.Mod;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class ModsSelectionPanes {
	private static final ModService modService = new ModService();
	private static ListView<Mod> availableMods = getModsListView();
	private static ListView<Mod> activeMods = getModsListView();

	public static HBox getModsBox() {
		HBox modsBox = new HBox(10);
		List<Mod> mods = modService.getSavedMods();
		if (mods != null && !mods.isEmpty()) {
			availableMods.getItems().addAll(mods.stream().filter(mod -> !mod.isSelected()).collect(Collectors.toList()));
			activeMods.getItems().addAll(mods);
			activeMods.getItems().removeAll(availableMods.getItems());
		}

		Button buttonMoveToRight = new Button(" -> ");
		buttonMoveToRight.setOnAction(event -> {
			ObservableList<Mod> selectedMods = availableMods.getSelectionModel().getSelectedItems();
			activeMods.getItems().addAll(selectedMods);
			availableMods.getItems().removeAll(selectedMods);
			activeMods.getSelectionModel().clearSelection();
			availableMods.getSelectionModel().clearSelection();
		});

		Button buttonMoveToLeft = new Button(" <- ");
		buttonMoveToLeft.setOnAction(event -> {
			ObservableList<Mod> selectedMods = activeMods.getSelectionModel().getSelectedItems();
			availableMods.getItems().addAll(selectedMods);
			activeMods.getItems().removeAll(selectedMods);
			activeMods.getSelectionModel().clearSelection();
			availableMods.getSelectionModel().clearSelection();
		});

		VBox buttonBox = new VBox(10);
		buttonBox.getChildren().addAll(buttonMoveToRight, buttonMoveToLeft);
		buttonBox.setAlignment(Pos.CENTER);
		modsBox.setAlignment(Pos.CENTER_LEFT);
		modsBox.getChildren().addAll(availableMods, buttonBox, activeMods);
		return modsBox;
	}

	private static ListView<Mod> getModsListView() {
		ListView<Mod> modsListView = new ListView<>();
		modsListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		return modsListView;
	}

	public static VBox getActionButtons() {
		VBox actionButtonsBox = new VBox(10);
		Button startModsButton = new Button("Run");
		startModsButton.setOnAction(event -> {
			try {
				modService.openMods(activeMods.getItems());
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		actionButtonsBox.getChildren().addAll(startModsButton);
		return actionButtonsBox;
	}

	public static List<Mod> getAllMods() {
		List<Mod> allMods = activeMods.getItems().stream().peek(mod -> mod.setSelected(true)).collect(Collectors.toList());
		List<Mod> remaining = availableMods.getItems().stream().peek(mod -> mod.setSelected(false)).collect(Collectors.toList());
		allMods.addAll(remaining);
		return allMods;
	}
}
