package database;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBAccess {
	private static DataSource[] dataSource = new DataSource[3];
	
	static {
		try {
			Context initCtx = new InitialContext();
			//Context envCtx = (Context) initCtx.lookup("java:comp/env");
			dataSource[0] = (DataSource)initCtx.lookup("java:comp/env/jdbc/CustomerDataSource");
			//dataSource[1] = (DataSource)envCtx.lookup("jdbc/DbmUserDataSource");
			//dataSource[2] = (DataSource)envCtx.lookup("jdbc/AdminDataSource");
		}
		catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public static DataSource[] getDataSource() {
		return dataSource;
	}
	
	public static void setDataSource() {
	
	}
}
