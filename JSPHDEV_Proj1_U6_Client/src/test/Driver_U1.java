package test;

import java.io.FileNotFoundException;
import java.io.IOException;

import adapter.BuildAuto;

import utility.*;
import model.Automobile;

public class Driver_U1 {
	public void startTest() throws FileNotFoundException, IOException, ClassNotFoundException {
		Automobile fordZTW = new Automobile();
		ReadFile reader = new ReadFile();
		fordZTW = reader.buildAutoObject("AutomobileOptions.txt");
		System.out.print(fordZTW.print());
		
		//FileIO serDe = new FileIO();
		//serDe.serializeAuto(fordZTW, "auto.ser");
		//BuildAuto newFordZTW = serDe.deserializeAuto("auto.ser");
		//System.out.print(newFordZTW.print());
	}
}
