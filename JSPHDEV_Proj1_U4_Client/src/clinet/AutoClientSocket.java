package clinet;

import java.net.*;
import java.util.Properties;
import java.util.Scanner;
import java.io.*;

import model.Automobile;


public class AutoClientSocket extends Thread{

	boolean DEBUG = true;
	private BufferedReader reader;
	private PrintWriter writer;
	//private BufferedWriter writer;
	private ObjectInputStream objectInputStream;
	private ObjectOutputStream objectOutputStream;
	private Socket sock;
	private String strHost;
	private int iPort;
	private Boolean addMore;
	private Automobile auto;
	private String configOption;

	public AutoClientSocket(String strHost, int iPort) {
		setPort(iPort);
		setHost(strHost);
	}// constructor

	public void run() {
		Properties props = null;
		CarModelOptionsIO io = new CarModelOptionsIO();
		if (openConnection()) {
			/**
			 * Add models, Continue to add more models if user choose do so
			 */
			addMore = true;
			sendAddMore("0");
			while (addMore == true) {
				props = io.readPropertyFile();
				sendPropertyObject(props);
				receiveResponse();
				sendAddMore("0");
			}
			
			/**
			 * Allow user to select a model and configure it
			 */
			SelectCarOption selectCarOption = new SelectCarOption();
			configOption = selectCarOption.askConfiguration();
			
			sendConfigOption("0");
			if (configOption.equals("1")){
				receiveAllModelNames();
				sendSelectedModel();
				receiveSelectedModel();
				selectCarOption.configAuto(auto);
			}
			closeSession();
		}
		System.out.println("Simulation ends successfully!");
	}

	public boolean openConnection() {
		try {
			sock = new Socket(strHost, iPort);
		} catch (IOException socketError) {
			if (DEBUG)
				System.err.println("Unable to connect to " + strHost);
			return false;
		}
		try {
			objectOutputStream = new ObjectOutputStream(sock.getOutputStream());
			objectInputStream = new ObjectInputStream(sock.getInputStream());
			reader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			writer = new PrintWriter(sock.getOutputStream(), true);
		} catch (Exception e) {
			if (DEBUG)
				System.err.println("Unable to obtain stream to/from " + strHost);
			return false;
		}
		return true;
	}

	public void receiveResponse() {
		String strInput = "";
		try {
			strInput = reader.readLine();
			System.out.println(strInput);
		} catch (IOException e) {
			if (DEBUG)
				System.out.println("Handling session with " + strHost + ":" + iPort);
		}
	}

	public void sendPropertyObject(Properties props) {
		try {
			objectOutputStream.writeObject(props);
		} catch (IOException e) {
			if (DEBUG)
				System.out.println("Error writing to " + strHost);
		}
	}

	public void sendAddMore(String option) {
		String opt = option;
		if (opt.equalsIgnoreCase("0")){
			String strOutput = null;
			System.out.println("\nAdd more models?");
			System.out.println("1. Yes");
			System.out.println("2. No");
			Scanner addMore_sc = new Scanner(System.in);
			switch (addMore_sc.nextLine()) {
			case "1":
				strOutput = "1";
				addMore = true;
				break;
			case "2":
				strOutput = "2";
				addMore = false;
				break;
			}

			writer.println(strOutput);
		} else{
			writer.println(opt);
		}
		
	}
	
	public void sendConfigOption(String option) {
		String opt = option;
		if (opt.equalsIgnoreCase("0")){
			writer.println(configOption);
		} else{
			writer.println(opt);
		}
	}
	
	public void receiveAllModelNames() {
		String strInput = "";
		try {
			strInput = reader.readLine();
			System.out.println("All available models as below: ");
			System.out.println(strInput);
		} catch (IOException e) {
			System.out.println(e.toString());
		}
	}
	
	public void sendSelectedModel() {
		System.out.println("Please enter the model name your desired model name:");
		Scanner model_sc = new Scanner(System.in);
		String modelSelected = model_sc.nextLine();
		PrintWriter out = null;
		try {
			out = new PrintWriter(sock.getOutputStream(), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.println(modelSelected);
	}
	
	public void receiveSelectedModel() {
		try {
			auto = (Automobile) objectInputStream.readObject();
		} catch (IOException | ClassNotFoundException e) {
			System.out.println(e.toString());
		}
	}

	public void closeSession() {
		try {
			reader = null;
			writer = null;
			objectInputStream.close();
			objectOutputStream.close();
			sock.close();
		} catch (IOException e) {
			if (DEBUG)
				System.err.println("Error closing socket to " + strHost);
		}
	}

	public void setHost(String strHost) {
		this.strHost = strHost;
	}

	public void setPort(int iPort) {
		this.iPort = iPort;
	}
}
