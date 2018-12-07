package com.troy.mod.ui.window;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmationModal {
	private VBox layout = new VBox(10);
	private Scene scene = new Scene(layout);
	private Stage modal = new Stage();

	private boolean answer = false;

	// Buttons
	private Button buttonY = new Button("Yes");
	private Button buttonN = new Button("No");

	// Labels
	private Label questionLabel = new Label();

	public ConfirmationModal(String title, String question) {
		layout.setPadding(new Insets(10, 20, 10, 20));
		layout.setAlignment(Pos.CENTER_LEFT);
		modal.setTitle(title);
		modal.setMinWidth(250);
		modal.setMinHeight(125);
		modal.setMaxWidth(500);
		modal.setMaxHeight(250);
		modal.setScene(scene);
		modal.initModality(Modality.APPLICATION_MODAL);

		questionLabel.setText(question);
		initializeButtons();

		HBox buttonBox = new HBox(10);
		buttonBox.setAlignment(Pos.BOTTOM_RIGHT);
		buttonBox.getChildren().addAll(buttonY, buttonN);
		layout.getChildren().addAll(questionLabel, buttonBox);
	}

	public boolean getConfirmation() {
		modal.showAndWait();
		return answer;
	}

	private void initializeButtons() {
		buttonY.setOnAction(event -> {
			answer = true;
			modal.close();
		});
		buttonN.setOnAction(event -> {
			answer = false;
			modal.close();
		});
	}
}
