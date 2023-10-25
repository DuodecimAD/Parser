package code;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main  extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception{

		setPrimaryStage(primaryStage);
	}


	public static void main(String[] args) {
		
		launch(args);
	}
	
	
	public void setPrimaryStage(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/parser.fxml"));
			Parent root = loader.load();
			System.out.println("fxml loaded");
			
			Controller controller = loader.getController();
			controller.setPrimaryStage(primaryStage);

		    primaryStage.setTitle("parser");
			primaryStage.setScene(new Scene(root));
		    primaryStage.show();
		    
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
