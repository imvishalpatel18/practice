package com.company;

/**
 * An immutable class, having one public constructor to initialize all of its fields, final class and only have getter
 * methods for its fields.
 * @author VISHAL
 *
 */
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