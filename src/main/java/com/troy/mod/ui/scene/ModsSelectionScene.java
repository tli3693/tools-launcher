package com.troy.mod.ui.scene;

import com.troy.mod.ui.pane.ModsSelectionPanes;
import com.troy.model.Mod;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

public class ModsSelectionScene {
	private Scene scene;
	private HBox modsBox = ModsSelectionPanes.getModsBox();
	private VBox actionButtons = ModsSelectionPanes.getActionButtons();
	public ModsSelectionScene() {
		BorderPane layout = new BorderPane();
		layout.setPadding(new Insets(10, 10, 10, 10));

		layout.setTop(new Label("Top Pane"));
		layout.setBottom(new Label("Bottom Pane"));
		layout.setLeft(new Label("Left Pane"));
		layout.setRight(actionButtons);
		layout.setCenter(modsBox);
		scene = new Scene(layout, 1000, 600);
	}

	public Scene getScene() {
		return scene;
	}
}
