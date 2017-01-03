package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DbmUser extends User {

	@Override
	public ArrayList<Object> getUserDetails() {
		// TODO Auto_generated method stub
		return userDetails;
	}

	public void setUserDetails(String username) {
		try {
			//Retrieve user info from the database and set the member variable.
			String sql = "select * from ADMIN.DBM_USER where USERNAME=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				for(int i = 1; i <= 5; i++)
					userDetails.add(rs.getString(i));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean create(ArrayList<Object> userDetails) {
		return false;
	}
	
	//add a product into the PRODUCT database.
	public boolean addProduct(ArrayList<Object> productDetails){
		
			String sql;
			try {
				PreparedStatement ps;
				sql="insert into ADMIN.PRODUCT values(?,?,?,?,?,?,?,?,?,?)";
		
				ps = con.prepareStatement(sql);
				ps.setString(1, (String)productDetails.get(0));
				ps.setString(2, (String)productDetails.get(1));
				// ps.setBlob(3, (Blob)productDetails.get(2));
				
				File f = new File((String) productDetails.get(2));
				String newFilePath = "./WebContent/images/products/" + (String)productDetails.get(0) + ".jpg";
				File newFile = new File(newFilePath);
				newFile.createNewFile();
				FileInputStream fis = new FileInputStream(f);
				FileOutputStream fos = new FileOutputStream(newFile);
				byte[] bytes = new byte[fis.available()];
				fis.read(bytes);
				fos.write(bytes);
				fis.close();
				fos.close();
				newFilePath = "images/products/" + (String)productDetails.get(0) + ".jpg";
				ps.setString(3, newFilePath);//image
				//f = (File)productDetails.get(3);
				//ps.setBinaryStream(4, new FileInputStream(f), (int)f.length());
	
				ps.setString(4, (String)productDetails.get(3));
				
				// ps.setDouble(5, (Double)productDetails.get(4));
				ps.setString(5, "0|0");//rating
				//ps.setClob(6, (Clob)productDetails.get(5));
				ps.setString(6, "");//review
				ps.setInt(7, Integer.parseInt((String)productDetails.get(6)));
				ps.setInt(8, Integer.parseInt((String)productDetails.get(7)));
				ps.setFloat(9, Float.parseFloat((String)productDetails.get(8)));
				ps.setString(10, (String)productDetails.get(9));
				ps.executeQuery();
			}
			catch(Exception e){
				return false;//Product name already exists
			}
			return true;
		
	}
	
	//Update an existing product.
	public boolean updateProduct(ArrayList<Object> productDetails) {
		String sql;
		try {
			PreparedStatement ps;
			sql="update ADMIN.PRODUCT set IMAGE=?, BRIEF_DESCRIPTION=?, QUANTITY=?, BASE_PRICE=?, DISCOUNT=?, STATUS=? where PNAME=?";
		
		    ps = con.prepareStatement(sql);
		    
			// ps.setBlob(3, (Blob)productDetails.get(2));
		    File f = new File((String) productDetails.get(2));
			String newFilePath = "./WebContent/images/products/" + (String)productDetails.get(0) + ".jpg";
			File newFile = new File(newFilePath);
			newFile.createNewFile();
			FileInputStream fis = new FileInputStream(f);
			FileOutputStream fos = new FileOutputStream(newFile);
			byte[] bytes = new byte[fis.available()];
			fis.read(bytes);
			fos.write(bytes);
			fis.close();
			fos.close();
			newFilePath = "images/products/" + (String)productDetails.get(0) + ".jpg";
			ps.setString(1, newFilePath);//image
			//ps.setClob(4, (Clob)productDetails.get(3));
			ps.setString(2, (String)productDetails.get(3));
			// ps.setDouble(5, (Double)productDetails.get(4));
			//ps.setClob(6, (Clob)productDetails.get(5));
			ps.setInt(3, Integer.parseInt((String)productDetails.get(6)));
			ps.setFloat(4, Float.parseFloat((String)productDetails.get(7)));
			ps.setFloat(5, Float.parseFloat((String)productDetails.get(8)));
			ps.setString(6, (String)productDetails.get(9));
			
			ps.setString(7, (String)productDetails.get(0));
			ps.executeUpdate();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return true;
	}
	
	//Remove an existing product.
	public boolean removeProduct(String name){
		
		String sql;
		try {
			PreparedStatement ps;
			sql = "delete from ADMIN.PRODUCT where PNAME=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.executeUpdate();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return true;
	}

}