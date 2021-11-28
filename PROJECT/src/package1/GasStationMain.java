package package1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

interface GasStation{
	
	String Name = "My Gas  Station";
	
	
	
	void SearchByDate();
	void UpdateData();
	void DeleteData();
	void profit();
	
	
	
	
}

public class GasStationMain {

	public static void main(String[] args) {
		 try {
	            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\91797\\Desktop\\PROJECT\\PROJECT\\src\\package1\\petrol.csv"));
	            
	            String Pline;
				while ((Pline = reader.readLine()) != null) {
	                Petrol.AddTodyData(new Petrol(Pline));
	            }	
	            reader.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	}

}
