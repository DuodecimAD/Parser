package code;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Parser {
	
	List<LinkedList<String>> tableArray = new ArrayList<>();


	public Parser() {


		// location du fichier sql où on doit mettre
		String filePath = "src/input/input.sql";


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

	        /*
	        //print array complet
	        System.out.println(tableArray);

	        //print table par table
	        System.out.println(tableArray.get(0));

	        //print premier nom de table, la suite sont ses element (city et city_id etc)
	        System.out.println(tableArray.get(0).get(0));

	        */

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

}
