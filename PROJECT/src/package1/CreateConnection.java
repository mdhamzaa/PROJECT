package package1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

class CreateConnection {
	    private static final String USERNAME = "root";
	    private static final String PASSWORD = "769769";
	    private static final String URL = "jdbc:mysql://localhost:3306/project";

	    public static Connection getConnection(){
	        Connection con = null;
	        try {
	         
	            Properties connectionProperties = new Properties();
	            connectionProperties.put("user",USERNAME);
	            connectionProperties.put("password",PASSWORD);
	            con = DriverManager.getConnection(URL,connectionProperties);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return con;
	    }

}
