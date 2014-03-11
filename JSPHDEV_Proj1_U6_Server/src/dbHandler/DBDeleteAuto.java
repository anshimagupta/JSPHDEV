package dbHandler;

import java.sql.*;

import com.mysql.jdbc.Statement;

public class DBDeleteAuto extends DBOpenConnection{
	private Statement statement_model = null; 
	private Statement statement_opset = null; 
	private Statement statement_opt = null; 
	private ResultSet rs_opset = null;
	private ResultSet rs_opt = null;
	
	public DBDeleteAuto(String host, String port, String db, String username, String password) {
		this.serverName = host;
		this.portNumber = port;
		this.dbName = db;
		this.username = username;
		this.password = password;
		this.uri = "jdbc:mysql://" + serverName + ":" + portNumber + "/" + dbName;
	}
	
	public void deleteAuto(String modelName){
		String query = null;
		if (doConnection()){
	        try {
	        	statement_model = (Statement) connection.createStatement();
	        	statement_opset = (Statement) connection.createStatement();
	        	statement_opt = (Statement) connection.createStatement();
	        	query = "SELECT opset_id FROM model_opset WHERE model = '" + modelName + "'";
	        	rs_opset = statement_model.executeQuery(query);
	        	while (rs_opset.next()){
					int opset_id = Integer.parseInt(rs_opset.getString("opset_id"));
					query = "SELECT opt_id FROM opset_opt WHERE opset_id = " + opset_id;
					rs_opt = statement_opset.executeQuery(query);
					while (rs_opt.next()){
						int opt_id = Integer.parseInt(rs_opt.getString("opt_id"));
						query = "DELETE FROM opt WHERE opt_id = " + opt_id;
						statement_opt.executeUpdate(query);
						
						//query = "DELETE FROM opset_opt WHERE opt_id = " + opt_id;
						//statement_opt.executeUpdate(query);
					}
					rs_opt.close();
					query = "DELETE FROM opset WHERE opset_id = " + opset_id;
					statement_opset.executeUpdate(query);
					
					//query = "DELETE FROM model_opset WHERE opset_id = " + opset_id;
					//statement.executeUpdate(query);
				}
	        	rs_opset.close();
	        	query = "DELETE FROM model WHERE model = '" + modelName + "'";
	        	statement_model.executeUpdate(query);
	        	
	        	System.out.println("Model '" + modelName + "' was successfully deleted from DB.");
				statement_opset.close(); 
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				// finally block used to close resources
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