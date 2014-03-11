package server;

import java.net.*;
import java.io.*;
import java.util.Date;

public class AutoServerSocket extends Thread {

	boolean DEBUG = true;
	private int iPort;

	public AutoServerSocket(int iPort) {
		setPort(iPort);
	}

	public void run() {
		startServer();
	}

	public void startServer() {
		boolean listening = true;
		//int clientCounter = 0;
		try (ServerSocket serverSocket = new ServerSocket(iPort)){
			while (listening){
				new AutoServerSocketThread(serverSocket.accept()).start();
				//clientCounter++;
				Date date = new Date();
				System.out.println("New client connection at " + date.toString());
			}
		} catch(IOException e) {
            System.err.println("Could not listen on port " + iPort);
            System.exit(-1);
		}
	}

	public void setPort(int iPort) {
		this.iPort = iPort;
	}
}
