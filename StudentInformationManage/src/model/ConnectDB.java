package model;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectDB {

private Connection connection = null;
	
	public Connection getConnect(){
		
		
		try {
			
			//¼ÓÔØÇý¶¯
			Class.forName("com.mysql.jdbc.Driver");
			//µÃµ½Á´½Ó
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_information", "root", "liuchengsql");
		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return connection;
	}
}
