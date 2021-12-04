package package1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
interface GasStation{
	
	  String Name = "My Gas  Station";
      List<Petrol>  ptrl =new ArrayList<>();
	  List<Diesel> dsl =new ArrayList<>();
	   
	
	
	 public static void ShowPetrolData() {
		 
		 ptrl.stream().forEach(System.out::println);
	 }
	
	
}

public class GasStationMain{

	public static void main(String[] args) {

		Connection con = CreateConnection.getConnection();
		final String SQL = "TRUNCATE petrol";
		try (PreparedStatement stmt = con.prepareStatement(SQL)) {
		            stmt.execute();
		           
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
			
		
	
		
		Scanner sc = new Scanner(System.in);
		
	
//		int opr;
//		do {
//			System.out.println("------------------------------------");
//			System.out.println(" ||     Wellcome To myGasStation   ||   ");
//			System.out.println("------------------------------------");
//			
//			
//	     opr = sc.nextInt();
//	     
//	     System.out.println("1)For Performing operations on petrol data.");
//	     System.out.println("2)For Performing operations of diesel data.");
//	     1)For Entering the data in the database table using CSV.
//	     2)For Updating The Petrol data using Date.
//	     3)For Delete the Petrol Data.
//	     4)For Searching the data using Date.
//	     5)For Searching the data Greater or less than the Total Sale
//         System.out.println();
//         System.out.println();
//         System.out.println();
//		} while (opr != 0);
		
		
		
		
		
		
		try {
			BufferedReader reader = new BufferedReader(
					new FileReader("C:\\Users\\91797\\Desktop\\PROJECT\\PROJECT\\src\\package1\\petrol.csv"));

			String Pline;
			while ((Pline = reader.readLine()) != null) {
				Petrol.AddTodyData(new Petrol(Pline));
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

//		Petrol.DeleteData("2021-12-6");
//		Petrol pt1= new Petrol("2021-12-6",23,25,1000);
//        Petrol.UpdateData(pt1);
//      
//		List<Petrol> pt =Petrol.SearchByDate("2021-12-10");
//		pt.stream().forEach(System.out::println);
//        GasStation.ShowPetrolData();
        
		List<Petrol> ptS =  Petrol.SearchBysale(20000,'<');
		ptS.stream().forEach(System.out::println);
        sc.close();

	}
	
	
	  
	

}
