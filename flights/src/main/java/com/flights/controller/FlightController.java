package com.flights.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.flights.model.FlightMst;
import com.flights.service.FlightMstService;
import com.flights.service.FlightMstServiceImpl;
import com.flights.util.Utils;


@RestController
public class FlightController {
	static final Logger logger = Logger.getLogger(FlightController.class);
	
	@Autowired
	private FlightMstService flightMstService;
	
	@RequestMapping(value = "/loadData", method=RequestMethod.GET)
	public String loadData(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		logger.info("start loadData FlightController");
		
		flightMstService.loadData();
		
		logger.info("end loadData FlightController");
		return "SUCCESS";
	}
	
	@RequestMapping(value = "/s/{origin}/{destination}", method = RequestMethod.GET)
	public String searchFlights(@PathVariable("origin") String origin, @PathVariable("destination") String destination ) {
		
		List<FlightMst> flights = flightMstService.searchFlights(origin, destination);
		if (flights.isEmpty()) {
			return "No flights found for " + origin + " --> " + destination;
		}
		
		logger.info("Flights list size"+flights.size());
		StringBuffer bufferOut = new StringBuffer();
		for (FlightMst flight : flights) {
			bufferOut.append(flight.getOrigin() + " --> " + flight.getDestination())
				.append("(" + flight.getFlightDepTime() + " --> " + flight.getFlightArrTime() + ")")
				.append(" - " + Utils.getInstance().formatCurrency(flight.getFlightPrice()))
				.append("\n<br>");
		}
		logger.debug("output" + bufferOut);
		return bufferOut.toString();
		
	}

}
