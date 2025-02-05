package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NoteApplication extends Application {
	
	private TextArea textArea;
	private DatePicker datePicker;
	private TextField topicTextField;

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		/* Setting Root */
		VBox root = new VBox();
		root.setPadding(new Insets(10, 5, 10, 5)); // Padding of scene
		root.setSpacing(8); // Space between VBox (root)
		
		/* Top Section */
		VBox topSection = new VBox();
		topSection.setSpacing(3); // Space between VBox (topSection)
		
		HBox topicPane = new HBox();
		Label topicLabel = new Label("Topic : ");
		topicTextField = new TextField();
		topicTextField.setPrefWidth(200); // Width of TextField
		
		topicPane.getChildren().addAll(topicLabel, topicTextField);
		
		HBox datePane = new HBox();
		Label dateLabel = new Label("Date : ");
		datePicker = new DatePicker();
		datePicker.setPrefWidth(150); // Width of DatePicker
		
		datePane.getChildren().addAll(dateLabel, datePicker);
		
		topSection.getChildren().addAll(topicPane, datePane);
		
		/* Middle Section */
		textArea = new TextArea();
		
		/* Bottom Section */
		HBox bottomSection = new HBox();
		bottomSection.setSpacing(3); // Space between HBox (bottomSection)
		bottomSection.setAlignment(Pos.CENTER_RIGHT); // Align to right
		
		Button okButton = new Button("OK");
		okButton.setPrefWidth(60); // Width of Button OK
		
		/* Event Handling - Anonymous Inner Class */
		okButton.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent arg0) {
				showAlert();
			}
		});
		
		Button clearButton = new Button("Clear");
		clearButton.setPrefWidth(60); // Width of Button Clear
		
		/* Event Handling - Lambda Function */
		clearButton.setOnAction(e -> {
			topicTextField.clear();
			datePicker.getEditor().clear();
			textArea.clear();
		});
		
		bottomSection.getChildren().addAll(okButton, clearButton);
		
		/* Add all nodes to root */
		root.getChildren().addAll(topSection, textArea, bottomSection);
		
		Scene scene = new Scene(root, 270, 300);
		
		primaryStage.setTitle("MyNote");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void showAlert() {
		String result = 
				"Topic : " + topicTextField.getText() + "\n" 
				+ "Date : " + datePicker.getValue() + "\n"
				+ "Description : " + textArea.getText();
		Alert alert = new Alert(AlertType.INFORMATION, result);
		alert.show();
	}
	
	public static void main(String [] args) {
		launch(args);
	}

}
