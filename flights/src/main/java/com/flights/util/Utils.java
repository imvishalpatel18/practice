package com.flights.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

/**
 * @author Vishal
 */
public class Utils {
	static final Logger logger = Logger.getLogger(Utils.class);

	private static Utils instance;
	
	private Utils() {
	}
	public static Utils getInstance() {
		if (Utils.instance == null) {
			Utils.instance = new Utils();
		}
		return Utils.instance;
	}

	public String format(Date date, String pattern) {
		DateFormat format = new SimpleDateFormat(pattern);
		return format.format(date);
	}

	public Date parse(String dateStr, String pattern) throws ParseException {
		DateFormat format = new SimpleDateFormat(pattern);
		return format.parse(dateStr);
	}
	
	public Double parseCurrency(String value){
		Double flightPrice = 0.0;
		String priceString = value.replace("$", "");
		flightPrice = Double.valueOf(priceString);
		return flightPrice;
	}
	
	public String formatCurrency(Double value){
		return "$".concat(String.valueOf(value));
	}

	public boolean isThisDateValid(String dateToValidate, String dateFromat) {

		if (dateToValidate == null
				|| dateToValidate.length() != dateFromat.length()) {
			return false;
		}

		SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);
		sdf.setLenient(false);

		try {
			// if not valid, it will throw ParseException
			Date date = sdf.parse(dateToValidate);

		} catch (ParseException e) {

			e.printStackTrace();
			return false;
		}

		return true;
	}
}