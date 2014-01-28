package com.bingl.jsphdev.parking;

public class ParkingTicket {
	private String carMake;
	private String carModel;
	private String carColor;
	private String illegalCarNumber;
	private double carFine = 0.0;
	private String policeOfficerName;
	private String policeOfficerNumber;
	
	public ParkingTicket(PoliceOfficer policeOfficer, ParkedCar car, double overtimeHour) {
		carMake = car.getMake();
		carModel = car.getModel();
		carColor = car.getColor();
		illegalCarNumber = car.getLicenseNumber();
		carFine = 25.00 + 10.00*(overtimeHour-1);
		policeOfficerName = policeOfficer.getName();
		policeOfficerNumber = policeOfficer.getBadgeNumber();
	}
	
	public void printTicket(){
		System.out.println("Car Make: " + carMake);
		System.out.println("Car Model: " + carModel);
		System.out.println("Car Color: " + carColor);
		System.out.println("Car License Number: " + illegalCarNumber);
		System.out.println("Car Fine ($): " + carFine);
		System.out.println("Police Officer Name: " + policeOfficerName);
		System.out.println("Police Officer Badge Number: " + policeOfficerNumber);
	}
}
