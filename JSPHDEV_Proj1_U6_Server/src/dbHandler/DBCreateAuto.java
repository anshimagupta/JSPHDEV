package dbHandler;

import java.sql.*;
import java.util.Iterator;

import model.Automobile;

import com.mysql.jdbc.Statement;

public class DBCreateAuto extends DBOpenConnection {	
	public DBCreateAuto(String host, String port, String db, String username, String password) {
		this.serverName = host;
		this.portNumber = port;
		this.dbName = db;
		this.username = username;
		this.password = password;
		this.uri = "jdbc:mysql://" + serverName + ":" + portNumber + "/" + dbName;
	}
	
	public void createAuto(Automobile auto){
		String query = null;
		if (doConnection()){
	        try {
	        	String model = auto.getModel();
	        	String make = auto.getMake();
	        	float basePrice = auto.getBaseprice();
	        	String values = "'" + model + "','" + make + "'," + basePrice;
	        	statement = (Statement) connection.createStatement();
				/**
				 * Check whether model with same exists before inserting
				 */
	        	query = "SELECT model FROM model WHERE model = '" + model + "'";
	        	rs = statement.executeQuery(query);
	        	if(rs.next()){
	        		System.err.println("Model '" + model + "' exists in system," +
	        				" no model with same name will be added. " +
	        				"Update the model if needed.");
	        	} else{
					/**
					 * Insert auto make, model, and base price into 'model' table
					 */
	        		query = "INSERT INTO model VALUES (" + values + ")";
					statement.executeUpdate(query);
					
					Iterator<String> iter = auto.getOpsetList().keySet().iterator();
					while(iter.hasNext()) {
						int opset_id = 0;
						String opsetName = iter.next();
						/**
						 * Insert opset name into 'opset' table
						 */
						query = "INSERT INTO opset (opset_name) VALUES ('" + opsetName + "')";
						statement.executeUpdate(query);
						/**
						 * Insert model name and opset_id into 'model_opset' table
						 */
						query = "SELECT opset_id FROM opset WHERE opset_name = '" + opsetName + "' ORDER BY opset_id DESC LIMIT 1";
						rs = statement.executeQuery(query);
						if(rs.next()){
							opset_id = Integer.parseInt(rs.getString("opset_id"));
						}
						values = "'" + model + "'," + opset_id;
						query = "INSERT INTO model_opset VALUES (" + values + ")";
						statement.executeUpdate(query);
						
						Iterator<String> optIter = auto.getOptions(opsetName).keySet().iterator();
						while(optIter.hasNext()) {
							int opt_id = 0;
							String optName = optIter.next();
							Float optPrice = auto.getOptions(opsetName).get(optName);
							/**
							 * Insert option name and option price into 'opt' table
							 */
							values = "'" + optName + "'," + optPrice;
							query = "INSERT INTO opt (opt_name, opt_price) VALUES (" + values + ")";
							statement.executeUpdate(query);
							/**
							 * Insert opset_id and option_id into 'opset_option' table
							 */
							query = "SELECT opt_id FROM opt WHERE opt_name = '" + optName + "' AND opt_price = '" + optPrice + "' ORDER BY opt_id DESC LIMIT 1";
							rs = statement.executeQuery(query);
							if(rs.next()){
								opt_id = Integer.parseInt(rs.getString("opt_id"));
							}
							rs.close();
							values = opset_id + "," + opt_id;
							query = "INSERT INTO opset_opt (opset_id, opt_id) VALUES (" + values + ")";
							statement.executeUpdate(query);
						}
					}	
					System.out.println("Model '" + model + "' was successfully added to DB.");
	        	}
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