package dbHandler;

import java.sql.*;

import com.mysql.jdbc.Statement;

public class DBUpdateOptionPrice extends DBOpenConnection{	
	public DBUpdateOptionPrice(String host, String port, String db, String username, String password) {
		this.serverName = host;
		this.portNumber = port;
		this.dbName = db;
		this.username = username;
		this.password = password;
		this.uri = "jdbc:mysql://" + serverName + ":" + portNumber + "/" + dbName;
	}
	
	public void updateOptionPrice(String modelName, String opsetName, String optName, float newPrice){
		if (doConnection()){
	        try {
	        	int opt_id = 0;
	        	statement = (Statement) connection.createStatement();
	        	String query = "SELECT opt.opt_id" +
			        			" FROM model, model_opset, opset, opset_opt, opt" +
			        			" WHERE model.model = model_opset.model" +
			        			" AND model_opset.opset_id = opset.opset_id" +
			        			" AND opset.opset_id = opset_opt.opset_id" +
			        			" AND opset_opt.opt_id = opt.opt_id" +
			        			" AND model.model = '" + modelName + "'" +
			        			" AND opset.opset_name = '" + opsetName + "'" +
			        			" AND opt.opt_name = '" + optName + "'";
	        	rs = statement.executeQuery(query);
	        	if(rs.next()){
	        		opt_id = Integer.parseInt(rs.getString("opt_id"));
	        	}
	        	
	        	query = "UPDATE opt SET opt_price = " + newPrice + " WHERE opt_id = " + opt_id;
	        	
	        	System.out.println("Option price has been changed to $" + newPrice + " in DB for:");
	        	System.out.println("Model: " + modelName);
	        	System.out.println("Option Set: " + opsetName);
	        	System.out.println("Option Name: " + optName);

				statement.close(); 
			} catch (SQLException e) {
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