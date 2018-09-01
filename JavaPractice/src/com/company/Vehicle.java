package com.company;

public final class Vehicle {
	private String brand;
	private String color;
	
	public Vehicle(String brand, String color) {
		this.brand = brand;
		this.color = color;
	}

	public String getBrand() {
		return brand;
	}

	public String getColor() {
		return color;
	}

	@Override
	public String toString() {
		return "Vehicle [brand=" + brand + ", color=" + color + "]";
	}
	
}