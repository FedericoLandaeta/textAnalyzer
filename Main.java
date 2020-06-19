package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
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

public class Main extends Application implements EventHandler<ActionEvent> {
	Button button;
	Label label1 = new Label("The Raven word occurences");
	Stage window;
	Scene scene1, scene2;
	 Label label2 = new Label();
	static String words;
	static String words2;


	public static void main(String[] args) {
		raven();
		launch(args);
	}

	public void start(Stage primaryStage) throws Exception {
		
		primaryStage.setTitle("Word Count for The Raven by Edgar Allan Poe ");
		button = new Button();
		button.setText("Analyze");
		button.setAlignment(Pos.CENTER_RIGHT);;
        label1.setStyle("-fx-background-color:yellow");
        label1.setPadding(new Insets(10,10,10,10)); 
        label1.setAlignment(Pos.CENTER);
		label2.setText(words);
		button.setOnAction(e -> primaryStage.setScene(scene2));
		
		VBox layout = new VBox();
		layout.setPadding(new Insets(10));
		ScrollPane layout2 = new ScrollPane();
		layout2.setPannable(true);
		layout2.setPrefSize(120, 120);
		layout2.setContent(label2);
		layout.getChildren().addAll(label1,button);
		
		scene1 = new Scene(layout, 425 , 100);
		scene2 = new Scene(layout2, 300, 600);
		primaryStage.setScene(scene1);
		primaryStage.show();
	}
		
		public static void raven() {
				try {
					File file = new File("C:\\Users\\User\\Documents\\EclipseFiles\\TheRaven.txt");
			        @SuppressWarnings("resource")
					Scanner scanner = new Scanner(file);
			        Map<String,Integer> map = new HashMap<String, Integer>(); 
			        while (scanner.hasNext())
			        {
			            words = scanner.next(); 
			            String words2;
			            words2 = words.replaceAll("[^a-zA-Z0-9]" , "");
			            if(map.containsKey(words) == false) 
			                map.put(words,1);
			            else 
			            {
			                int count = (int)(map.get(words)); 
			                map.remove(words); 
			                map.put(words,count+1);
			            }
			        }
			        Set<Map.Entry<String, Integer>> set = map.entrySet();
			        List<Map.Entry<String, Integer>> sortedList = new ArrayList<Map.Entry<String, Integer>>(set);  
			        Collections.sort( sortedList, new Comparator<Map.Entry<String, Integer>>() 
			        {
			            public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b ) 
			            {
			                return (b.getValue()).compareTo( a.getValue() );            
			            }
			        } );
			        for(Map.Entry<String, Integer> i:sortedList){
			            words = words +("\n"+i.getKey()+" --> "+i.getValue()+"\n");      
			        }
				}
			catch(Exception FileNotFound){
				System.out.println("File not found");	
		}	
	}

		@Override
		public void handle(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}	
}