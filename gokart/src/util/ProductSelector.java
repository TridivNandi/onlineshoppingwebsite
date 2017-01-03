package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import database.DBAccess;

public class ProductSelector {

	public ProductSelector() {
		// TODO Auto-generated constructor stub
	}
	
	private ArrayList<ArrayList<Object>> selectedProducts = new ArrayList<ArrayList<Object>>();

	public ArrayList<ArrayList<Object>> getSelectedProducts() {
		
		try {
			selectedProducts = generateSelectedProducts();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return selectedProducts;
	}

	public void setSelectedProducts(ArrayList<ArrayList<Object>> selectedProducts) {
		
	}
	
	/*private ArrayList<ArrayList<Object>> generateSelectedProducts() throws SQLException {
		
		Connection con = DBAccess.getDataSource()[0].getConnection();
		String sql = "select PNAME from ADMIN.PRODUCT";
		PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = ps.executeQuery();
		int length = 0;
		while(rs.next())
			length++;
		rs.beforeFirst();//rewinds cursor
		String names[] = new String[5];//5 products will be displayed
		//Randomly select 5 product names.
		for(int i = 0; i < 5; i++) {
			Random r = new Random();
			int index = r.nextInt(length);
			for(int j = 0; j <= index; j++)
				rs.next();
			names[i] = rs.getString(1);
			rs.beforeFirst();
		}
		
		//Retrieve product details for the 5 products from the database.
		for(String pname : names) {
			sql = "select PNAME,IMAGE,RATING,BASE_PRICE,DISCOUNT,STATUS from ADMIN.PRODUCT where PNAME=? and STATUS='In_Stock'";
			ps = con.prepareStatement(sql);
			ps.setString(1, pname);
			rs = ps.executeQuery();
			ArrayList<Object> product = new ArrayList<Object>();
			rs.next();
			product.add(rs.getString(1));
			product.add(rs.getString(2));
			String rating = rs.getString(3);
			product.add(rating.substring(0,rating.indexOf("|")));
			product.add(rs.getFloat(4));
			product.add(rs.getFloat(5));
			product.add(rs.getString(6));
			selectedProducts.add(product);
		}
		return selectedProducts;
	}*/
	
	private ArrayList<ArrayList<Object>> generateSelectedProducts() throws SQLException {
		
		Connection con = DBAccess.getDataSource()[0].getConnection();
		String sql = "select PNAME from ADMIN.PRODUCT";
		PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = ps.executeQuery();
		ArrayList<Object> allProducts = new ArrayList<Object>();
		while(rs.next()){
			allProducts.add(rs.getString("Pname"));
		}
		rs.beforeFirst();//rewinds cursor
		Collections.shuffle(allProducts);
		String names[] = new String[5];//5 products will be displayed
		//Randomly select 5 product names.
		for(int i = 0; i < 5; i++) {
			names[i] = (String)allProducts.get(i);
		}
		
		//Retrieve product details for the 5 products from the database.
		for(String pname : names) {
			sql = "select PNAME,IMAGE,RATING,BASE_PRICE,DISCOUNT,STATUS from ADMIN.PRODUCT where PNAME=? and STATUS='In_Stock'";
			ps = con.prepareStatement(sql);
			ps.setString(1, pname);
			rs = ps.executeQuery();
			ArrayList<Object> product = new ArrayList<Object>();
			rs.next();
			product.add(rs.getString(1));
			product.add(rs.getString(2));
			String rating = rs.getString(3);
			product.add(rating.substring(0,rating.indexOf("|")));
			product.add(rs.getFloat(4));
			product.add(rs.getFloat(5));
			product.add(rs.getString(6));
			selectedProducts.add(product);
		}
		return selectedProducts;
	}
	//Images retrieved from the database for the 5 products are written to files at the server side.
	/*private void writeImageToFile(Blob blob, int fileNamePos) throws SQLException {
		try {
			  
			long blobLength = blob.length();  
			int pos = 1;   // position is 1-based   
			byte[] bytes = blob.getBytes(pos,(int) blobLength);      
			FileOutputStream os = new FileOutputStream("/gokart/WebContent/images/selectedProducts/img" + fileNamePos + ".jpg");  
			os.write(bytes);
			os.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  

	}*/
	
	

}
