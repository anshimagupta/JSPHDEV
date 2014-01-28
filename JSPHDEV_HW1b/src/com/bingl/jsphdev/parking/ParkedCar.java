package com.bingl.jsphdev.parking;

public class ParkedCar {
	private String make;
	private String model;
	private String color;
	private String licenseNumber;
	private double parkedMinutes;
	
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getLicenseNumber() {
		return licenseNumber;
	}
	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}
	public double getParkedMinutes() {
		return parkedMinutes;
	}
	public void setParkedMinutes(double parkedMinutes) {
		this.parkedMinutes = parkedMinutes;
	}
}
