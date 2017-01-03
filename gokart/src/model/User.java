package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class User {

	protected ArrayList<Object> userDetails = new ArrayList<Object>();
	//Every user has a connection to the database which is already established.
	protected Connection con;
	
	public Connection getConnection() {
			return con;
	}
	
	public abstract ArrayList<Object> getUserDetails();
	
	public abstract void setUserDetails(String username);

	public abstract boolean create(ArrayList<Object> userDetails);
	//To connect to the database.
	public boolean connect(String usn, char[] pass) {
		try {
			this.con = Authenticator.getAuthenticator().authenticate(usn, pass, this);
			System.out.println("User: " + con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(this.con != null) {
			setUserDetails(usn);
			return true;
		}	
		else
			return false;
	}
	
}
