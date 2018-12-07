package com.troy.main;

import com.troy.mod.service.ModService;
import com.troy.mod.ui.pane.ModsSelectionPanes;
import com.troy.mod.ui.scene.ModsSelectionScene;
import com.troy.mod.ui.window.ConfirmationModal;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ModsManagerApp extends Application {
	private Stage window = null;
	private ModService modService = new ModService();
	private ModsSelectionScene modsSelectionScene = new ModsSelectionScene();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		window = primaryStage;
		Scene scene = modsSelectionScene.getScene();

		window.setOnCloseRequest(event -> {
			event.consume();
			confirmCloseApp();
		});

		window.setTitle("PoE Mod Manager");
		window.setScene(scene);
		window.show();
	}

	private void confirmCloseApp() {
		ConfirmationModal closeAppConfirmation = new ConfirmationModal("Close Application", "Are you sure you want to close this application?");
		boolean confirmed = closeAppConfirmation.getConfirmation();
		if (confirmed) {
			modService.closeMods();
			modService.saveMods(ModsSelectionPanes.getAllMods());
			window.close();
		}
	}
}
