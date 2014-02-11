package test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;

import model.Automobile;
import utility.FileIO;

import adapter.BuildAuto;

public class Driver {
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		System.out.println("**TEST FOR PART A**");
		// Test Case 1: test Option Set/Option update functions
		System.out.println("========================");
		System.out.println("Test Case 1: test Option Set/Option update functions");
		System.out.println("[Update] Option Set Name 'Side Impact Air Bags' --> 'Air Bags Protection'");
		System.out.println("[Update] Option Set 'Power Moonroof' Option 'selected' price --> $888");
		BuildAuto testAutoShop = new BuildAuto();
		testAutoShop.buildAuto("testAutoShop1", "AutomobileOptions.txt");
		testAutoShop.updateOptionSetName("testAutoShop1", "Side Impact Air Bags", "Air Bags Protection");
		testAutoShop.updateOptionPrice("testAutoShop1", "Power Moonroof", "selected", 888);
		System.out.println("");
		testAutoShop.printAuto("testAutoShop1");
		
		// Test Case 2: test exception - missing file name
		System.out.println("========================");
		System.out.println("Test Case 2: test exception - missing file name");
		testAutoShop.buildAuto("testAutoShop2", "");
		System.out.println("");
		testAutoShop.printAuto("testAutoShop2");

		// Test Case 3: test exception - missing "Make" field
		System.out.println("========================");
		System.out.println("Test Case 3: test exception - missing 'Make' field");
		testAutoShop.buildAuto("testAutoShop3", "AutomobileOptions_missing_Make.txt");
		System.out.println("");
		testAutoShop.printAuto("testAutoShop3");
		
		// Test Case 4: test exception - missing "Model" field
		System.out.println("========================");
		System.out.println("Test Case 4: test exception - missing 'Model' field");
		testAutoShop.buildAuto("testAutoShop4", "AutomobileOptions_missing_Model.txt");
		System.out.println("");
		testAutoShop.printAuto("testAutoShop4");
		
		// Test Case 4: test exception - missing "Base Price" field
		System.out.println("========================");
		System.out.println("Test Case 5: test exception - missing 'Base Price' field");
		testAutoShop.buildAuto("testAutoShop5", "AutomobileOptions_missing_BasePrice.txt");
		System.out.println("");
		testAutoShop.printAuto("testAutoShop5");
		
		// Test Case 6: test exception - missing Option values
		System.out.println("========================");
		System.out.println("Test Case 6: test exception - missing Option values");
		testAutoShop.buildAuto("testAutoShop6", "AutomobileOptions_missing_OptionValue.txt");
		System.out.println("");
		testAutoShop.printAuto("testAutoShop6");
		
		
		System.out.println("\n**TEST FOR PART B**");
	    // Put auto 1 - Ford to the map
		testAutoShop.buildAuto("Ford", "AutomobileOptions.txt");
	    
	    // Put auto 2 - Toyota to the map
		testAutoShop.buildAuto("Toyota", "ToyotaOptions.txt");
		
		testAutoShop.setOptionChoice("Ford", "Power Moonroof", "selected");
		System.out.println("Print selected option:");
		System.out.println(testAutoShop.getOptionChoice("Ford", "Power Moonroof"));
		System.out.println("Print selected option price:");
		System.out.println(testAutoShop.getOptionChoicePrice("Ford", "Power Moonroof"));
		
		System.out.println("\n========================");
		System.out.println("**Test of Serialization**");
		FileIO serDe = new FileIO();
		serDe.serializeAuto(testAutoShop, "testAutoShop.ser");
		BuildAuto loadedAutoShop = serDe.deserializeAuto("testAutoShop.ser");
		System.out.println("Print Toyota Options:");
		loadedAutoShop.printAuto("Toyota");
	}
}