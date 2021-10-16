package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseUtil {
	
	public static Connection getConnection() {
		try {
			String dbURL = "jdbc:mysql://localhost:3306/Timetable";
			String dbID = "Kwoun5812!";
			String dbPassword = "Kwoun5812!";
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(dbURL, dbID, dbPassword);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
