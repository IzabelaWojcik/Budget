package learning.budget;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection{
	private final static String conectionUrl = "jdbc:sqlite:C:\\sqlite\\sqlite-tools-win32-x86-3260000\\budget.db";

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
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection(conectionUrl);
		}catch(Exception e){
			e.printStackTrace();
		}
		return con;
	}
}
