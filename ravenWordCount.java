package ravenReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * The Raven word count class with JavaFX
 * 
 * @author Federico
 *
 */
public class ravenWordCount extends Application implements EventHandler<ActionEvent> {
	Button button;
	Label label1 = new Label("The Raven word occurences");
	Stage window;
	Scene scene1, scene2;
	Label label2 = new Label();

	/**
	 * The main method
	 * 
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		//getConnection();
		Reader.createTable();
		launch(args);
	}
	

	public void start(Stage primaryStage) throws Exception {
		Reader reader = new Reader();
		reader.raven();
		String words = reader.raven();
		primaryStage.setTitle("Word Count for The Raven by Edgar Allan Poe ");
		button = new Button();
		button.setText("Analyze");
		button.setAlignment(Pos.CENTER);
		;
		label1.setStyle("-fx-background-color:yellow");
		label1.setPadding(new Insets(20));
		label1.setAlignment(Pos.CENTER);
		label2.setText(words);
		button.setOnAction(e -> primaryStage.setScene(scene2));

		VBox layout = new VBox();
		layout.setAlignment(Pos.CENTER);
		layout.setPadding(new Insets(10,125,10,125));
		ScrollPane layout2 = new ScrollPane();
		layout2.setPannable(true);
		layout2.setPrefSize(120, 120);
		layout2.setContent(label2);
		layout.getChildren().addAll(label1, button);

		scene1 = new Scene(layout, 450, 100);
		scene2 = new Scene(layout2, 300, 600);
		primaryStage.setScene(scene1);
		primaryStage.show();
	}

	@Override
	public void handle(ActionEvent arg0) {
	}
}
