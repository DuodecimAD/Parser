package code;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
	
	List<String> firstNames = List.of(
			"Wade", "Dave", "Seth", "Ivan", "Riley", "Gilbert", "Jorge", "Dan", "Brian", "Roberto",
		    "Ramon", "Miles", "Liam", "Nathaniel", "Ethan", "Lewis", "Milton", "Claude", "Joshua", "Glen",
		    "Harvey", "Blake", "Antonio", "Connor", "Julian", "Aidan", "Harold", "Conner", "Peter", "Hunter",
		    "Eli", "Alberto", "Carlos", "Shane", "Aaron", "Marlin", "Paul", "Ricardo", "Hector", "Alexis",
		    "Adrian", "Kingston", "Douglas", "Gerald", "Joey", "Johnny", "Charlie", "Scott", "Martin", "Tristin",
		    "Troy", "Tommy", "Rick", "Victor", "Jessie", "Neil", "Ted", "Nick", "Wiley", "Morris", "Clark",
		    "Stuart", "Orlando", "Keith", "Marion", "Marshall", "Noel", "Everett", "Romeo", "Sebastian", "Stefan",
		    "Robin", "Clarence", "Sandy", "Ernest", "Samuel", "Benjamin", "Luka", "Fred", "Albert", "Greyson",
		    "Terry", "Cedric", "Joe", "Paul", "George", "Bruce", "Christopher", "Mark", "Ron", "Craig", "Philip",
		    "Jimmy", "Arthur", "Jaime", "Perry", "Harold", "Jerry", "Shawn", "Walter",
		    "Daisy", "Deborah", "Isabel", "Stella", "Debra", "Beverly", "Vera", "Angela", "Lucy", "Lauren",
		    "Janet", "Loretta", "Tracey", "Beatrice", "Sabrina", "Melody", "Chrysta", "Christina", "Vicki", "Molly",
		    "Alison", "Miranda", "Stephanie", "Leona", "Katrina", "Mila", "Teresa", "Gabriela", "Ashley", "Nicole",
		    "Valentina", "Rose", "Juliana", "Alice", "Kathie", "Gloria", "Luna", "Phoebe", "Angelique", "Graciela",
		    "Gemma", "Katelynn", "Danna", "Luisa", "Julie", "Olive", "Carolina", "Harmony", "Hanna", "Anabelle",
		    "Francesca", "Whitney", "Skyla", "Nathalie", "Sophie", "Hannah", "Silvia", "Sophia", "Della", "Myra",
		    "Blanca", "Bethany", "Robyn", "Traci", "Desiree", "Laverne", "Patricia", "Alberta", "Lynda", "Cara",
		    "Brandi", "Janessa", "Claudia", "Rosa", "Sandra", "Eunice", "Kayla", "Kathryn", "Rosie", "Monique",
		    "Maggie", "Hope", "Alexia", "Lucille", "Odessa", "Amanda", "Kimberly", "Blanche", "Tyra", "Helena",
		    "Kayleigh", "Lucia", "Janine", "Maribel", "Camille", "Alisa", "Vivian", "Lesley", "Rachelle", "Kianna"
        );
	
	List<String> lastNames = List.of(
			"Williams", "Harris", "Thomas", "Robinson", "Walker", "Scott", "Nelson", "Mitchell", "Morgan", "Cooper",
		    "Howard", "Davis", "Miller", "Martin", "Smith", "Anderson", "White", "Perry", "Clark", "Richards",
		    "Wheeler", "Warburton", "Stanley", "Holland", "Terry", "Shelton", "Miles", "Lucas", "Fletcher", "Parks",
		    "Norris", "Guzman", "Daniel", "Newton", "Potter", "Francis", "Erickson", "Norman", "Moody", "Lindsey", "Gross",
		    "Sherman", "Simon", "Jones", "Brown", "Garcia", "Rodriguez", "Lee", "Young", "Hall", "Allen", "Lopez",
		    "Green", "Gonzalez", "Baker", "Adams", "Perez", "Campbell", "Shaw", "Gordon", "Burns", "Warren", "Long",
		    "Mcdonald", "Gibson", "Ellis", "Fisher", "Reynolds", "Jordan", "Hamilton", "Ford", "Graham", "Griffin",
		    "Russell", "Foster", "Butler", "Simmons", "Flores", "Bennett", "Sanders", "Hughes", "Bryant", "Patterson",
		    "Matthews", "Jenkins", "Watkins", "Ward", "Murphy", "Bailey", "Bell", "Cox", "Martinez", "Evans", "Rivera",
		    "Peterson", "Gomez", "Murray", "Tucker", "Hicks", "Crawford", "Mason", "Rice", "Black", "Knight", "Arnold",
		    "Wagner", "Mosby", "Ramirez", "Coleman", "Powell", "Singh", "Patel", "Wood", "Wright", "Stephens", "Eriksen",
		    "Cook", "Roberts", "Holmes", "Kennedy", "Saunders", "Fisher", "Hunter", "Reid", "Stewart", "Carter", "Phillips",
		    "Spencer", "Howell", "Alvarez", "Little", "Jacobs", "Foreman", "Knowles", "Meadows", "Richmond", "Valentine",
		    "Dudley", "Woodward", "Weasley", "Livingston", "Sheppard", "Kimmel", "Noble", "Leach", "Gentry", "Lara",
		    "Pace", "Trujillo", "Grant"
        );
	
	List<String> cityNames = List.of(
		    "Los Angeles", "Minsk", "Yangon", "Berlin", "Lima", "Alexandria", "Khartoum", "Kazan", "Kuala Lumpur", "Rome",
		    "Bucharest", "Philadelphia", "Madrid", "Chennai", "Pune", "Kharkiv", "Birmingham", "Fukuoka", "Wuhan", "Guangzhou",
		    "Foshan", "Surat", "Brussels", "Budapest", "Delhi", "Bangalore", "Guadalajara", "Miami", "Washington, D.C.",
		    "Rostov-on-Don", "Nizhny Novgorod", "Cairo", "Hangzhou", "Riyadh", "Warsaw", "Kolkata", "Jinan", "Santiago",
		    "Nanjing", "Singapore", "Bogotá", "São Paulo", "Kinshasa", "Dallas", "Dar es Salaam", "Nagoya", "Dalian", "Qingdao",
		    "Chengdu", "Rio de Janeiro", "Seoul", "Karachi", "Toronto", "New York City", "Jakarta", "Suzhou", "Chongqing",
		    "Atlanta", "Munich", "Beijing", "Perm", "Tianjin", "Harbin", "Dongguan", "Sofia", "Vienna", "Johannesburg",
		    "Tokyo", "Shenyang", "Mumbai", "Shanghai", "Houston", "Istanbul", "Dhaka", "Kyiv", "Belgrade", "Baghdad",
		    "Buenos Aires", "London", "Samara", "Volgograd", "Ho Chi Minh City", "Voronezh", "Saint Petersburg",
		    "Belo Horizonte", "Chicago", "Lagos", "Ahmedabad", "Luanda", "Odessa", "Paris", "Prague", "Ufa", "Barcelona",
		    "Hong Kong", "Lahore", "Osaka", "Shenzhen", "Milan"
		);
	
	List<String> countryNames = List.of(
		    "Palestine", "Grenada", "Mauritius", "Northern Mariana Islands", "Anguilla", "Germany", "Faroe Islands", "Virgin Islands", "Montserrat", "Singapore",
		    "Afghanistan", "Argentina", "Benin", "Mongolia", "Mayotte", "Angola", "Andorra", "Burkina Faso", "Antigua and Barbuda", "Sierra Leone",
		    "Réunion", "Finland", "Saint Helena, Ascension and Tristan da Cunha", "British Indian Ocean Territory", "Falkland Islands", "Cocos Islands", "Eritrea", "Uzbekistan", "Cameroon", "South Sudan",
		    "Taiwan, Province of China", "Rwanda", "Burundi", "Korea", "India", "El Salvador", "Senegal", "Lao People's Democratic Republic", "Mexico", "Oman",
		    "Cyprus", "Bulgaria", "France", "Cayman Islands", "Marshall Islands", "Albania", "Libya", "Turkmenistan", "Suriname", "Ireland",
		    "Poland", "Greenland", "Nicaragua", "Bahrain", "Nauru", "Israel", "Ukraine", "Bangladesh", "Romania", "Timor-Leste",
		    "Malawi", "Kuwait", "Australia", "Guernsey", "Japan", "Greece", "Virgin Islands", "Denmark", "Mali", "Armenia",
		    "Zambia", "Paraguay", "Martinique", "San Marino", "Vietnam", "Dominican Republic", "Gabon", "Ivory Coast", "Yemen", "Seychelles",
		    "Equatorial Guinea", "Iceland", "Guatemala", "Sweden", "Chad", "Belgium", "Bolivia", "Lesotho", "Congo", "Iran",
		    "Switzerland", "Saint Barthélemy", "Nepal", "Cabo Verde", "Ethiopia", "Guadeloupe", "Jersey", "Bermuda", "Micronesia", "Hong Kong"
		);
	
	
	List<String> arrayNom = new ArrayList<String>();

    
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
	
	public static String getRandomElement(List<String> list) {
        Random random = new Random();
        int index = random.nextInt(list.size());
        return list.get(index);
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
			        champChoiceBoxes.get(i).getItems().addAll("Please pick", "Primary Key", "Foreign Key",
			        											"First Names", "Last Names", "City Names", "Street Names",
			        											"Country Names");
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
					primaryStage.hide();
					primaryStage.show();
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
		        //fullArray.get(getCurrentTable()).get(i).add(getTableName());
	        
		        /*for (int k = 0; k < getCurrentTableSize(); k++) {
			        fullArray.get(0).get(k).add(parser.tableArray.get(getCurrentTable()).get(k));
			    }*/
		        
		        for (int j = 1; j < champChoiceBoxes.size(); j++) {
		        	
		        	
		        	
		        	String selectedValue = champChoiceBoxes.get(j).getValue();
		        	
		        	switch (selectedValue) {
			            case "Primary Key" -> {
			            	fullArray.get(getCurrentTable()).get(i).add(Integer.toString(i+1)); 
			            }
			            case "Foreign Key" -> {
			            	
			            	fullArray.get(getCurrentTable()).get(i).add(selectedValue);
			            }
			            case "First Names" -> {
			            	fullArray.get(getCurrentTable()).get(i).add(getRandomElement(firstNames));
			            }
			            case "Last Names" -> {
			            	fullArray.get(getCurrentTable()).get(i).add(getRandomElement(lastNames));
			            }
			            case "City Names" -> {
			            	fullArray.get(getCurrentTable()).get(i).add(getRandomElement(cityNames));
			            }
			            case "Country Names" -> {
			            	fullArray.get(getCurrentTable()).get(i).add(getRandomElement(countryNames));
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
