package code;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.application.Platform;
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
	
	List<String> streetNames = List.of(
		    "Broadway", "Magnolia Court", "Lakeview Drive", "Beech Street", "Forest Avenue", "Harrison Street", "Highland Avenue", "Durham Road", "East Avenue", "Magnolia Avenue",
		    "Essex Court", "Route 202", "Old York Road", "Homestead Drive", "Poplar Street", "Westminster Drive", "Cleveland Avenue", "Cedar Avenue", "Franklin Street", "Devonshire Drive",
		    "Fairview Avenue", "3rd Street", "Pearl Street", "Marshall Street", "Howard Street", "Smith Street", "Route 1", "Roosevelt Avenue", "Hickory Street", "Cherry Lane",
		    "Cambridge Court", "Route 9", "Dogwood Drive", "Summit Avenue", "Valley Drive", "Front Street North", "Laurel Lane", "Front Street South", "Route 44", "3rd Avenue",
		    "Elmwood Avenue", "Cemetery Road", "Pennsylvania Avenue", "Cedar Street", "North Street", "Bank Street", "Buttonwood Drive", "Edgewood Road", "Mulberry Lane", "Mill Street",
		    "Route 30", "Jefferson Avenue", "Windsor Drive", "Hillcrest Avenue", "B Street", "Union Street", "Monroe Street", "Meadow Lane", "Park Street", "Park Drive",
		    "Lexington Court", "Aspen Court", "Route 5", "Oak Lane", "King Street", "Riverside Drive", "Cooper Street", "Windsor Court", "4th Street North", "Spruce Street",
		    "Church Street", "White Street", "Sunset Avenue", "Route 70", "Route 11", "Oxford Court", "3rd Street North", "John Street", "Durham Court", "Forest Street",
		    "School Street", "Lafayette Avenue", "Manor Drive", "8th Street West", "Locust Street", "Laurel Drive", "Briarwood Court", "Euclid Avenue", "Elm Street", "Shady Lane",
		    "Front Street", "Glenwood Drive", "Circle Drive", "Park Avenue", "Route 2", "4th Avenue", "Fieldstone Drive", "Willow Avenue", "Myrtle Street", "Myrtle Avenue",
		    "Chestnut Street", "2nd Street", "Hartford Road", "Church Road", "Maple Street", "Heritage Drive", "8th Avenue", "5th Street South", "6th Street North", "Pleasant Street",
		    "Ann Street", "Sunset Drive", "3rd Street West", "Cobblestone Court", "8th Street South", "Magnolia Drive", "Bridge Street", "Hamilton Road", "Jefferson Court", "Water Street",
		    "Railroad Avenue", "Edgewood Drive", "Oak Street", "Church Street South", "Winding Way", "Redwood Drive", "Highland Drive", "Warren Street", "Route 7", "Route 32",
		    "Hickory Lane", "Creekside Drive", "Harrison Avenue", "Forest Drive", "Fairway Drive", "Prospect Avenue", "5th Avenue", "Wall Street", "Lake Avenue", "Canal Street",
		    "Deerfield Drive", "Sherwood Drive", "Adams Street", "Arlington Avenue", "Beechwood Drive", "James Street", "Virginia Avenue", "Augusta Drive", "Lawrence Street", "Heather Court",
		    "Country Club Drive", "Center Street", "Laurel Street", "Victoria Court", "New Street", "South Street", "Hawthorne Lane", "York Road", "West Street", "Oxford Road",
		    "Cambridge Road", "Lincoln Avenue", "Main Street East", "13th Street", "12th Street", "1st Street", "Walnut Avenue", "Grove Avenue", "Hillside Drive", "Holly Drive",
		    "Charles Street", "Madison Court", "North Avenue", "Park Place", "Valley View Drive", "Summit Street", "Jefferson Street", "Orchard Lane", "4th Street West", "7th Avenue",
		    "Cambridge Drive", "Surrey Lane", "Orchard Street", "Linden Street", "Chapel Street", "10th Street", "Lafayette Street", "4th Street", "West Avenue", "Route 10",
		    "Cardinal Drive", "Crescent Street", "Washington Avenue", "7th Street", "Tanglewood Drive", "8th Street", "Mulberry Court", "5th Street East", "Church Street North", "Cross Street",
		    "Main Street West", "Lantern Lane", "Eagle Street", "3rd Street East", "Hudson Street", "Olive Street", "Broad Street West", "Ridge Avenue", "Woodland Road", "Canterbury Drive",
		    "Sycamore Drive", "Chestnut Avenue", "Canterbury Court", "Maple Avenue", "Henry Street", "Route 64", "Valley View Road", "14th Street", "Heather Lane", "Liberty Street",
		    "Orchard Avenue", "Main Street South", "Bridle Court", "Hawthorne Avenue", "Clay Street", "1st Avenue", "2nd Street North", "Ridge Street", "9th Street West", "Madison Street",
		    "Parker Street", "Colonial Avenue", "Country Club Road", "Sycamore Lane", "Ashley Court", "5th Street West", "Fairview Road", "Route 100", "Lincoln Street", "Inverness Drive",
		    "Woodland Avenue", "Elizabeth Street", "Eagle Road", "Route 41", "Andover Court", "Fawn Court", "Walnut Street", "York Street", "Linda Lane", "Dogwood Lane"
		);
	
	List<String> jobs = List.of(
		    "Budget analyst", "Anthropologist", "Plumber", "Paramedic", "Zoologist", "Real Estate Agent", "Designer", "Economist", "Dentist", "Construction Manager",
		    "Paralegal", "Urban Planner", "Insurance Agent", "Educator", "Veterinarian", "Editor", "Artist", "Loan Officer", "Human Resources Assistant", "Diagnostic Medical Sonographer",
		    "Librarian", "Chemist", "Clinical Laboratory Technician", "Janitor", "Computer Programmer", "Physician", "Executive Assistant", "Architect", "Desktop publisher", "Physicist",
		    "Microbiologist", "Referee", "Judge", "Systems Analyst", "Accountant", "Police Officer", "Software Developer", "Occupational Therapist", "HR Specialist", "Drafter",
		    "Housekeeper", "Substance Abuse Counselor", "Repair Worker", "Family Therapist", "Social Worker", "School Counselor", "Carpenter", "Teacher Assistant",
		    "Fitness Trainer", "Radiologic Technologist", "IT Manager", "Dental Hygienist", "Epidemiologist", "Art Director", "Database administrator", "Bus Driver", "Lawyer",
		    "Event Planner", "Writer", "Computer Systems Analyst", "Web Developer", "Historian", "Statistician", "Psychologist", "Medical Assistant", "Translator",
		    "Public Relations Specialist", "Hairdresser", "Bookkeeping clerk", "Childcare worker", "Painter", "Surveyor", "Truck Driver", "Actuary", "Speech-Language Pathologist",
		    "Chef", "Secretary", "Mathematician", "Professional athlete", "Reporter", "School Psychologist", "Security Guard", "Medical Secretary", "Farmer", "Landscaper",
		    "Actor", "Preschool Teacher", "Civil Engineer", "Middle School Teacher", "Musician", "Customer Service Representative", "Patrol Officer", "Automotive mechanic", "Sports Coach",
		    "Cost Estimator", "Logistician", "Court Reporter", "Electrical Engineer", "Computer Support Specialist", "Mechanical Engineer"
		);

	List<String> animals = List.of(
		    "guinea pig", "bald eagle", "ram", "seal", "chimpanzee", "alpaca", "jackal", "chicken", "chameleon", "impala",
		    "kitten", "ground hog", "marmoset", "rhinoceros", "okapi", "cougar", "zebu", "horse", "grizzly bear", "crocodile",
		    "elephant", "wombat", "ape", "tiger", "lynx", "leopard", "pronghorn", "sheep", "mountain goat", "kangaroo", "snowy owl",
		    "baboon", "porcupine", "fawn", "chamois", "musk-ox", "peccary", "hartebeest", "waterbuck", "pony", "rat", "gazelle",
		    "hippopotamus", "giraffe", "salamander", "toad", "lovebird", "ibex", "highland cow", "monkey", "beaver", "marten", "parrot",
		    "dugong", "lamb", "crow", "pig", "cat", "bumble bee", "ewe", "rabbit", "mule", "ferret", "jaguar", "llama", "quagga",
		    "panda", "fox", "gorilla", "bat", "rooster", "aoudad", "burro", "dung beetle", "oryx", "muskrat", "wolverine", "antelope",
		    "blue crab", "squirrel", "hog", "capybara", "wolf", "elk", "koala", "otter", "wildcat", "donkey", "prairie dog", "chipmunk",
		    "octopus", "coati", "hyena", "frog", "mynah bird", "musk deer", "colt", "coyote", "duckbill platypus", "shrew", "raccoon",
		    "bighorn", "badger", "reindeer", "sloth", "deer", "thorny devil", "buffalo", "eagle owl", "canary", "mouse", "gila monster",
		    "ermine", "doe", "gnu", "mongoose", "ox", "starfish", "hamster", "puma", "goat", "lion", "meerkat", "vicuna", "finch",
		    "camel", "stallion", "newt", "addax", "bunny", "polar bear", "gopher", "basilisk", "bull", "anteater", "puppy", "aardvark",
		    "turtle", "lemur", "alligator", "eland", "iguana", "orangutan", "hedgehog", "opossum", "whale", "bear", "lizard", "gemsbok",
		    "springbok"
		);
	
	List<String> arrayNom = new ArrayList<String>();
	
	Parser parser = new Parser();  
	Export export;
	
	@FXML
	public void initialize() {
		
		System.out.println("Initialize method called");
		
		//index of the first table
		setCurrentTable(0);
		
		newTableArray();
		
		export = new Export(this, parser);
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
	        											"First Names", "Last Names", "City Names", 
	        											"Country Names", "Street Names", "Jobs",
	        											 "Animal Names", "Dates");
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

        buttonNext.setText("Clicked");
        System.out.println("button clicked");
        
        int lineNumbers;
        try {
            lineNumbers = Integer.parseInt(lineChoice.getText());
           //System.out.println("lineNumbers : " + lineNumbers);
        } catch (NumberFormatException e) {
            // Parsing failed, handle the error here
            System.err.println("you didn't put any number of lines");
            // You can provide a default value or show an error message to the user
            lineNumbers = 0; // Default value, you can change this as needed
            //Platform.exit();
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
		            case "Street Names" -> {
		            	fullArray.get(getCurrentTable()).get(i).add(getRandomElement(streetNames));
		            }
		            case "Animal Names" -> {
		            	fullArray.get(getCurrentTable()).get(i).add(getRandomElement(animals));
		            }
		            case "Jobs" -> {
		            	fullArray.get(getCurrentTable()).get(i).add(getRandomElement(jobs));
		            }
		            case "Dates" -> {
		            	//String dates = ;
		            	//fullArray.get(getCurrentTable()).get(i).add(dates);
		            }
		            default -> {
		            	fullArray.get(getCurrentTable()).get(i).add("iDidNotSelectAnythingImBlind");
		            }
	        	};
	        	
	            //fullArray.get(getCurrentTable()).get(i).add(selectedValue);
	        }
        }
        
        
        //System.out.println(fullArray); 
        
        if(getCurrentTable() == parser.tableArray.size()-1) {
			System.out.println("exporting...");
			
			export.toSQL();
			
			System.out.println("CLOSE");
			Platform.exit();
			continueRunning = false;
		}
	        
	       
	    if (continueRunning) {
	        tablePanel.getChildren().removeAll(champChoiceBoxes);
	        tablePanel.getChildren().removeAll(champLabels);
	        
	        setCurrentTable(getCurrentTable()+1);

	        newTableArray();

		} 
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
	
	public List<List<List<String>>> getFullArray() {
		return fullArray;
	}
	
}
