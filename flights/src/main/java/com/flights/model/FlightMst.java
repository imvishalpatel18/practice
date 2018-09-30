package com.flights.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GeneratorType;

/**
 * @author Vishal
 */
@Entity
@Table(name = "FLIGHTMST")
public class FlightMst implements Serializable, Comparable<FlightMst>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2434208525404666658L;

	// create table FLIGHTMST (FL_ID integer AUTO_INCREMENT, FL_DESTINATION varchar(200), FL_ARR_TIME timestamp, FL_DEP_TIME timestamp, FL_PRICE double, FL_ORIGIN varchar(200), FL_PROVIDER varchar(200), primary key (FL_ID))
	
	@Id
	@Column(name = "FL_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer flightId;
	
	@Column(name = "FL_ORIGIN", length=200)
	private String origin;
	
	@Column(name = "FL_DESTINATION", length=200)
	private String destination;

	@Column(name = "FL_PROVIDER", length=200)
	private String provider;
	
	@Column(name = "FL_PRICE")
	private Double flightPrice;

	@Column(name = "FL_DEP_TIME")
	private Date flightDepTime;
	
	@Column(name = "FL_ARR_TIME")
	private Date flightArrTime;

	public Integer getFlightId() {
		return flightId;
	}

	public void setFlightId(Integer flightId) {
		this.flightId = flightId;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public Double getFlightPrice() {
		return flightPrice;
	}

	public void setFlightPrice(Double flightPrice) {
		this.flightPrice = flightPrice;
	}

	public Date getFlightDepTime() {
		return flightDepTime;
	}

	public void setFlightDepTime(Date flightDepTime) {
		this.flightDepTime = flightDepTime;
	}

	public Date getFlightArrTime() {
		return flightArrTime;
	}

	public void setFlightArrTime(Date flightArrTime) {
		this.flightArrTime = flightArrTime;
	}

	@Override
	public String toString() {
		return "FlightMst [flightId=" + flightId + ", origin=" + origin
				+ ", destination=" + destination + ", provider=" + provider
				+ ", flightPrice=" + flightPrice + ", flightDepTime="
				+ flightDepTime + ", flightArrTime=" + flightArrTime + "]";
	}

	@Override
	public int compareTo(FlightMst otherFlight) {
		if (this.getFlightPrice() < otherFlight.getFlightPrice()) return -1;
		if (this.getFlightDepTime().before(otherFlight.getFlightDepTime())) return -1;
		
		return 1;
	}
}
