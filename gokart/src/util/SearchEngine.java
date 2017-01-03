package util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.DBAccess;

import oracle.jdbc.driver.OracleDriver;

public class SearchEngine {

	private static Connection con;
	static String checker[];
	
	public static String[] getChecker() {
		return checker;
	}
	public static void setChecker(String[] checker) {
		SearchEngine.checker = checker;
	}

	static {
		try {
			//OracleDriver driver = new OracleDriver();
			//DriverManager.registerDriver(driver);
			//con = DBAccess.getDataSource()[0].getConnection();
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "scott", "tiger");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection getCon() {
		return con;
	}
	public static void setCon(Connection con) {
		SearchEngine.con = con;
	}
	//normal search with a search word..
	public ArrayList<ArrayList<Object>> search(String searchWord) {
		searchWord = searchWord.toLowerCase();
		searchWord = "%" + searchWord + "%";
		String sql = "with viewer as(select lower(PNAME) as PNAME,IMAGE,RATING,BASE_PRICE,DISCOUNT,STATUS from ADMIN.PRODUCT) select PNAME,IMAGE,RATING,BASE_PRICE,DISCOUNT,STATUS from viewer where PNAME like ?";
		ResultSet rs = null;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, searchWord);
			rs = ps.executeQuery();
			ArrayList<ArrayList<Object>> result = new  ArrayList<ArrayList<Object>>();
			ArrayList<Object> row = new ArrayList<Object>();
			while(rs.next()) {
				row.add(rs.getString("PNAME"));
				row.add(rs.getString("IMAGE"));
				String rating = rs.getString("RATING");
				row.add(rating.substring(0,rating.indexOf("|")));
				row.add(rs.getFloat("BASE_PRICE"));
				row.add(rs.getFloat("DISCOUNT"));
				row.add(rs.getString("STATUS"));
				System.out.println(row);
				result.add(row);
				//row.removeAll(row);
				row = new ArrayList<Object>();
				
			}
			if(result.size() != 0)
				return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	//search with refinement according to price..
	public ArrayList<ArrayList<Object>> search(String searchWord, float startPrice, float endPrice) {
		ResultSet rs = null;
		searchWord = "%" + searchWord + "%";
		String dPrice = "(BASE_PRICE) - (BASE_PRICE)*DISCOUNT/100";
		String sql = "select PNAME,IMAGE,RATING,BASE_PRICE,DISCOUNT,STATUS from ADMIN.PRODUCT where PNAME like ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, searchWord);
			rs = ps.executeQuery();
			ArrayList<ArrayList<Object>> result = new  ArrayList<ArrayList<Object>>();
			ArrayList<Object> row = new ArrayList<Object>();
			while(rs.next()) {
				row.add(rs.getString("PNAME"));
				row.add(rs.getString("IMAGE"));
				row.add(rs.getFloat("RATING"));
				row.add(rs.getFloat("BASE_PRICE"));
				row.add(rs.getFloat("DISCOUNT"));
				row.add(rs.getString("STATUS"));
				System.out.println(row);
				result.add(row);
				//row.removeAll(row);
				row = new ArrayList<Object>();
				
			}
			if(result.size() != 0)
				return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	//search for exact product.
	public ArrayList<Object> searchForMatchingProduct(String pname) {
		String sql = "select IMAGE,BASE_PRICE, DISCOUNT from ADMIN.PRODUCT where PNAME=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, pname);
			ResultSet rs = ps.executeQuery();
			rs.next();
			ArrayList<Object> addedProduct = new ArrayList<Object>();
			addedProduct.add(rs.getString(1));
			addedProduct.add(rs.getFloat(2));
			addedProduct.add(rs.getFloat(3));
			return addedProduct;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	//Returns all products of a certain category.
	public ArrayList<ArrayList<Object>> searchByCategory(String category) {
		String sql = "select PNAME,IMAGE,RATING,BASE_PRICE,DISCOUNT,STATUS from ADMIN.PRODUCT where CATEGORY=?";
		ResultSet rs = null;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, category);
			rs = ps.executeQuery();
			ArrayList<ArrayList<Object>> result = new  ArrayList<ArrayList<Object>>();
			ArrayList<Object> row = new ArrayList<Object>();
			while(rs.next()) {
				row.add(rs.getString("PNAME"));
				row.add(rs.getString("IMAGE"));
				String rating = rs.getString("RATING");
				row.add(rating.substring(0,rating.indexOf("|")));	
				row.add(rs.getFloat("BASE_PRICE"));
				row.add(rs.getFloat("DISCOUNT"));
				row.add(rs.getString("STATUS"));
				System.out.println(row);
				result.add(row);
				//row.removeAll(row);
				row = new ArrayList<Object>();
				
			}
			if(result.size() != 0)
				return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//Returns all details associated with a certain product.
	public ArrayList<Object> searchProduct(String pname) {
		String sql = "select * from ADMIN.PRODUCT where PNAME=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, pname);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
			ArrayList<Object> addedProduct = new ArrayList<Object>();
				addedProduct.add(rs.getString(1));
				addedProduct.add(rs.getString(2));
				//addedProduct.add(rs.getBlob(3));
				//addedProduct.add(rs.getClob(4));
				addedProduct.add(rs.getString(3));
				
				InputStream is = (InputStream)rs.getBinaryStream(4);
				//BufferedReader br = new BufferedReader(new InputStreamReader(is));
				String text = "";
				int t;
				try {
					while((t = is.read()) != -1)
						text = text + (char) t;
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				addedProduct.add(text);
				
				String rating = rs.getString(5);
				addedProduct.add(rating.substring(0,rating.indexOf("|")));
				addedProduct.add(rs.getString(6));
				addedProduct.add(rs.getInt(7));
				addedProduct.add(rs.getDouble(8));
				addedProduct.add(rs.getDouble(9));
				addedProduct.add(rs.getString(10));
				return addedProduct;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	
		
	}
	
	//Used by admin to search for a certain DBM_USER in the database. Returns null if username doesn't exist
	public ArrayList<Object> searchDbmUser(String usn) {
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "admin", "rmsgnu83");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		String sql = "select * from ADMIN.DBM_USER where USERNAME=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, usn);
			ResultSet rs = ps.executeQuery();
			if(!rs.next())//if username doesn't exist
				return null;
			ArrayList<Object> user = new ArrayList<Object>();
			user.add(rs.getString(1));
			user.add(rs.getString(2));
			user.add(rs.getString(3));
			user.add(rs.getString(4));
			user.add(rs.getString(5));
			return user;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String[] getAllProducts(){
		
		int i = 0;
		int n = 0;
		String array[] = null;
		checker = new String[7];
		String sql = "select PNAME, CATEGORY from ADMIN.PRODUCT order by CATEGORY";
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				n++;
			}
			array = new String[n];
			rs.beforeFirst();
			String category="";
			int x=0;
			int m=0;
			while(rs.next()){
				if(category == rs.getString("CATEGORY")){
					System.out.println("hoola");
						checker[x] = array[m];
					x++;
				}
				category = rs.getString("CATEGORY");
				array[i] = rs.getString("PNAME");
				m=i;
				i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return array;
	}
	
	public String[] getAllDbmUser(){
		
		int i = 0;
		int n = 0;
		String array[] = null;
		String sql = "select USERNAME from ADMIN.DBM_USER";
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				n++;
			}
			array = new String[n];
			rs.beforeFirst();
			while(rs.next()){
				array[i] = rs.getString("USERNAME");
				i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return array;
	}
}
