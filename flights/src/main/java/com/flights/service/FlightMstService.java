/**
 * 
 */
package com.flights.service;

import java.util.List;

import com.flights.model.FlightMst;

/**
 * @author Vishal
 */
public interface FlightMstService {

	public void loadData();
	
	public List<FlightMst> searchFlights(String source, String destination);
}
