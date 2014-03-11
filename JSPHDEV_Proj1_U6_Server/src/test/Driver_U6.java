package test;

import dbHandler.DBCreateSchema;
import dbHandler.DBCreateTable;

import adapter.BuildAuto;

public class Driver_U6 {
	public void startTest(){
		String serverName = "localhost";
		String portNumber = "3306";
		String dbName = "JSPHDEV_Auto1";

		String username = "JSPHDEV";
		String password = "JSPHDEV";
		
		System.out.println("**UNIT 6 SERVER TESTING**");
		System.out.println("========================");
		
		/**
		 * Create database schema by reading sql from file
		 */
		DBCreateSchema cs = new DBCreateSchema(serverName, portNumber, username, password);
		cs.createSchemaFromFile("sql/create_schema.sql");
		System.out.println("\n");
		
		/**
		 * Create database tables by reading sql from file
		 */
		DBCreateTable ct = new DBCreateTable(serverName, portNumber, dbName, username, password);
		ct.createTableFromFile("sql/model.sql");
		ct.createTableFromFile("sql/opset.sql");
		ct.createTableFromFile("sql/model_opset.sql");
		ct.createTableFromFile("sql/opt.sql");
		ct.createTableFromFile("sql/opset_opt.sql");
		System.out.println("\n");
		
		/**
		 * Below is the demonstration of persistent changes in static LishHashMap object and data in DB
		 * All CREATE, DELETE, and UPDATE operation in DB are implemented with interfaces
		 * 
		 * Creating two models ("Focus Wagon ZTW" and "Prius") to the static LinkedHashMap object
		 * Auto information stored in MySQL database updated accordingly
		 */
		BuildAuto testAutoShop = new BuildAuto();
		testAutoShop.buildAuto("Focus Wagon ZTW", "Focus_Wagon_ZTW.txt");
		testAutoShop.buildAuto("Prius", "Prius.txt");
		System.out.println("\n");
		System.out.println("=====PRINT [Focus Wagon ZTW]=====");
		testAutoShop.printAuto("Focus Wagon ZTW");
		System.out.println("\n");
		
		/**
		 * Deleting one models ("Prius") from the static LinkedHashMap object
		 * Auto information stored in MySQL database updated accordingly
		 */
		testAutoShop.deleteAuto("Prius");
		System.out.println("\n");
		
		/**
		 * Updating one option price from the static LinkedHashMap object
		 * Auto information stored in MySQL database updated accordingly
		 */
		testAutoShop.updateOptionPrice("Focus Wagon ZTW", "Color", "Fort Knox Gold Clearcoat Metallic", 555);
		System.out.println("\n");
		System.out.println("=====PRINT UPDATED [Focus Wagon ZTW]=====");
		testAutoShop.printAuto("Focus Wagon ZTW");
	}
}
