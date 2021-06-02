package realestate.domain;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;



public class DataManager {

	public static ArrayList<Property> properties =new ArrayList<>();
	public static ArrayList<Owner> owners  =new ArrayList<>();
	
	public static boolean readDefaultOwnersCSV() {
		owners.clear();
        try
        {    
        	FileReader fr=new FileReader("owners.csv");   
        	BufferedReader br=new BufferedReader(fr);
        	String line=br.readLine(); //reading record count 
        	while((line=br.readLine())!=null)  
        	{  
        		String[] values = line.split(",");
        		if(values.length==5 || values.length==6) {
        			String name=values[0];
    				String address=values[1];
    				int uniqueID=Integer.parseInt(values[2]);
        			//Seller
        			if(values[3].equals("1")) {
        				String conveyancer=values[4];
        				Seller seller=new Seller(name,address,uniqueID,conveyancer);
        				owners.add(seller);
        			}//landlord
        			else if(values[3].equals("2")) {        				
        				String bankDetails=values[4];
        				double weeklyRent=Double.parseDouble(values[5]);
        				Landlord landlord=new Landlord(name,address,uniqueID,bankDetails,weeklyRent);
        				owners.add(landlord);
        			}
        		}
	        	
        	}  
        	fr.close();    //closes the stream and release the resources  
        	
        } 
        catch(Exception ex) 
        {   
            return false;
        } 
        return true;
	}
	
	
	public static Owner getOwner(int id) {
		for(Owner owner:owners) {
			if(owner.getOwnerID()==id) {
				return owner;
			}
		}
		return null;
	}
	
	public static boolean readDefaultPropertiesCSV() {
		properties.clear();
        try
        {    
        	FileReader fr=new FileReader("properties.csv");   
        	BufferedReader br=new BufferedReader(fr);
        	String line=br.readLine(); //reading record count 
        	while((line=br.readLine())!=null)  
        	{  
        		String[] values = line.split(",");
        		if(values.length==7) {
        			String address=values[0];
        			String town=values[1];
        			int propertyID=Integer.parseInt(values[2]);
        			Owner propertyOwner=getOwner(Integer.parseInt(values[3]));
        			if(propertyOwner!=null) {
	        			//sale
	        			if(values[4].equals("1")) {
	        				int askingPrice=Integer.parseInt(values[5]);
	        				double indoorArea=Double.parseDouble(values[6]);
	        				SaleProperty sp=new SaleProperty(address,town, propertyID, propertyOwner,askingPrice, indoorArea);
	        				properties.add(sp);
	        			}//leased
	        			else if(values[4].equals("2")) {
	        				String tenantName=values[5];
	        				String tenantPhone=values[6];
	        				RentalProperty rp=new RentalProperty(address, town, propertyID, propertyOwner, tenantName, tenantPhone);
	        				properties.add(rp);
	        			}
        			}
        		}
        	}  
        	fr.close();    //closes the stream and release the resources  
        	
        } 
        catch(Exception ex) 
        {   
            return false;
        } 
        return true;
	}

	
	 /*
	  * To store owners list to file
	  * */
    public static boolean saveOwners(){

        try
        {    
            //Saving of object in a file 
            FileOutputStream file = new FileOutputStream("ownersbin.txt"); 
            ObjectOutputStream out = new ObjectOutputStream(file); 
            //Storing to file
            for(Owner owner:owners){
                out.writeObject(owner);
            }

            out.close(); 
            file.close(); 
        } 

        catch(Exception ex) 
        { 
            System.out.println("IOException is caught:"+ex.toString()); 
            return false;
        }
        return true;
    }
    
    /*
	  * To store properties list to file
	  * */
   public static boolean saveProperties(){

       try
       {    
           //Saving of object in a file 
           FileOutputStream file = new FileOutputStream("propertybin.txt"); 
           ObjectOutputStream out = new ObjectOutputStream(file); 
           //Storing to file
           for(Property pro:properties){
               out.writeObject(pro);
           }
           out.close(); 
           file.close(); 
       } 

       catch(Exception ex) 
       { 
           System.out.println("IOException is caught:"+ex.toString()); 
           return false;
       }
       return true;
   }
   
   
   /**
    * To load owners data from file
    **/    
   public static boolean loadOwnersFromFile(){
   	owners.clear();
       try
       {    
       	Owner owner = null; 
           // Reading the object from a file 
           FileInputStream file = new FileInputStream("ownersbin.txt"); 
           ObjectInputStream in = new ObjectInputStream(file); 

           // Method for deserialization of object 
           while(true){
        	   owner = (Owner)in.readObject(); 
               if(owner==null){
                   //to exit loop when no more object
                   break;
               }
               owners.add(owner);
           }

           in.close(); 
           file.close(); 

       } 
       catch(Exception ex) 
       {   
           return true;
       } 
       return true;
   }
   
   /**
    * To load properties data from file
    **/    
   public static boolean loadPropertiesFromFile(){
   	properties.clear();
       try
       {    
       	Property prop = null; 
           // Reading the object from a file 
           FileInputStream file = new FileInputStream("propertybin.txt"); 
           ObjectInputStream in = new ObjectInputStream(file); 

           // Method for deserialization of object 
           while(true){
        	   prop = (Property)in.readObject(); 
               if(prop==null){
                   //to exit loop when no more object
                   break;
               }
               properties.add(prop);
           }

           in.close(); 
           file.close(); 

       } 
       catch(Exception ex) 
       {   
           return true;
       } 
       return true;
   }

   
   
    
}
