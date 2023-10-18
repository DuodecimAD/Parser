package code;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Export {

    private Controller controller;
    private Parser parser;

    public Export(Controller controller, Parser parser) {
        this.controller = controller;
        this.parser = parser;
    }


	public void toSQL() {
		
		//System.out.println("export : " + parser.tableArray);
		//System.out.println("export : " + controller.getFullArray());
		
		
		System.out.println("/////////////////////////////////////////");
		
		/*
			
			for (int t = 0; t < controller.getFullArray().size(); t++) {
				for (int i = 0; i < controller.getFullArray().get(t).size(); i++) {
					
					System.out.print("INSERT INTO ");
					System.out.print(parser.tableArray.get(t).get(0)+" (");
					
					
					for (int c = 1; c < parser.tableArray.get(t).size(); c++) {
						
						System.out.print(parser.tableArray.get(t).get(c));
						
						if(c == parser.tableArray.get(t).size()-1) {
							
						}else {
							System.out.print(", ");
						}

					}
					
					System.out.print(") ");
					System.out.print("VALUES (");
					
					for (int v = 0; v < controller.getFullArray().get(t).get(i).size(); v++) {
						
						System.out.print("'");
						System.out.print(controller.getFullArray().get(t).get(i).get(v));
						System.out.print("'");
						
						if(v == controller.getFullArray().get(t).get(i).size()-1) {
							
						}else {
							System.out.print(", ");
						}
						
					}
					System.out.println(");");
						
				}
			}
			*/
		
			// Define the file path where you want to save the SQL statements
			String filePath = "src/output/output.sql";
			
			// Check if the file exists
			File outputFile = new File(filePath);

			if (!outputFile.exists()) {
			    try {
			        outputFile.createNewFile();
			    } catch (IOException e) {
			        e.printStackTrace();
			        // Handle the exception appropriately
			    }
			}

			try {
			    BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

			    for (int t = 0; t < controller.getFullArray().size(); t++) {
			        for (int i = 0; i < controller.getFullArray().get(t).size(); i++) {
			            writer.write("INSERT INTO ");
			            writer.write(parser.tableArray.get(t).get(0) + " (");

			            for (int c = 1; c < parser.tableArray.get(t).size(); c++) {
			                writer.write(parser.tableArray.get(t).get(c));

			                if (c == parser.tableArray.get(t).size() - 1) {
			                } else {
			                    writer.write(", ");
			                }
			            }

			            writer.write(") ");
			            writer.write("VALUES (");

			            for (int v = 0; v < controller.getFullArray().get(t).get(i).size(); v++) {
			                writer.write("'");
			                writer.write(controller.getFullArray().get(t).get(i).get(v));
			                writer.write("'");

			                if (v == controller.getFullArray().get(t).get(i).size() - 1) {
			                } else {
			                    writer.write(", ");
			                }
			            }
			            writer.write(");");
			            writer.newLine();
			        }
			    }

			    writer.close();
			    System.out.println("SQL statements have been written to " + filePath);
			} catch (IOException e) {
			    e.printStackTrace();
			}
			

	}	

}
