package package1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

interface GasStation{
	
	String Name = "My Gas  Station";
	
	
	void getAlldata();
	void searchByprofit();
	void SearchByDate(String date);
	
	
	
	
	
	
}

public class GasStationMain {

	public static void main(String[] args) {

		Connection con = CreateConnection.getConnection();
		final String SQL = "TRUNCATE petrol";
		try (PreparedStatement stmt = con.prepareStatement(SQL)) {
		            stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

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
		Petrol pt1= new Petrol("2021-12-6",23,25,1000);
        Petrol.UpdateData(pt1);
	}
	
	
	
	

}
