package tim3.spring.project.isamrs.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tim3.spring.project.comparator.AirlineComparatorAddress;
import tim3.spring.project.comparator.AirlineComparatorName;
import tim3.spring.project.isamrs.dto.AirlineDTO;
import tim3.spring.project.isamrs.model.Airline;
import tim3.spring.project.isamrs.service.AirlineService;

@RestController
public class AirlineController {
	
	@Autowired
	AirlineService airlineService;

	@RequestMapping(value="/getAllAirlines",method = RequestMethod.GET)
	public ResponseEntity<List<Airline>> getAllAirlines() {
		List<Airline> airlines = airlineService.getAll();
		return new ResponseEntity<>(airlines, HttpStatus.OK); 
	}
	
	@RequestMapping(value="/createAirline",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Airline> create(@RequestBody AirlineDTO airlineDTO) {
		Airline retVal = airlineService.create(new Airline(airlineDTO));
		return new ResponseEntity<>(retVal, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/findAirline", method = RequestMethod.GET,  produces =MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Airline> findAirline(){
		Airline airline = airlineService.getOne(1);
		if (airline != null) {
			return new ResponseEntity<>(airline, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value="/saveChangesAirline", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces =MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Airline> saveChangesAirline(@RequestBody Airline airline){
		Airline a = airlineService.getOne(airline.getId());
		if (a==null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		a.setId(airline.getId());
		a.setName(airline.getName());
		a.setAddress(airline.getAddress());
		a.setPromotionalDescription(airline.getPromotionalDescription());
		a.setFlights(airline.getFlights());
		a.setDestinations(airline.getDestinations());
		a.setAirplanes(airline.getAirplanes());
		a.setAirlineServices(airline.getAirlineCustomerServices());
		a.setQuickBookingTickets(airline.getQuickBookingTickets());
		
		
		Airline air = airlineService.save(a);
		return new ResponseEntity<>(air, HttpStatus.OK);
	}
	
	@RequestMapping(value="/showAirlines/{criteria}", method = RequestMethod.GET)
	public ResponseEntity<List<Airline>> showAirlines(@PathVariable String criteria) {
		System.out.println("Show airlines pozvano");
		List<Airline> airlines=airlineService.getAll();
		//List<Hotel> cars = hotelService.getAll();
		/*
		 * List<Rentacar> rentACars = new ArrayList<Rentacar>(); Rentacar h1=new
		 * Rentacar("ANameOne", "CAddressOne", "CPromoDesOne",5.0, null,null, null);
		 * Rentacar h2=new Rentacar("BNameTwo", "BAddressTwo", "CPromoDesTwo",4.0,
		 * null,null, null); Rentacar h3=new Rentacar("CNameThree", "AAddressThree",
		 * "CPromoDesThree",3.0, null,null, null); rentACars.add(h1); rentACars.add(h2);
		 * rentACars.add(h3);
		 */
		if (criteria.equals("sortByNameAirlines")) {
			Collections.sort(airlines, new AirlineComparatorName());
		}else if (criteria.equals("sortByAddressAirlines")) {
			Collections.sort(airlines, new AirlineComparatorAddress());
		}
		return new ResponseEntity<>(airlines, HttpStatus.OK); 
	}
	
	@RequestMapping(value="/findAirlines/{field}", method = RequestMethod.GET)
	public ResponseEntity<List<Airline>> findAirlines(@PathVariable String field) {
		System.out.println("Find airlines pozvano");
		//List<Hotel> cars = hotelService.getAll();
		List<Airline> airlines = new ArrayList<Airline>();
		airlines = (List<Airline>) airlineService.findByName(field);
		if (airlines.size()==0) {
			airlines = (List<Airline>) airlineService.findByAddress(field);
		}
		/*
		 * Rentacar h1=new Rentacar("ANameOne", "CAddressOne", "CPromoDesOne",5.0,
		 * null,null, null); Rentacar h2=new Rentacar("BNameTwo", "BAddressTwo",
		 * "CPromoDesTwo",4.0, null,null, null); Rentacar h3=new Rentacar("CNameThree",
		 * "AAddressThree", "CPromoDesThree",3.0, null,null, null); rentACars.add(h1);
		 * rentACars.add(h2); rentACars.add(h3);
		 */
		
		return new ResponseEntity<>(airlines, HttpStatus.OK); 
	}
}
