package sports;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectDB {

private Connection connection = null;
	
	public Connection getConnect(){
		
		try {
			
			//加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			//得到链接
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sportgrademanage", "root", "liuchengsql");
		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return connection;
	}
}
