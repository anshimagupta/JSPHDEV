package server;

import java.net.*;
import java.util.Properties;
import java.io.*;

import model.Automobile;

import adapter.BuildAuto;

import utility.*;

public class AutoServerSocketThread extends Thread {

	boolean DEBUG = true;
	private BufferedReader reader;
	private PrintWriter writer;
	//private BufferedWriter writer;
	private ObjectInputStream objectInputStream;
	private ObjectOutputStream objectOutputStream;
	private String strHost;
	private int iPort;
	private ServerSocket serverSocket = null;
	private Socket clientSocket = null;
	private boolean addMore;
	private boolean configCar = true;
	private String selectedModelName;
	private AutoServer autoServer;

    private Socket socket = null;
    
	public AutoServerSocketThread(Socket socket) {
		//setPort(iPort);
        super("AutoServerSocketThread");
        this.socket = socket;
	}

	public void run() {
		System.out.println();
		establishConnection();
		receiveAddMoreMsg();
		while (addMore == true) {
			receivePropertyObject();
			sendResponse();
			receiveAddMoreMsg();
		}
		
		receiveConfigCar();
		if (configCar==true){
			sendAllModelNames();
			receiveSelectedModel();
			sendSelectedModel();
		}
		
		closeSession();
	}

	public void establishConnection() {
		try {
			objectInputStream = new ObjectInputStream(socket.getInputStream());
			objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer = new PrintWriter(socket.getOutputStream(), true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void receivePropertyObject() {
		try {
			Properties props = new Properties();
			props = (Properties) objectInputStream.readObject();
			ReadFile rd = new ReadFile();
			autoServer = new BuildAuto();
			autoServer.buildCarModelOptions(props.getProperty("CarModel"),
			rd.buildAutoFromPropertyObject(props));
		} catch (IOException | ClassNotFoundException e) {
			if (DEBUG)
				System.out.println("Handling session with localhost:" + iPort);
		}
	}

	public void sendResponse() {
		String strOutput = "[Server Confirmation]: The uploaded automobile options is successfully added!";
		writer.println(strOutput);
	}

	public void receiveAddMoreMsg() {
		String strInput = "";
		try {
			strInput = reader.readLine();
			switch (strInput) {
			case "1":
				addMore = true;
				break;
			case "2":
				addMore = false;
				break;
			}
		} catch (IOException e) {
			if (DEBUG)
				System.out.println("Handling session with " + strHost + ":" + iPort);
		}
	}
	
	public void receiveConfigCar() {
		String strInput = "";
		try {
			strInput = reader.readLine();
				switch (strInput) {
				case "1":
					configCar = true;
					break;
				case "2":
					configCar = false;
					break;
			}

		} catch (IOException e) {
			if (DEBUG)
				System.out.println("Handling session with " + strHost + ":" + iPort);
		}
	}
	
	public void sendAllModelNames() {
		autoServer = new BuildAuto();
		String strOutput = autoServer.printAllModelNames();
		writer.println(strOutput);
	}
	
	public void receiveSelectedModel() {
		String strInput = "";
		try {
			strInput = reader.readLine();
			selectedModelName = strInput;
		} catch (IOException e) {
			if (DEBUG)
				System.out.println("Handling session with " + strHost + ":" + iPort);
		}
	}
	
	public void sendSelectedModel() {
		Automobile selectedAuto = autoServer.returnSelectedAuto(selectedModelName);
		try {
			objectOutputStream.writeObject(selectedAuto);
		} catch (IOException e) {
			if (DEBUG)
				System.out.println("Error writing to " + strHost);
		}
	}

	public void closeSession() {
		try {
			writer = null;
			reader = null;
			objectInputStream.close();
			objectOutputStream.close();
			//serverSocket.close();
			socket.close();
		} catch (IOException e) {
			if (DEBUG)
				System.err.println("Error closing socket to " + strHost);
		}
	}

	public void setPort(int iPort) {
		this.iPort = iPort;
	}
}// class DefaultSocketClient
