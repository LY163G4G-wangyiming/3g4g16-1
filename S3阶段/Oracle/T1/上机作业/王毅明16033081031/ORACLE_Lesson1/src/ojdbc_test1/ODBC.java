package ojdbc_test1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.CBA;

public class ODBC {
	private static Connection conn;
	private static String url;
	private static String user;
	private static String password;
	private PreparedStatement ps;
	private static Connection getConn(){
		try {
			url = "jdbc:oracle:thin:127.0.0.1:1521:orcl";
			user = "sys as sysdba";
			password = "123";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url,user,password);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return conn;
	}
	private void Insert(CBA cba){
		conn = getConn();
		String sql = "insert into cba values(?,?,?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1,cba.getId());
			ps.setString(2, cba.getName());
			ps.setString(3, cba.getSex());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		CBA cba = new CBA();
		cba.setId(4);
		cba.setName("ÕÅÈý·á");
		cba.setSex("ÄÐ");
		ODBC odbc = new ODBC();
		odbc.Insert(cba);
		System.out.println(getConn());
	}
}
