package model;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import database.DBAccess;

import oracle.jdbc.driver.OracleDriver;

import security.Encrypt;
import security.SystemUnavailableException;


public class Customer extends User {

	//Every customer has a cart.
	private Map<String, ArrayList<Object>> cart = new ConcurrentHashMap<String, ArrayList<Object>>();
	
	public Map<String, ArrayList<Object>> getCart() {
		return cart;
	}
	//Unused.
	public void setCart(Map<String, ArrayList<Object>> cart) {
		
	}
	//called when customer adds a product to the cart
	public void addToCart(String pname, ArrayList<Object> product) {
		cart.put(pname,product);
	}
	//called when customer removes a product to the cart
	public void removeFromCart(String pname) {
			cart.remove(pname);
	}

	@Override
	public ArrayList<Object> getUserDetails() {
		// TODO Auto-generated method stub
		
		return userDetails;
	}
	
	public void setUserDetails(String username) {
		try {
			//Retrieve user info from the database and set the member variable.
			String sql = "select * from ADMIN.CUSTOMER where EMAIL=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			rs.next();
			for(int i = 1; i <= 7; i++) {
				System.out.println(rs.getString(i));
				userDetails.add(rs.getString(i));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*To register a new Customer in the database. If a customer already exists with the same
	email id, false is returned indicating a different email must be supplied for successful registration*/
	@Override
	public boolean create(ArrayList<Object> userDetails) {
		// TODO Auto-generated method stub
		String sql;
		try {
		PreparedStatement ps; 
		sql = "insert into ADMIN.CUSTOMER values(?,?,?,?,?,?,?)";
		ps = con.prepareStatement(sql);
		int i = 1;
		for(Object o : userDetails)
			ps.setString(i++, (String)o);
		ps.setString(4, Encrypt.getInstance().hash("SHA-512", (String)userDetails.get(3), true));
		ps.executeQuery();
		}
		catch(SQLException e) {
			return false;//invoked when a customer already exists with the same name in the database causing violation of pk constraint
		} catch (SystemUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	//Used to update an existing user's personal details
	public void updateUserDetails(ArrayList<Object> userDetails) {
		// TODO Auto-generated method stub
		String email = (String)userDetails.get(2);
		//First delete the existing user's details
		
		OracleDriver driver = new OracleDriver();
		try {
			DriverManager.registerDriver(driver);
			con = DBAccess.getDataSource()[0].getConnection();
			//con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "scott", "tiger");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String sql = "delete from ADMIN.CUSTOMER where EMAIL=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, email);
			ps.executeQuery();
			//Then create the user with the updated user details.
			create(userDetails);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	

	
	//Processing for a single product..
	public void placeOrder(ArrayList<Object> order) {
		
		try {
			//update ORDERS relation
			String sql = "insert into ADMIN.ORDERS values(?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, (String)order.get(0));
			ps.setString(2, (String)order.get(1));
			ps.setString(3, (String)order.get(2));
			Date date = new Date((Long)order.get(3));
			ps.setDate(4, date);
			ps.setInt(5, (Integer)order.get(4));
			ps.executeQuery();
			
			//update QUANTITY after purchase
			sql = "update ADMIN.PRODUCT set QUANTITY=QUANTITY-? where PNAME=?";
			ps = con.prepareStatement(sql);
			String pname = (String)order.get(1);
			int no = (Integer)order.get(4);
			ps.setInt(1, no);
			ps.setString(2, pname);
			ps.executeQuery();
			
			//set product status
			sql = "select QUANTITY from ADMIN.PRODUCT where PNAME=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, (String)order.get(1));
			ResultSet rs = ps.executeQuery();
			rs.next();
			int quantity = rs.getInt("QUANTITY");
			if(quantity==0){
				sql = "update ADMIN.PRODUCT set STATUS=? where PNAME=?";
				ps = con.prepareStatement(sql);
				ps.setString(1, "OUT_OF_STOCK");
				ps.setString(2, (String)order.get(1));
			}
			
			removeFromCart(pname);//removing the checked-out item from the cart
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	//Submit rating and review.
	public void submitFeedback(String pname, String review, int newRating){
		try{
			String sql = "select RATING from ADMIN.PRODUCT where PNAME=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, pname);
			ResultSet rs = ps.executeQuery();
			rs.next();
			String oldRating = rs.getString("RATING");
			int separatorIndex = oldRating.indexOf("|");//rating=oldR|no_of_votes
			float oldR = Float.parseFloat(oldRating.substring(0, separatorIndex));
			int no_of_votes = Integer.parseInt(oldRating.substring(separatorIndex + 1));
			float newR = (oldR * no_of_votes + newRating) / (++no_of_votes);
			String rating = newR + "|" + no_of_votes;
			
			sql = "select REVIEW from ADMIN.PRODUCT where PNAME=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, pname);
			rs = ps.executeQuery();
			rs.next();
			String reviews = rs.getString("REVIEW");
			if(reviews == null)//checking for the 1st review
				reviews = "" + review;
			else
				reviews += review;
			sql = "update ADMIN.PRODUCT set REVIEW=? , RATING=? where PNAME=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, reviews);
			ps.setString(2, rating);
			ps.setString(3, pname);
			rs = ps.executeQuery();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
