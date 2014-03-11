package test;

import java.io.FileNotFoundException;
import java.io.IOException;
import utility.FileIO;

import adapter.BuildAuto;
import adapter.CreateAuto;
import adapter.UpdateAuto;

public class Driver_U2 {
	public void startTest() throws FileNotFoundException, IOException, ClassNotFoundException {
		System.out.println("**TEST FOR PART A**");
		// Test Case 1: test Option Set/Option update functions
		System.out.println("========================");
		System.out.println("Test Case 1: test Option Set/Option update functions");
		System.out.println("[Update] Option Set Name 'Side Impact Air Bags' --> 'Air Bags Protection'");
		System.out.println("[Update] Option Set 'Power Moonroof' Option 'selected' price --> $888");
		CreateAuto testCreateAuto = new BuildAuto();
		testCreateAuto.buildAuto("testAutoShop1", "AutomobileOptions.txt", "textFile");
		UpdateAuto testUpdateAuto = new BuildAuto();
		testUpdateAuto.updateOptionSetName("testAutoShop1", "Side Impact Air Bags", "Air Bags Protection");
		testUpdateAuto.updateOptionPrice("testAutoShop1", "Power Moonroof", "selected", 888);
		System.out.println("");
		testCreateAuto.printAuto("testAutoShop1");
		
		// Test Case 2: test exception - missing file name
		System.out.println("========================");
		System.out.println("Test Case 2: test exception - missing file name");
		testCreateAuto.buildAuto("testAutoShop2", "", "textFile");
		System.out.println("");
		testCreateAuto.printAuto("testAutoShop2");

		// Test Case 3: test exception - missing "Make" field
		System.out.println("========================");
		System.out.println("Test Case 3: test exception - missing 'Make' field");
		testCreateAuto.buildAuto("testAutoShop3", "AutomobileOptions_missing_Make.txt", "textFile");
		System.out.println("");
		testCreateAuto.printAuto("testAutoShop3");
		
		// Test Case 4: test exception - missing "Model" field
		System.out.println("========================");
		System.out.println("Test Case 4: test exception - missing 'Model' field");
		testCreateAuto.buildAuto("testAutoShop4", "AutomobileOptions_missing_Model.txt", "textFile");
		System.out.println("");
		testCreateAuto.printAuto("testAutoShop4");
		
		// Test Case 4: test exception - missing "Base Price" field
		System.out.println("========================");
		System.out.println("Test Case 5: test exception - missing 'Base Price' field");
		testCreateAuto.buildAuto("testAutoShop5", "AutomobileOptions_missing_BasePrice.txt", "textFile");
		System.out.println("");
		testCreateAuto.printAuto("testAutoShop5");
		
		// Test Case 6: test exception - missing Option values
		System.out.println("========================");
		System.out.println("Test Case 6: test exception - missing Option values");
		testCreateAuto.buildAuto("testAutoShop6", "AutomobileOptions_missing_OptionValue.txt", "textFile");
		System.out.println("");
		testCreateAuto.printAuto("testAutoShop6");
		
		
		System.out.println("\n**TEST FOR PART B**");
	    // Put auto 1 - Ford to the map
		testCreateAuto.buildAuto("Ford", "AutomobileOptions.txt", "textFile");
	    
	    // Put auto 2 - Toyota to the map
		testCreateAuto.buildAuto("Toyota", "ToyotaOptions.txt", "textFile");
		
		BuildAuto testAutoShop = new BuildAuto();
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