package test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ClientDriver {
	public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, IOException{
		/**
		 * Unit1: test for customized exception handling
		 * 
		 */
		//Driver_U1 driver_u1 = new Driver_U1();
		//driver_u1.startTest();
		
		/**
		 * Unit2: test for customized exception handling
		 * 
		 */
		Driver_U2 driver_u2 = new Driver_U2();
		driver_u2.startTest();

		
		/**
		 * Unit3: test for multi-threading
		 * 
		 */
		//Driver_U3 driver_u3 = new Driver_U3();
		//driver_u3.startTest();
		
		/**
		 * Unit4 & 5: Socket communication - client side
		 * Enable server-client socket connection
		 */
		//Driver_U4 driver_u4 = new Driver_U4();
		//driver_u4.startTest();
		
		/**
		 * Unit 6 testing cases are included in server project
		 */
	}
}