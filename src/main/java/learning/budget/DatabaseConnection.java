package learning.budget;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection{
	private final static String conectionUrl = "jdbc:sqlserver://localhost:1433;" + 
			"databasename=Budget;integratedSecurity=true;";
	private static Connection con = null;
	
	private DatabaseConnection() {}
	
	public static Connection getInstance() {
		if(con == null) {
			con = connectionWithDB();
		}
		return con;
	}
	
	private static Connection connectionWithDB(){
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(conectionUrl);

		}catch(Exception e){
			e.printStackTrace();
		}
		return con;
	}
	
}
