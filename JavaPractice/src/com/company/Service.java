package com.company;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * One service class to implement filters on Vehicle class, there is one method to filter vehicles based on color
 * @author VISHAL
 *
 */
public class Service {

	// filter based on color
	public List<Vehicle> getByColor(List<Vehicle> vehicles, String color) {
		
		List<Vehicle> filtered = vehicles.stream().filter(x -> x.getColor().equals(color)).collect(Collectors.toList());
		
		// this is a sequancial stream
		// parallel stream 
		
		// HashMap , indexing based on key
		// color , indexing , 
		
		// 
		return filtered;
	}
	
}
