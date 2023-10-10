package code;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Controller {
	
	private Stage primaryStage;
	
	@FXML private VBox mainPanel;
	
	@FXML private AnchorPane titlePanel;
    @FXML private AnchorPane tablePanel;
    @FXML private AnchorPane linePanel;
    @FXML private AnchorPane submitPanel;
    
    
    @FXML private Label tableName;
    
    @FXML private Label champ1;
    @FXML private Label champ2;
    @FXML private Label champ3;
    @FXML private Label champ4;
    @FXML private ChoiceBox<String> champ1choice;
    @FXML private ChoiceBox<String> champ2choice;
    @FXML private ChoiceBox<String> champ3choice;
    @FXML private ChoiceBox<String> champ4choice;
    
    @FXML private TextField lineChoice;
    @FXML private Button buttonNext;
    
    private int currentTable;
    
    Parser parser = new Parser();

    
	@FXML
	public void initialize() {
		
		setCurrentTable(0);
		
		System.out.println("Initialize method called");
		
		tableName.setText(parser.tableArray.get(getCurrentTable()).get(0));
		
		int tableSize = parser.tableArray.get(getCurrentTable()).size();

		List<ChoiceBox<String>> champChoiceBoxes = new ArrayList<ChoiceBox<String>>();
		List<Label> champLabels = new ArrayList<Label>();
		
		    
		for (int i = 0; i < tableSize; i++) {
	    	 // Create and add a ChoiceBox to the list
	    	champChoiceBoxes.add(new ChoiceBox<String>());
	    	champLabels.add(new Label());
	        

	        champChoiceBoxes.get(i).setId("champ" + (i + 1) + "choice");
	        champChoiceBoxes.get(i).getItems().addAll("Please pick", "Primary Key", "Foreign Key", "First Name", "Last Name", "City", "Street");
	        champChoiceBoxes.get(i).setLayoutX(210.0);
	        champChoiceBoxes.get(i).setLayoutY(13.0 + i * 40.0); 
	        champChoiceBoxes.get(i).setPrefWidth(150.0);
	        
	     // Set the selected item
			champChoiceBoxes.get(i).setValue("Please pick"); // This will preselect "Option 2"

	        
	        champLabels.get(i).setId("champ" + (i + 1));

	        champLabels.get(i).setLayoutX(40.0);
	        champLabels.get(i).setLayoutY(15.0 + i * 40.0); 
	        champLabels.get(i).setPrefHeight(20.0);
	        champLabels.get(i).setPrefWidth(150.0);
	        champLabels.get(i).setStyle("-fx-background-color: f2f2f2;");
	        champLabels.get(i).setText(parser.tableArray.get(getCurrentTable()).get(i)); 

	    }
		
		
		
		for (int i = 1; i < tableSize; i++) {
			tablePanel.getChildren().addAll(champChoiceBoxes.get(i), champLabels.get(i));
		}
	 // Set the prefHeight of the AnchorPane to adjust its height
	  //  tablePanel.setPrefHeight(totalHeight);
		Platform.runLater(() -> {

	    double fullHeight = titlePanel.getHeight() + tablePanel.getHeight() + linePanel.getHeight() + submitPanel.getHeight() + 23 + 15;
	    primaryStage.setHeight(fullHeight);
	    //System.out.println(fullHeight);
		});
		
	}
	
	public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }


	public int getCurrentTable() {
		return currentTable;
	}

	public void setCurrentTable(int currentTable) {
		this.currentTable = currentTable;
	}

}
