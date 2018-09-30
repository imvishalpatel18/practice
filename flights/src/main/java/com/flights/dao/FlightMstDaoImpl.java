/**
 * 
 */
package com.flights.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.flights.model.FlightMst;
import com.flights.util.Status;

/**
 * @author Vishal
 */
@Repository
public class FlightMstDaoImpl implements FlightMstDao {

	static final Logger logger = Logger.getLogger(FlightMstDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	List<FlightMst> data = Collections.synchronizedList(new ArrayList<FlightMst>());
	
	FlightRepository repo = new FlightRepository();
	
	@SuppressWarnings("unchecked")
	@Override
	public List<FlightMst> listAllActiveRecords() {
		logger.info("Getting listAllActiveRecords from DBPropertyMstDaoImpl");
		return null;
	}
	
	@Override
	@Transactional
	public void clearData() {
		String hql = "DELETE FROM " + FlightMst.class.getSimpleName();
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		int countDeleted = query.executeUpdate();
		logger.debug(countDeleted + " records deleted from FLIGHTMST");
	}
	
	private class FlightRepository {
		
		int defaultCapacity = 100;
		
		// collection container to store flights
		Map<Integer, List<FlightMst>> flights;
		
		// constructor
		public FlightRepository() {
			this.flights = new HashedMap();
		}
		
		private Integer generateIndex(FlightMst flight) {
			String source = flight.getOrigin();
			String destination = flight.getDestination();
			return this.generateIndex(source, destination);
		}
		private Integer generateIndex(String source, String destination) {
			String stringIndex = source.concat("-").concat(destination);
			Integer tempindex = stringIndex.hashCode();
			Integer index = tempindex % defaultCapacity;
			return index;
		}
		
		// store flights with key as a number generated from String hashcode SOURCE-DEST city
		public void addFlight(FlightMst flight) {
			
			/// generate index from source and destination of flight
			Integer index = this.generateIndex(flight);
			
			// store it in collection
			if (!this.flights.containsKey(index)) {
				this.flights.put(index, new ArrayList<>());
			}
			this.flights.get(index).add(flight);
		}
		public List<FlightMst> searchFlights(String source, String destination) {
			Integer index = this.generateIndex(source, destination);
			List<FlightMst> flights = this.flights.get(index);
			return flights.stream().filter(x-> x.getOrigin().equals(source) && x.getDestination().equals(destination))
				.collect(Collectors.toList());
		}
	}
	
	@Override
	@Transactional
	public List<FlightMst> searchFlights(String source, String destination) {
		
		logger.info("search flight");
		List<FlightMst> result = repo.searchFlights(source, destination);
		logger.info("result from repo " + result.size());
		
		return data.stream().filter(f -> f.getOrigin().equals(source) && f.getDestination().equals(destination))
				
				.collect(Collectors.toList());
		 // .sorted(Comparator)
		
//		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(FlightMst.class);
//		criteria.add(Restrictions.eq("origin", source));
//		criteria.add(Restrictions.eq("destination", destination));
//		criteria.addOrder(Order.asc("flightPrice"));
//		criteria.addOrder(Order.asc("flightDepTime"));
//		return criteria.list();
	}
		
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public void add(FlightMst flightMst) {
		logger.info("start add new entry");
		sessionFactory.getCurrentSession().save(flightMst);
		data.add(flightMst);
		repo.addFlight(flightMst);
		logger.info("end add new entry");
	}

}
