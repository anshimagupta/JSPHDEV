package test;

public class ServerDriver {
	public static void main(String[] args){
		/**
		 * Unit1-3 test cases are included in client project
		 */
		
		/**
		 * Unit4 & 5: Socket communication - server side
		 * Enable server-client socket connection
		 */
		//Driver_U4 driver_u4 = new Driver_U4();
		//driver_u4.startTest();
		
		/**
		 * Unit6 - Persistent CREATE, DELETE and UPDATE of LishHashMap object and DB
		 * Enable DB operation via JDBC connection
		 * Interface class is applied
		 * Create DB schema by reading SQL file
		 */
		Driver_U6 driver_u6 = new Driver_U6();
		driver_u6.startTest();
	}
}