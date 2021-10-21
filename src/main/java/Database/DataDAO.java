package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataDAO { 
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; 
	// jdbc ����̹� �ּ� 
	
	static final String DB_URL = "jdbc:mysql://localhost:3306/School?useSSL=false&allowPublicKeyRetrieval=true&useSSL=false"; 
	// DB ���� �ּ� 
	//localhost�� �����Ϸ��� �����ͺ��̽� �ּҸ� �Է��Ͻø� �˴ϴ�. localhost�� ����ϸ� �˴ϴ�. 
	//3306�� �����ͺ��̽��� �����Ҷ� ����ϴ� ���͹�ȣ�Դϴ�. ��ġ�Ҷ� ������ ��Ʈ��ȣ�� ����մϴ�. 
	//databasename���� �����Ϸ��� database�� name�� �Է����ݴϴ�. 
	
	static final String USERNAME = "root"; 
	// DB ID 
	static final String PASSWORD = "root"; 
	// DB Password 
	
	private static Connection conn = null; 
	private Statement stmt = null; 
	private ResultSet rs = null; 
	//�����ͺ��̽� ������ Ȯ���մϴ�. 
	
	public DataDAO(){ 
		// DB ���� 
		System.out.print("DatabaseName �����ͺ��̽� ���� : "); 
		try { 
			Class.forName(JDBC_DRIVER); 
			conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD); 
			if (conn != null){
				System.out.println("����");
				} 
			else{
				System.out.println("����");
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
	// �����ͺ��̽��� �����͸� Insert�ϴ� Method�Դϴ�. 
	
	public void tableInsert(int no, String name){ 
		
		Data tn = new Data(no, name); 
		//�ܺο��� �Ű������� �����͸� �޾� Qurey���� ����� �ݴϴ�. 
		
		String query = "INSERT INTO data " + "VALUE (" + tn.no + ", '" + tn.name +"');"; 
		System.out.println(query); 
		// Qurey�� Ȯ�� 
		
		try { 
			// �����ͺ��̽��� �����մϴ�. 
			Class.forName(JDBC_DRIVER); 
			conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD); 
			
			stmt = conn.createStatement(); 
			//�����ͺ��̽��� query���� �ѱ� Statement�� ������ݴϴ�. 
			
			stmt.executeUpdate(query); 
			// query�� �����ŵ�ϴ�. 
			
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
		// table�� ��� ���ڵ带 ����ϴ� query���Դϴ�. 
		
		Data tn = new Data(); 
		// TableName ��ü�� ������ݴϴ�. 
		
		try { 
			
			// �����ͺ��̽��� �����մϴ�. 
			Class.forName(JDBC_DRIVER); 
			conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD); 
			
			stmt = conn.createStatement(); 
			//�����ͺ��̽��� query���� �ѱ� Statement�� ������ݴϴ�. 
			
			rs = stmt.executeQuery(query); 
			// query�� �����ŵ�ϴ�. 
			//executeQuery()�� executeUpdate()�ٸ� ��Ȳ�� ����ϴ� ��ɾ��Դϴ�. 
			//executeUpdate() - INSERT, UPDATE, DELETE ���� ���� ���¿��� ����մϴ�.. 
			//executeQuery() - DDL���� SELECT������ ����Ͻø� �˴ϴ�. 
			//��ü���� ������ ���� �����͸� ����մϴ�. 
			
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
