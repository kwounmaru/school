package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ArrayListTest {
	
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		String url = "jdbc:mysql://localhost:3306/School?useSSL=false&allowPublicKeyRetrieval=true&useSSL=false";
		String userid = "root";
		String userpw = "root";
		String sql = "SELECT * FROM data";
		
		try {
			conn = DriverManager.getConnection(url, userid, userpw);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			List<String[]> jobList = new ArrayList<String[]>();
			while (rs.next()) {
				String[] arrStr = {rs.getString("no"), rs.getString("name")};
				jobList.add(arrStr);
			}
			
			System.out.println(jobList.get(8)[1]);
			
			/*for (int i = 0; i < jobList.size(); i++) {
				System.out.println("번호 : " + jobList.get(i)[0]);
				System.out.println("과목 : " + jobList.get(i)[1]);
			}*/
			
		} catch(Exception e) {
			System.out.println("SQL Error" + e.getMessage());
			
		}
		
	}

}
