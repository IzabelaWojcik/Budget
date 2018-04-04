package classes;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
	private String conectionUrl = "jdbc:sqlserver://localhost:1433;" + 
			"databasename=Budget;integratedSecurity=true;";
	private Connection con = null;
	//private Statement start = null;
	//private ResultSet rs = null;
	
	public Connection connectionWithDB(){
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(conectionUrl);
			//System.out.println("Connection Established");
			
			//insert into database
			//start = con.createStatement();
			//start.executeUpdate("insert into Dues_category " + "values('Fundusz remontowy')");
		
			//select from database
		//	String SQL = "Select * from dbo.Dues_category";
		//	start = con.createStatement();
			//rs = start.executeQuery(SQL);
			//start = con.createStatement();
		//	while(rs.next()){
		//		System.out.println(rs.getInt("id_dues_category") + rs.getString("name"));
		////	}
		}catch(Exception e){
			e.printStackTrace();
		}
		return con;
	}
	
}
