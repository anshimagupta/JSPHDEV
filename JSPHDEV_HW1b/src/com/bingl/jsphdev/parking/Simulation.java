package com.bingl.jsphdev.parking;

public class Simulation {
	// Show the ticket if there is an illegally parked car, 
	// otherwise, no ticket will be printed.
	public static void printToConsole(ParkingTicket ticket){
		if (ticket != null){
			System.out.println("Below ticket has been issued for the illegally parked car:");
			ticket.printTicket();
		} else{
			System.out.println("Legal parking, no ticket will be issued.");
		}
	}
	
	public void run() {
		ParkedCar testCar = new ParkedCar();
		ParkingMeter testMeter = new ParkingMeter();
		PoliceOfficer testOfficer = new PoliceOfficer();
		
		testCar.setMake("Toyota");
		testCar.setModel("Camry");
		testCar.setColor("Black");
		testCar.setLicenseNumber("S12345");
		testOfficer.setName("John Smith");
		testOfficer.setBadgeNumber("PO007");
		
		// Test the program with different part time and purchased time
		// Test for: ParkedVehicle is within the parking time purchased
		System.out.println("Test for: ParkedVehicle is within the parking time purchased");
		System.out.println("parked time = 60min and purchased time = 100min");
		testCar.setParkedMinutes(60);
		testMeter.setParkingMinutesPurchased(100);
		ParkingTicket testTicket1 = testOfficer.issueTicket(testCar, testMeter);
		printToConsole(testTicket1);
		
		// Test for: ParkedVehicle is just in (boundary condition) the parking time purchased.
		System.out.println("\nTest for: ParkedVehicle is just in (boundary condition 1) the parking time purchased");
		System.out.println("parked time = 100min and purchased time = 100min");
		testCar.setParkedMinutes(100);
		testMeter.setParkingMinutesPurchased(100);
		ParkingTicket testTicket2 = testOfficer.issueTicket(testCar, testMeter);
		printToConsole(testTicket2);
		
		// Test for: ParkedVehicle is out of the parking time purchased.
		System.out.println("\nTest for: ParkedVehicle is out of the parking time purchased");
		System.out.println("Test 3 with parked time = 120min and purchased time = 100min");
		testCar.setParkedMinutes(120);
		testMeter.setParkingMinutesPurchased(100);
		ParkingTicket testTicket3 = testOfficer.issueTicket(testCar, testMeter);
		printToConsole(testTicket3);
		
		// Test for: Boundary condition - exact out of purchased time for 1 hour 
		System.out.println("\nTest for: Boundary condition 2 - exact out of purchased time for 1 hour");
		System.out.println("parked time = 160min and purchased time = 100min");
		testCar.setParkedMinutes(160);
		testMeter.setParkingMinutesPurchased(100);
		ParkingTicket testTicket4 = testOfficer.issueTicket(testCar, testMeter);
		printToConsole(testTicket4);
		
		// Test for: out of purchased time for more than 1 hour and less than 2 hours
		System.out.println("\nTest for: out of purchased time for more than 1 hour and less than 2 hours");
		System.out.println("parked time = 200min and purchased time = 100min");
		testCar.setParkedMinutes(180);
		testMeter.setParkingMinutesPurchased(100);
		ParkingTicket testTicket5 = testOfficer.issueTicket(testCar, testMeter);
		printToConsole(testTicket5);
	}
}
