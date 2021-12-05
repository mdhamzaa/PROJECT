package package1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

final class Diesel implements GasStation {

	private String Date;
	private double P_Price;
	private double S_Price;
	private double Sale;

	

	public Diesel(String date, double p_Price, double s_Price, double sale) {

		Date = date;
		P_Price = p_Price;
		S_Price = s_Price;
		Sale = sale;
	}
	
	public Diesel(String PLine) {
		String values[] = PLine.split(",");
		this.Date = values[0];
		this.P_Price = Double.valueOf(values[1]);
		this.S_Price = Double.valueOf(values[2]);
		this.Sale = Double.valueOf(values[3]);

	}

	@Override
	public String toString() {
		System.out.println("----------------------------------------------------------------------");
		return "Date=" + Date + ", P_Price=" + P_Price + ", S_Price=" + S_Price + ", Sale=" + Sale + "]";
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

	public static void AddTodyData(Diesel P) {
		Connection con = CreateConnection.getConnection();
		final String SQL = "insert into Diesel values(?,?,?,?)";
		try (PreparedStatement stmt = con.prepareStatement(SQL)) {
			stmt.setString(1, P.getDate());
			stmt.setDouble(2, P.getP_Price());
			stmt.setDouble(3, P.getS_Price());
			stmt.setDouble(4, P.getSale());
			int rowsAffected = stmt.executeUpdate();
			System.out.println(rowsAffected + " row has been added to Diesel sales data.");
			dsl.add(P);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void UpdateData(Diesel Pt) {
		Connection con = CreateConnection.getConnection();
		final String SQL = "update Diesel set P_price=?, S_price=? ,sale=? where dates=? ";
		try (PreparedStatement stmt = con.prepareStatement(SQL)) {
			stmt.setDouble(1, Pt.getP_Price());
			stmt.setDouble(2, Pt.getS_Price());
			stmt.setDouble(3, Pt.getSale());
			stmt.setString(4, Pt.getDate());

			int rowsAffected = stmt.executeUpdate();
			int n = ptrl.size();
			int i;
			for (i = 0; i < n; i++) {
				if (ptrl.get(i).getDate().equals(Pt.getDate())) {

					dsl.set(i, Pt);
				}
			}
			System.out.println(rowsAffected + " rows has been updated in Diesel sale data.");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void DeleteData(String d) {

		Connection con = CreateConnection.getConnection();
		final String SQL = "DELETE FROM Diesel WHERE dates=?";
		try (PreparedStatement stmt = con.prepareStatement(SQL)) {
			stmt.setString(1, d);

			int rowsAffected = stmt.executeUpdate();
			int n = ptrl.size();
			for (int i = 0; i < n; i++) {
				if (ptrl.get(i).getDate().equals(d)) {

					ptrl.remove(i);
				}
			}
			System.out.println(rowsAffected + " row has been deleted from Diesel sales data.");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static List<Diesel> Search(String date) {

		List<Diesel> ptbydate = new ArrayList<>();
		Connection con = CreateConnection.getConnection();
		final String SQL = "select *from Diesel where dates = ?";
		try (PreparedStatement stmt = con.prepareStatement(SQL)) {
			stmt.setString(1, date);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Diesel pt = new Diesel(rs.getString("Dates"),rs.getDouble("P_Price"),rs.getDouble("S_Price"),rs.getDouble("Sale"));
				ptbydate.add(pt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ptbydate;
	}
    
	public static List<Diesel> Search(Double Pprice) {

		List<Diesel> ptbyPprice = new ArrayList<>();
		Connection con = CreateConnection.getConnection();
		final String SQL = "select *from Diesel where P_sale = ?";
		try (PreparedStatement stmt = con.prepareStatement(SQL)) {
			stmt.setDouble(1, Pprice);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Diesel pt = new Diesel(rs.getString("Dates"),rs.getDouble("P_Price"),rs.getDouble("S_Price"),rs.getDouble("Sale"));
				ptbyPprice.add(pt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ptbyPprice;
	}
	

	public static 	List<Diesel> SearchBysale(double ltr, char opr) {
		List<Diesel> DbyPprice = new ArrayList<>();
		Connection con = CreateConnection.getConnection();
		String SQL;
		if(opr=='>') {
		     SQL = "select *from Diesel where sale > ?";
		}else if (opr=='<') {
			 SQL = "select *from Diesel where sale < ?";	
		}else if(opr=='=') {
			 SQL = "select *from Diesel where sale = ?";
		}else {
			System.out.println("given operation is not possible");
			return null;
		}
		try (PreparedStatement stmt = con.prepareStatement(SQL)) {
			
			
			
			stmt.setDouble(1, ltr);
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Diesel pt = new Diesel(rs.getString("Dates"),rs.getDouble("P_Price"),rs.getDouble("S_Price"),rs.getDouble("Sale"));
				DbyPprice.add(pt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return DbyPprice;

	}
	
	
	

	

}
