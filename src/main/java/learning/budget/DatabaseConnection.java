package learning.budget;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
	private String conectionUrl = "jdbc:sqlserver://localhost:1433;" + 
			"databasename=Budget;integratedSecurity=true;";
	private Connection con = null;
	
	public Connection connectionWithDB(){
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(conectionUrl);
		}catch(Exception e){
			e.printStackTrace();
		}
		return con;
	}
}
