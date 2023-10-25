package code;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class Parser {
	
	private Stage primaryStage;
	
	List<LinkedList<String>> tableArray = new ArrayList<>();
	String filePath = null;

	public Parser() {
			
		filePath = selectInput(filePath);
		
		parseSQLFile();

	}
	
	public String selectInput(String filePath) {
		
		FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new ExtensionFilter("select your .sql", "*.sql"));
        
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")+"/src/input/"));

        File selectedFile = fileChooser.showOpenDialog(primaryStage);

        
        if (selectedFile != null) {
            filePath = selectedFile.getAbsolutePath();

            System.out.println("Selected File: " + filePath);
        }
		
		return filePath;
	}
	
	 public void parseSQLFile() {
		 try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

	    	// array où  seront stockés les tables et leurs elements issus du sql
	        LinkedList<String> currentTableInfo = null;
	        String line;
	        boolean isInsideTableDefinition = false;

	        // lis le fichier sql
	        while ((line = reader.readLine()) != null) {
	        	// eleve les espaces avant/après
	            line = line.trim();

	            if (isInsideTableDefinition) {
	                if (line.isEmpty() || line.startsWith(");")) {
	                	// si la ligne = ); fin de la table
	                    tableArray.add(currentTableInfo);
	                    currentTableInfo = null;
	                    isInsideTableDefinition = false;
	                } else if (!line.startsWith("CONSTRAINT")) {
	                	// ajoute les noms des champs sauf si ligne commence par constraint
	                    String[] parts = line.split("\\s+");
	                    currentTableInfo.add(parts[0]);
	                }
	            } else if (line.startsWith("CREATE TABLE")) {
	            	// si ligne commence par CREATE TABLE, le mot qui suit est ajouté dans un array en première position dans l'array général
	                String tableName = line.substring(13, line.indexOf('(')).trim();
	                currentTableInfo = new LinkedList<>();
	                currentTableInfo.add(tableName);
	                isInsideTableDefinition = true;
	            }
	        }
	        reader.close();

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	 }

}
