package realestate.domain;

import java.io.File;

public class Main {
	public static void main(String args[]) {
		
		 // Get the file
        File f = new File("ownersbin.txt");
  
        // Check if the specified file
        // Exists or not
        if (f.exists())
        {
        	DataManager.loadOwnersFromFile();
        	DataManager.loadPropertiesFromFile();
        }
        else
        {
        	DataManager.readDefaultOwnersCSV();
    		DataManager.readDefaultPropertiesCSV();
    		DataManager.saveOwners();
    		DataManager.saveProperties();
    		
        }
		new Menu();
	}
}
