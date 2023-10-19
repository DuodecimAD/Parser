package code;


import java.io.File;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;



public class Main  extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception{
	
		System.out.println("start");
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/parser.fxml"));
			Parent root = loader.load();
			System.out.println("fxml loaded");
			
			Controller controller = loader.getController();
			controller.setPrimaryStage(primaryStage);

		    primaryStage.setTitle("parser");
		    

		    //root.getStylesheets().add("/src/css/parser.css");
		
		    //primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.setScene(new Scene(root));
			
		    primaryStage.show();
		    
		} catch (Exception e) {
			System.out.println(e);
		}
	
	}


	public static void main(String[] args) {
		
		launch(args);

	}
}
