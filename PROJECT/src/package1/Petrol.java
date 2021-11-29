package package1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

final class Petrol implements GasStation {

	private String Date;
	private double P_Price;
	private double S_Price;
	private double Sale;
	
	
	

	
	public Petrol(String date, double p_Price, double s_Price, double sale) {
		
		Date = date;
		P_Price = p_Price;
		S_Price = s_Price;
		Sale = sale;
	}
    
	 public Petrol(String PLine) {
	        String values[] = PLine.split(",");
	        this.Date = values[0];
	        this.P_Price = Double.valueOf(values[1]);
	        this.S_Price = Double.valueOf(values[2]);
	        this.Sale = Double.valueOf(values[3]);
	        
	 }
	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

	public double getP_Price() {
		return P_Price;
	}

	public void setP_Price(double p_Price) {
		P_Price = p_Price;
	}

	public double getS_Price() {
		return S_Price;
	}

	public void setS_Price(double s_Price) {
		S_Price = s_Price;
	}

	public double getSale() {
		return Sale;
	}

	public void setSale(double sale) {
		Sale = sale;
	}

	
	public static void AddTodyData(Petrol P) {
		  Connection con = CreateConnection.getConnection();
		  final String SQL = "insert into petrol values(?,?,?,?)";
	        try(PreparedStatement stmt = con.prepareStatement(SQL)){
	            stmt.setString(1,P.getDate());
	            stmt.setDouble(2,P.getP_Price());
	            stmt.setDouble(3,P.getS_Price());
	            stmt.setDouble(4,P.getSale());
	            int rowsAffected = stmt.executeUpdate();
	            System.out.println(rowsAffected+" row has been added to petrol sales data.");
	        }catch(SQLException e){
	            e.printStackTrace();
	        }

	}

	@Override
	public void SearchByDate(String date) {
		

	}

	
	public static void UpdateData(Petrol Pt) {
		Connection con = CreateConnection.getConnection();
        final String SQL = "update petrol set P_price=?, S_price=? ,sale=? where dates=? ";
        try(PreparedStatement stmt = con.prepareStatement(SQL)){
        	  stmt.setDouble(1, Pt.getP_Price());
        	  stmt.setDouble(2, Pt.getS_Price());
        	  stmt.setDouble(3, Pt.getSale());
        	  stmt.setString(4,Pt.getDate());
          
            
            int rowsAffected = stmt.executeUpdate();
            System.out.println(rowsAffected+" rows has been updated in petrol sale data.");
        }catch(SQLException e){
            e.printStackTrace();
        }

	}

	
	public static void DeleteData(String d) {
		
		  Connection con = CreateConnection.getConnection();
		  final String SQL = "DELETE FROM petrol WHERE dates=?";
	        try(PreparedStatement stmt = con.prepareStatement(SQL)){
	            stmt.setString(1,d);
	           
	            int rowsAffected = stmt.executeUpdate();
	            System.out.println(rowsAffected+" row has been deleted from petrol sales data.");
	        }catch(SQLException e){
	            e.printStackTrace();
	        }

	}

	
	@Override
	public String toString() {
		return "Date=" + Date + ", P_Price=" + P_Price + ", S_Price=" + S_Price + ", Sale=" + Sale + "]";
	}

	@Override
	public void getAlldata() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void searchByprofit() {
		// TODO Auto-generated method stub
		
	}

}
