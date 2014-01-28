package com.bingl.jsphdev.parking;

public class PoliceOfficer {
	private String name;
	private String badgeNumber;
	private double overtimeHour;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBadgeNumber() {
		return badgeNumber;
	}
	public void setBadgeNumber(String badgeNumber) {
		this.badgeNumber = badgeNumber;
	}
	
	public ParkingTicket issueTicket(ParkedCar car, ParkingMeter parkingMeter){
		if (car.getParkedMinutes() > parkingMeter.getParkingMinutesPurchased()){
			overtimeHour = Math.ceil((car.getParkedMinutes() - parkingMeter.getParkingMinutesPurchased())/60.0);
			ParkingTicket newTicket = new ParkingTicket(this, car, overtimeHour);
			return newTicket;
		} else {
			return null;
		}
	}
}	
