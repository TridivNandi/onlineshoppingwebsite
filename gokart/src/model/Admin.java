package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class Admin extends User {

	@Override
	public ArrayList<Object> getUserDetails() {
		// TODO Auto-generated method stub
		return userDetails;
	}

	public void setUserDetails(String username) {
		try {
			//Retrieve user info from the database and set the member variable.
			String sql = "select * from ADMIN where USERNAME=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int n = rsmd.getColumnCount();
			if(rs.next()) {
				for(int i = 1; i <= n; i++) {
					userDetails.add(rs.getString(i));
					//System.out.println("userDetails = " + userDetails);
					//System.out.println("rs = " + rs);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Unused.
	@Override
	public boolean create(ArrayList<Object> userDetails) {
		return true;
	}
	//Create a DBM_USER account.
	public boolean createDbmUser(ArrayList<Object> userDetails) {
		
		String sql="select USERNAME from ADMIN.DBM_USER where USERNAME=?";
		try {
		PreparedStatement ps;
		//rs.next();
		sql ="insert into ADMIN.DBM_USER values (?,?,?,?,?)";
		ps=con.prepareStatement(sql);
		int i = 1;
		for(Object o : userDetails)
			ps.setString(i++,(String) o);	
		ps.executeUpdate();
		}
		catch(SQLException e){
			return false;//Username already exists
		}
		return true;
		
	}
	//Remove a DBM_USER account.
	public boolean removeDbmUser(String usn){
		
		String sql;
		try {
			PreparedStatement ps;
			sql = "delete from ADMIN.DBM_USER where USERNAME=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, usn);
			ps.executeQuery();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return true;
		
	}
	
	public boolean updateDbmUser(ArrayList<Object> userDetails){
		
		String sql;
		try {
			PreparedStatement ps;
			sql="update ADMIN.DBM_USER set FIRST_NAME=?, LAST_NAME=?, PASSWORD=?, PHONE_NO=? where USERNAME=?";
			ps = con.prepareStatement(sql);
			for(int i = 0; i < 5; i++)
				System.out.println(userDetails.get(i));
			ps.setString(1, (String)userDetails.get(0));
			ps.setString(2, (String)userDetails.get(1));
			ps.setString(3, (String)userDetails.get(3));
			ps.setString(4, (String)userDetails.get(4));
			
			ps.setString(5, (String)userDetails.get(2));
			
			System.out.println(ps.executeUpdate());
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return true;
		
	}
	


}