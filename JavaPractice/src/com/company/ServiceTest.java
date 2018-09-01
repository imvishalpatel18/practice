package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ServiceTest {

	/**
	 * create an immutable class, Vehicle, create a list and filter list based on some field like color <br>
	 * For this create a separate service class.
	 * @param args
	 */
	public static void main(String[] args) {
		Service service = new Service();
		List<Vehicle> vehicles = new ArrayList<>();
		vehicles.add(new Vehicle("Toyota", "White"));
		vehicles.add(new Vehicle("Toyota", "Black"));
		vehicles.add(new Vehicle("BMW", "White"));
		vehicles.add(new Vehicle("White", "BMW"));
		vehicles.add(new Vehicle("Honda", "Blue"));
		List<Vehicle> filtered = service.getByColor(vehicles, "White");
		System.out.println(Arrays.toString(filtered.toArray()));
	}
}
