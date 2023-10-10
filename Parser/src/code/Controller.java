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
	@FXML private Label title;
    
    @FXML private AnchorPane tablePanel;
    @FXML private Label tableName;
    @FXML private Label champ1;
    @FXML private Label champ2;
    @FXML private Label champ3;
    @FXML private Label champ4;
    @FXML private ChoiceBox<String> champ1choice;
    @FXML private ChoiceBox<String> champ2choice;
    @FXML private ChoiceBox<String> champ3choice;
    @FXML private ChoiceBox<String> champ4choice;
    
    @FXML private AnchorPane linePanel;
    @FXML private TextField lineChoice;
    
    @FXML private AnchorPane submitPanel;
    @FXML private Button buttonNext;
    
    private int currentTable;

	Parser parser = new Parser();

    
	@FXML
	public void initialize() {
		
		System.out.println("Initialize method called");
		
		//index of the first table
		setCurrentTable(0);

		//size of the table
		int tableSize = parser.tableArray.get(getCurrentTable()).size();
		
		//set title as Parser + which table it's showing out of all tables in the array
		title.setText("Parser " + (getCurrentTable()+1) + "/" + parser.tableArray.size());
		
		// set title of the actual table
		tableName.setText(parser.tableArray.get(getCurrentTable()).get(0));
		
		//create list of labels and boxes
		List<Label> champLabels = new ArrayList<Label>();
		List<ChoiceBox<String>> champChoiceBoxes = new ArrayList<ChoiceBox<String>>();
		
		
		//populate the lists of labels and boxes
		for (int i = 0; i < tableSize; i++) {
			
			champLabels.add(new Label());
			
	        champLabels.get(i).setId("champ" + (i + 1));
	        champLabels.get(i).setText(parser.tableArray.get(getCurrentTable()).get(i)); 
	        champLabels.get(i).setLayoutX(40.0);
	        champLabels.get(i).setLayoutY(15.0 + i * 40.0); 
	        champLabels.get(i).setPrefHeight(20.0);
	        champLabels.get(i).setPrefWidth(150.0);
	        champLabels.get(i).setStyle("-fx-background-color: f2f2f2;");
			
	        
	    	champChoiceBoxes.add(new ChoiceBox<String>());

	        champChoiceBoxes.get(i).setId("champ" + (i + 1) + "choice");
	        champChoiceBoxes.get(i).getItems().addAll("Please pick", "Primary Key", "Foreign Key", "First Name", "Last Name", "City", "Street");
	        champChoiceBoxes.get(i).setValue("Please pick");
	        champChoiceBoxes.get(i).setLayoutX(210.0);
	        champChoiceBoxes.get(i).setLayoutY(13.0 + i * 40.0); 
	        champChoiceBoxes.get(i).setPrefWidth(150.0);

	    }
		
		
		for (int i = 1; i < tableSize; i++) {
			tablePanel.getChildren().addAll(champChoiceBoxes.get(i), champLabels.get(i));
		}
		
		//Make the height of the program be the same of the total height of all children
		Platform.runLater(() -> {
		    double fullHeight = titlePanel.getHeight() + tablePanel.getHeight() + linePanel.getHeight() + submitPanel.getHeight() + 23 + 15;
		    primaryStage.setHeight(fullHeight);
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
	
	@FXML
    public void buttonNextClick() {
        buttonNext.setText("Clicked");
        
        
    }


}
