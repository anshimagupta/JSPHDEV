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
		BuildAuto testAutoShop = new BuildAuto();
		testAutoShop.buildAuto("testAutoShop1", "AutomobileOptions.txt");
		System.out.println("");
		testAutoShop.printAuto("testAutoShop1");
	}
}