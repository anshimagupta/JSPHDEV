package adapter;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.Set;

import utility.ReadFile;
import model.*;

public abstract class ProxyAutoMobile implements Serializable {
	/**
	 * Add generated serial version id
	 */
	private static final long serialVersionUID = -4263531946085598548L;
	private static LinkedHashMap<String, Automobile> auto_shop = new LinkedHashMap<String, Automobile>();
	
	
	public static LinkedHashMap<String, Automobile> getAuto_shop() {
		return auto_shop;
	}

	public void buildAuto(String modelName, String fileName){
		ReadFile reader = new ReadFile();
		auto_shop.put(modelName, reader.buildAutoObject(fileName));
	}
	
	public void printAuto(String modelName){
		System.out.println(auto_shop.get(modelName).print());
	}
	
	public void updateOptionSetName(String modelName, String opsetName, String newName){
		auto_shop.get(modelName).updateOpsetName(opsetName, newName);
	}
	
	public void updateOptionPrice(String modelName, String opsetName, String optName, float newPrice){
		auto_shop.get(modelName).updateOpt(opsetName, optName, newPrice);
	}
	
	public void setOptionChoice(String modelName, String opsetName, String optName){
		auto_shop.get(modelName).setOptionChoice(opsetName, optName);
	}
	
	public String getOptionChoice(String modelName, String opsetName){
		return auto_shop.get(modelName).getOptionChoice(opsetName);
	}
	
	public float getOptionChoicePrice(String modelName, String opsetName){
		return auto_shop.get(modelName).getOptionChoicePrice(opsetName);
	}
	
	/**
	 * The function for editing option set names: 
	 * The program first asks user for option set name that to be edited.
	 * Then it asks user to input new option set name.
	 * Finally it prints new option set names after the edit operation
	 * If the option set name to be edited does not exist, an exception will be thrown.
	 */
	public void editOpsetName(String modelName){
		synchronized(auto_shop.get(modelName)){
			Thread currThread = Thread.currentThread();
			System.out.println("\n===============================");
			System.out.println("Test of multithreading - edit opset/option");
			System.out.println("Current running thread is:" + currThread.getName());
			
			Iterator<String> iter = auto_shop.get(modelName).getOpsetList().keySet().iterator();
			System.out.println("");
			while(iter.hasNext()){
				System.out.println(iter.next());
			}

			System.out.println("\nPlease enter the option set name you want to edit:");
			Scanner sc_old = new Scanner(System.in);
			String oldOpsetName = sc_old.nextLine();
			
			System.out.println("\nPlease enter the new option set name:");
			Scanner sc_new = new Scanner(System.in);
			String newOpsetName = sc_new.nextLine();
			auto_shop.get(modelName).updateOpsetName(oldOpsetName, newOpsetName);
			
			Iterator<String> iter_1 = auto_shop.get(modelName).getOpsetList().keySet().iterator();
			System.out.println("\n** Current option set names are:");
			while(iter_1.hasNext()){
				System.out.println(iter_1.next());
			}
		}
	}
	
	/**
	 * Function for editing options:
	 * The program first asks user for option set name that to be edited.
	 * Then it asks user for option name that to be edited. 
	 * Then it asks user to input new option name and price.
	 * Finally it prints new automobile information after the edit operation
	 */
	public void editOption(String modelName){
		synchronized(auto_shop.get(modelName)){
			Thread currThread = Thread.currentThread();
			System.out.println("\n===============================");
			System.out.println("Test of multithreading - edit opset/option");
			System.out.println("Current running thread is:" + currThread.getName());
			
			System.out.println("\nPlease enter the option set name you want to edit:");
			Scanner sc_opset = new Scanner(System.in);
			String opsetName = sc_opset.nextLine();

			System.out.println("\nPlease enter the option name you want to edit:");
			Scanner sc_optionName = new Scanner(System.in);
			String newOpsetName = sc_optionName.nextLine();
			
			System.out.println("\nPlease enter new option price:");
			Scanner sc_optionPrice = new Scanner(System.in);
			float newOpsetPrice = Float.parseFloat(sc_optionPrice.nextLine());
			auto_shop.get(modelName).updateOpt(opsetName, newOpsetName, newOpsetPrice);
			
			System.out.println("\nCurrent automobile information is:");
			System.out.println(auto_shop.get(modelName).print());
		}
	}
	
	public void buildCarModelOptions(String modelName, Automobile auto){
		auto_shop.put(modelName, auto);
	}
	
	public String printAllModelNames(){
		Iterator<String> iter = auto_shop.keySet().iterator();
		StringBuffer sB = new StringBuffer();
		while(iter.hasNext()){
			sB.append(iter.next());
			sB.append(";");	
		}
		return sB.toString();
	}
	
	public Automobile returnSelectedAuto(String modelName){
		Automobile auto = auto_shop.get(modelName);
		return auto;
	}
}
