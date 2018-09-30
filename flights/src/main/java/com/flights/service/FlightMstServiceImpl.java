package com.flights.service;


import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import antlr.Utils;

import com.flights.dao.FlightMstDao;
import com.flights.model.FlightMst;

@Service
public class FlightMstServiceImpl implements FlightMstService{

	static final Logger logger = Logger.getLogger(FlightMstServiceImpl.class);

	ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private FlightMstDao flightMstDao;
	
	private DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	private DateFormat dateFormat2 = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");

	/**
	 * reads a file and returns textual content from it
	 * @return
	 */
	private String readFile(String fileName) {
		
		try{
			InputStream inStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			IOUtils.copy(inStream, outStream);
			String allData = new String(outStream.toByteArray());
			return allData;
		}catch(Exception e){
			logger.info("Exception in load data",e);
			return "";
		}
	}
	
	/**
	 * called on startup of the application to read data from file and load in database
	 */
	@Transactional
	@Override
	public void loadData() {
		
		logger.debug("clearing the data");
		flightMstDao.clearData();
		
		logger.debug("loading all data");
		this.loadSingle("provider1-flights.txt", dateFormat, ",");
		this.loadSingle("provider2-flights.txt", dateFormat2, ",");
		this.loadSingle("provider3-flights.txt", dateFormat, "\\|");
	}

	private void loadSingle(String fileName, DateFormat dateFormat, String separator) {
		
		logger.debug("loading data from " + fileName);
		String provider1 = this.readFile(fileName);
		String[] fls1 = provider1.split("\n");
		for (String s : fls1) {
			System.out.println(s);
			// for provider 1 split with comma or pipe
			String[] tokens = s.split(separator);
			
			FlightMst flight = new FlightMst();
			flight.setOrigin(tokens[0]);
			try {
				flight.setFlightDepTime(dateFormat.parse(tokens[1]));
				flight.setFlightArrTime(dateFormat.parse(tokens[3]));
			} catch (ParseException e) {
				logger.debug(tokens[1]+" "+tokens[3]);
				logger.error("Date formation error",e);
			}
			flight.setDestination(tokens[2]);
			Double flightPrice = com.flights.util.Utils.getInstance().parseCurrency(tokens[4].split("\n")[0]);
			flight.setFlightPrice(flightPrice);
			
			flightMstDao.add(flight);
		}
	}
	
	@Override
	public List<FlightMst> searchFlights(String source, String destination) {
		return flightMstDao.searchFlights(source, destination);
	}
}
