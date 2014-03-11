package dbHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

public class DBOpenConnection {
	protected String serverName = null;
	protected String portNumber = null;
	protected String dbName = null;
	protected String uri = null;

	protected String username = null;
	protected String password = null;

	protected String driverName = "com.mysql.jdbc.Driver";
	protected Connection connection = null;
	protected Statement statement = null; 
	protected ResultSet rs = null;
	
	public boolean doConnection() {
		try {
			// Load the JDBC driver
			Class.forName(driverName);
			// Create a connection to the database
			connection = DriverManager.getConnection(uri, username, password);

		} catch (ClassNotFoundException e) {
			// Could not find the database driver
			System.out.println("ClassNotFoundException : " + e.getMessage());
			return false;
		} catch (SQLException e) {
			// Could not connect to the database
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}
}
