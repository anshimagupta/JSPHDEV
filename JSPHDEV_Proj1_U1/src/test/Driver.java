package test;

import java.io.FileNotFoundException;
import java.io.IOException;

import utility.*;
import model.Automobile;

public class Driver {
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		String autoName = "Focus Wagon ZTW";
		float basePrice = 18445;
		int optionSetSize = 5;
		
		Automobile fordZTW = new Automobile(autoName, basePrice, optionSetSize);
		ReadFile reader = new ReadFile();
		fordZTW = reader.buildAutoObject("AutomobileOptions.txt", fordZTW);
		//System.out.print(fordZTW.print());
		
		FileIO serDe = new FileIO();
		serDe.serializeAuto(fordZTW, "auto.ser");
		Automobile newFordZTW = serDe.deserializeAuto("auto.ser");
		System.out.print(newFordZTW.print());
	}
}
