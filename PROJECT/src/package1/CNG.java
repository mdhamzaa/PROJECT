package package1;

final class CNG implements GasStation {

	private String Date;
	private int P_Price;
	private int S_Price;
	private int Sale;
    
	
	
	@Override
	public String toString() {
		return "Date=" + Date + ", P_Price=" + P_Price + ", S_Price=" + S_Price + ", Sale=" + Sale + "]";
	}

	public CNG(String date, int p_Price, int s_Price, int sale) {

		Date = date;
		P_Price = p_Price;
		S_Price = s_Price;
		Sale = sale;
	}

	public int getP_Price() {
		return P_Price;
	}

	public void setP_Price(int p_Price) {
		P_Price = p_Price;
	}

	public int getS_Price() {
		return S_Price;
	}

	public void setS_Price(int s_Price) {
		S_Price = s_Price;
	}

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

	public int getSale() {
		return Sale;
	}

	public void setSale(int sale) {
		Sale = sale;
	}

	
	public void AddTodyData() {
		// TODO Auto-generated method stub

	}

	@Override
	public void SearchByDate(String date) {
		// TODO Auto-generated method stub

	}

	
	public void UpdateData() {
		// TODO Auto-generated method stub

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

