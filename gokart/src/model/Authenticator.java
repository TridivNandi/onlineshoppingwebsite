package model;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import oracle.jdbc.driver.OracleDriver;

public final class Authenticator {
	
	private static Authenticator authenticator;
	static {
			authenticator = new Authenticator();
	}
	
	public static Authenticator getAuthenticator() {
			return authenticator;
	}
	//Return connection based on user-type. Return null if connection is unsuccessful.
	public Connection authenticate(String usn, char[] pass, User user) throws SQLException {
		Connection con;
		String sql;
		if(user instanceof Customer) {
			OracleDriver driver = new OracleDriver();
			DriverManager.registerDriver(driver);
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "scott", "tiger");
			sql = "select * from ADMIN.CUSTOMER where EMAIL=? and PASSWORD=?";
		}
		else if(user instanceof DbmUser) {
			// con = DBAccess.getDataSource()[1].getConnection();
			OracleDriver driver = new OracleDriver();
			DriverManager.registerDriver(driver);
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "dbm", "parker");
			sql = "select * from ADMIN.DBM_USER where USERNAME=? and PASSWORD=?";
		}
		else {
			OracleDriver driver = new OracleDriver();
			DriverManager.registerDriver(driver);
			//con = DBAccess.getDataSource()[2].getConnection();
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "admin", "rmsgnu83");
			sql = "select * from ADMIN.ADMIN where USERNAME=? and PASSWORD=?";
		}
		
		PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ps.setString(1, usn);
		ps.setString(2, new String(pass));
		ResultSet rs = ps.executeQuery();
		//System.out.print(rs.first());
		if(rs.next())
			return con;
		else {
			System.out.println("Authenticatior 2: " + usn + " " + new String(pass));
			return null;
		}
	}

}
