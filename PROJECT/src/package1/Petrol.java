package package1;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


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

	@Override
	public String toString() {
		System.out.println("----------------------------------------------------------------------");
		return "| Date=" + Date + "  | P_Price=" + P_Price + "  | S_Price=" + S_Price + "  | Sale=" + Sale + " |";
	}

	public static void AddTodyData(Petrol P) {
		Connection con = CreateConnection.getConnection();
		final String SQL = "insert into petrol values(?,?,?,?)";
		try (PreparedStatement stmt = con.prepareStatement(SQL)) {
			stmt.setString(1, P.getDate());
			stmt.setDouble(2, P.getP_Price());
			stmt.setDouble(3, P.getS_Price());
			stmt.setDouble(4, P.getSale());
			int rowsAffected = stmt.executeUpdate();
			System.out.println(rowsAffected + " row has been added to petrol sales data.");
			ptrl.add(P);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void UpdateData(Petrol Pt) {
		Connection con = CreateConnection.getConnection();
		final String SQL = "update petrol set P_price=?, S_price=? ,sale=? where dates=? ";
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

					ptrl.set(i, Pt);
				}
			}
			System.out.println(rowsAffected + " rows has been updated in petrol sale data.");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void DeleteData(String d) {

		Connection con = CreateConnection.getConnection();
		final String SQL = "DELETE FROM petrol WHERE dates=?";
		try (PreparedStatement stmt = con.prepareStatement(SQL)) {
			stmt.setString(1, d);

			int rowsAffected = stmt.executeUpdate();
			int n = ptrl.size();
			for (int i = 0; i < n; i++) {
				if (ptrl.get(i).getDate().equals(d)) {

					ptrl.remove(i);
				}
			}
			System.out.println(rowsAffected + " row has been deleted from petrol sales data.");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static List<Petrol> Search(String date) {

		List<Petrol> ptbydate = new ArrayList<>();
		Connection con = CreateConnection.getConnection();
		final String SQL = "select *from petrol where dates = ?";
		try (PreparedStatement stmt = con.prepareStatement(SQL)) {
			stmt.setString(1, date);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Petrol pt = new Petrol(rs.getString("Dates"),rs.getDouble("P_Price"),rs.getDouble("S_Price"),rs.getDouble("Sale"));
				ptbydate.add(pt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ptbydate;
	}
    
	public static List<Petrol> Search(Double Pprice) {

		List<Petrol> ptbyPprice = new ArrayList<>();
		Connection con = CreateConnection.getConnection();
		final String SQL = "select *from petrol where P_sale = ?";
		try (PreparedStatement stmt = con.prepareStatement(SQL)) {
			stmt.setDouble(1, Pprice);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Petrol pt = new Petrol(rs.getString("Dates"),rs.getDouble("P_Price"),rs.getDouble("S_Price"),rs.getDouble("Sale"));
				ptbyPprice.add(pt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ptbyPprice;
	}
	

	public static 	List<Petrol> SearchBysale(double ltr, char opr) {
		List<Petrol> ptbyPprice = new ArrayList<>();
		Connection con = CreateConnection.getConnection();
		String SQL;
		if(opr=='>') {
		     SQL = "select *from petrol where sale > ?";
		}else if (opr=='<') {
			 SQL = "select *from petrol where sale < ?";	
		}else if(opr=='=') {
			 SQL = "select *from petrol where sale = ?";
		}else {
			System.out.println("given operation is not possible");
			return null;
		}
		try (PreparedStatement stmt = con.prepareStatement(SQL)) {
			
			
			
			stmt.setDouble(1, ltr);
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Petrol pt = new Petrol(rs.getString("Dates"),rs.getDouble("P_Price"),rs.getDouble("S_Price"),rs.getDouble("Sale"));
				ptbyPprice.add(pt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ptbyPprice;
			}

	
	  public static List<Petrol> sort(String colname){
			List<Petrol> sorted = new ArrayList<>();
			Connection con = CreateConnection.getConnection();
			 String SQL = "select *from petrol ORDER BY "+colname+ " ASC";
			try (PreparedStatement stmt = con.prepareStatement(SQL)) {
			    
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					Petrol pt = new Petrol(rs.getString("Dates"),rs.getDouble("P_Price"),rs.getDouble("S_Price"),rs.getDouble("Sale"));
					sorted.add(pt);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return sorted;
		  
	  }
}
