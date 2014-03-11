package test;

import server.AutoServer;
import server.AutoServerSocket;

import adapter.BuildAuto;

public class Driver_U4 {
	public void startTest(){
		System.out.println("**UNIT 4 SERVER TESTING**");
		System.out.println("========================");
		
		/**
		 * The server socket responses to request from the client in terms of:
		 * 1. Adding new models
		 * 2. Retrieve models added
		 */
		
		AutoServer testAutoShop = new BuildAuto();
		AutoServerSocket serverSocket = new AutoServerSocket(5555);
		serverSocket.start();
	}
}
