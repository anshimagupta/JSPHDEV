package dbHandler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

import com.mysql.jdbc.Statement;

public class DBCreateTable extends DBOpenConnection{
	public DBCreateTable(String host, String port, String db, String username, String password) {
		this.serverName = host;
		this.portNumber = port;
		this.dbName = db;
		this.username = username;
		this.password = password;
		this.uri = "jdbc:mysql://" + serverName + ":" + portNumber + "/" + dbName;
	}
	
	public void createTableFromFile(String fileName){
		if (doConnection()){
	        try {
	        	BufferedReader br = new BufferedReader(new FileReader(fileName));
	        	StringBuilder sb = new StringBuilder();
	        	String line = br.readLine();
	        	while (line != null) {
	                sb.append(line);
	                sb.append("\n");
	                line = br.readLine();
	            }
	        	String query = sb.toString();
				statement = (Statement) connection.createStatement();
				statement.executeUpdate(query);
				System.out.println("Table created successfully with file " + fileName);
				statement.close(); 
			} catch (SQLException | IOException e) {
				e.printStackTrace();
			} finally{
				// finally block used to close resources
				try {
					if (statement != null)
						statement.close();
				} catch (SQLException se2) {
				}
				try {
					if (connection != null)
						connection.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
	}
}