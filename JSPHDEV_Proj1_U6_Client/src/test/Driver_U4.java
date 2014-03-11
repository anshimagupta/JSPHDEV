package test;

import clinet.AutoClientSocket;

public class Driver_U4 {
	public void startTest(){
		System.out.println("**UNIT 4 CLIENT TESTING**");
		System.out.println("========================");
		
		/**
		 * The client socket responses to request from the users in terms of:
		 * 1. Uploading a new property file
		 * 2. Adding more property files
		 * 3. Configure a model
		 */
		
		AutoClientSocket clientSocket = new AutoClientSocket("localhost", 5555);
		clientSocket.run();
	}
}
