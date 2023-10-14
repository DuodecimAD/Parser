package code;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
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
import javafx.util.Duration;

public class Controller {
	
	private Stage primaryStage;
	
	@FXML private VBox mainPanel;
	
	@FXML private AnchorPane titlePanel;
	@FXML private Label title;
    
    @FXML private AnchorPane tablePanel;
    @FXML private Label tableName;
    
    @FXML private AnchorPane linePanel;
    @FXML private TextField lineChoice;
    
    @FXML private AnchorPane submitPanel;
    @FXML private Button buttonNext;
    
    private int currentTable;
    
    private List<List<List<String>>> fullArray = new ArrayList<>();
    

	List<Label> champLabels = new ArrayList<Label>();
    private List<ChoiceBox<String>> champChoiceBoxes= new ArrayList<>();
    

	Parser parser = new Parser();
	
	private volatile boolean continueRunning = true;

    
	@FXML
	public void initialize() {
		
		mainPanel.prefHeightProperty().bind(
			    Bindings.createDoubleBinding(() -> {
			        double fullHeight = titlePanel.getHeight() + tablePanel.getHeight() + linePanel.getHeight() + submitPanel.getHeight() + 23 + 15;
			        return fullHeight;
			    }, titlePanel.heightProperty(), tablePanel.heightProperty(), linePanel.heightProperty(), submitPanel.heightProperty())
			);
		
		System.out.println("Initialize method called");
		
		//index of the first table
		setCurrentTable(0);
		
		newTableArray();
		
		
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
	
	public void setTitle() {
		//set title as Parser + which table it's showing out of all tables in the array
		title.setText("Parser " + (getCurrentTable()+1) + "/" + parser.tableArray.size());
	}
	
	public void setTableName() {
		// set title of the actual table
		tableName.setText(parser.tableArray.get(getCurrentTable()).get(0));
	}
	public String getTableName() {
		// set title of the actual table
		return parser.tableArray.get(getCurrentTable()).get(0);
	}
	
	public int getCurrentTableSize() {
		return parser.tableArray.get(getCurrentTable()).size();
	}
	
	public void newTableArray() {

				
				setTitle();
				
				setTableName();
				
				champLabels.clear();
				champChoiceBoxes.clear();

				//populate the lists of labels and boxes
				for (int i = 0; i < getCurrentTableSize(); i++) {
					
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
				
				tablePanel.getChildren().removeAll(champChoiceBoxes);
		        tablePanel.getChildren().removeAll(champLabels);
				for (int i = 1; i < getCurrentTableSize(); i++) {
					tablePanel.getChildren().addAll(champChoiceBoxes.get(i), champLabels.get(i));
				}
				
				//Make the height of the program be the same of the total height of all children
				Platform.runLater(() -> {

				    double fullHeight = titlePanel.getHeight() + tablePanel.getHeight() + linePanel.getHeight() + submitPanel.getHeight() + 23 + 15;
				    primaryStage.setHeight(fullHeight);
				});
	}
	
	@FXML
    public void buttonNextClick() {
		
		
		if(getCurrentTable() == parser.tableArray.size()-1) {
			System.out.println("CLOSE");
			Platform.exit();
			continueRunning = false;
		}
		
		
	        buttonNext.setText("Clicked");
	        System.out.println("button clicked");
	        
	        int lineNumbers;
	        try {
	            lineNumbers = Integer.parseInt(lineChoice.getText());
	            System.out.println("lineNumbers : " + lineNumbers);
	        } catch (NumberFormatException e) {
	            // Parsing failed, handle the error here
	            System.err.println(e.getMessage());
	            // You can provide a default value or show an error message to the user
	            lineNumbers = 0; // Default value, you can change this as needed
	            Platform.exit();
	        }
	        
	        
	        fullArray.add(new ArrayList<>());
	        
	        for (int i = 0; i < lineNumbers; i++) {
	
		        fullArray.get(getCurrentTable()).add(new ArrayList<>());
		        fullArray.get(getCurrentTable()).get(i).add(getTableName());
	        
		        for (int j = 1; j < champChoiceBoxes.size(); j++) {
		        	
		        	
		        	
		        	String selectedValue = champChoiceBoxes.get(j).getValue();
		        	
		        	switch (selectedValue) {
			            case "Primary Key" -> {
			            	fullArray.get(getCurrentTable()).get(i).add(Integer.toString(i+1)); 
			            }
			            case "Foreign Key" -> {
			            	fullArray.get(getCurrentTable()).get(i).add(selectedValue);
			            }
			            case "First Name" -> {
			            	fullArray.get(getCurrentTable()).get(i).add(selectedValue);
			            }
			            case "Last Name" -> {
			            	fullArray.get(getCurrentTable()).get(i).add(selectedValue);
			            }
			            case "City" -> {
			            	fullArray.get(getCurrentTable()).get(i).add(selectedValue);
			            }
			            default -> {
			            	fullArray.get(getCurrentTable()).get(i).add("didNotSelectAnythingImBlind");
			            }
		        	};
		        	
		            //fullArray.get(getCurrentTable()).get(i).add(selectedValue);
		        }
		     
	        }
	        
	        System.out.println(fullArray); 
	        
	       
	    if (continueRunning) {
	        tablePanel.getChildren().removeAll(champChoiceBoxes);
	        tablePanel.getChildren().removeAll(champLabels);
	        
	        setCurrentTable(getCurrentTable()+1);

	        newTableArray();

		
		}
        
    }


}
