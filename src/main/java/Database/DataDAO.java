package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataDAO { 
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; 
	// jdbc 드라이버 주소 
	
	static final String DB_URL = "jdbc:mysql://localhost:3306/School?useSSL=false&allowPublicKeyRetrieval=true&useSSL=false"; 
	// DB 접속 주소 
	//localhost는 접속하려는 데이터베이스 주소를 입력하시면 됩니다. localhost를 사용하면 됩니다. 
	//3306은 데이터베이스에 접속할때 사용하는 포터번호입니다. 설치할때 설정한 포트번호를 사용합니다. 
	//databasename에는 접속하려는 database의 name을 입력해줍니다. 
	
	static final String USERNAME = "root"; 
	// DB ID 
	static final String PASSWORD = "root"; 
	// DB Password 
	
	private static Connection conn = null; 
	private Statement stmt = null; 
	private ResultSet rs = null; 
	//데이터베이스 접속을 확인합니다. 
	
	public DataDAO(){ 
		// DB 접속 
		System.out.print("DatabaseName 데이터베이스 접속 : "); 
		try { 
			Class.forName(JDBC_DRIVER); 
			conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD); 
			if (conn != null){
				System.out.println("성공");
				} 
			else{
				System.out.println("실패");
				}
			} catch (ClassNotFoundException e) { 
				System.out.println("Class Not Found Exection"); 
				e.printStackTrace(); 
				} catch (SQLException e) {
					System.out.println("SQL Exception"); 
					e.printStackTrace(); 
					} 
		}
	//UserDAO 
	// 데이터베이스에 데이터를 Insert하는 Method입니다. 
	
	public void tableInsert(int no, String name){ 
		
		Data tn = new Data(no, name); 
		//외부에서 매개변수로 데이터를 받아 Qurey문을 만들어 줍니다. 
		
		String query = "INSERT INTO data " + "VALUE (" + tn.no + ", '" + tn.name +"');"; 
		System.out.println(query); 
		// Qurey문 확인 
		
		try { 
			// 데이터베이스에 접속합니다. 
			Class.forName(JDBC_DRIVER); 
			conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD); 
			
			stmt = conn.createStatement(); 
			//데이터베이스에 query문을 넘길 Statement를 만들어줍니다. 
			
			stmt.executeUpdate(query); 
			// query를 실행시킵니다. 
			
			stmt.close(); 
			conn.close(); 
			} catch (ClassNotFoundException e) { 
				System.out.println("Class Not Found Exection"); 
				} catch (SQLException e) { 
					System.out.println("SQL Exception : " + e.getMessage()); 
					} 
		} 
	public void tableSelect() { 
		
		String query = "SELECT * FROM data"; 
		// table에 모든 레코드를 출력하는 query문입니다. 
		
		Data tn = new Data(); 
		// TableName 객체를 만들어줍니다. 
		
		try { 
			
			// 데이터베이스에 접속합니다. 
			Class.forName(JDBC_DRIVER); 
			conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD); 
			
			stmt = conn.createStatement(); 
			//데이터베이스에 query문을 넘길 Statement를 만들어줍니다. 
			
			rs = stmt.executeQuery(query); 
			// query를 실행시킵니다. 
			//executeQuery()와 executeUpdate()다른 상황에 사용하는 명령어입니다. 
			//executeUpdate() - INSERT, UPDATE, DELETE 문과 같은 형태에서 사용합니다.. 
			//executeQuery() - DDL문과 SELECT문에서 사용하시면 됩니다. 
			//객체에서 위에서 부터 데이터를 출력합니다. 
			
			while (rs.next()){ 
				tn.setNo(rs.getInt("no")); 
				tn.setName(rs.getString("name")); 
				System.out.println("no : " + tn.getNo() + " name : " + tn.name); 
				} 
			stmt.close(); 
			
			stmt.close(); 
			conn.close(); 
			} catch (ClassNotFoundException e) { 
				System.out.println("Class Not Found Exection"); 
			} catch (SQLException e) { 
				System.out.println("SQL Exception : " + e.getMessage()); 
			} 
		}
	public static void main(String[] args) throws Exception {
		Data tn = new Data();
		System.out.println("no : " + tn.getNo() + " name : " + tn.name);
	}
}
