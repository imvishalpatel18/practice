/**
 * 
 */
package com.flights.dao;

import java.util.List;

import com.flights.model.FlightMst;

/**
 * @author Vishal
 */
public interface FlightMstDao {
	public List<FlightMst> listAllActiveRecords();
	public List<FlightMst> searchFlights(String source, String destination);
	// deletes data from FLIGHTMST (all data gets cleared)
	public void clearData();
	void add(FlightMst flightMst);
}
