package tim3.spring.project.isamrs.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tim3.spring.project.isamrs.comparator.AirlineComparatorAddress;
import tim3.spring.project.isamrs.comparator.AirlineComparatorName;
import tim3.spring.project.isamrs.dto.AirlineDTO;
import tim3.spring.project.isamrs.model.Airline;
import tim3.spring.project.isamrs.model.AirlineAdmin;
import tim3.spring.project.isamrs.model.AirlineWorkingDestinations;
import tim3.spring.project.isamrs.model.User;
import tim3.spring.project.isamrs.service.AirlineService;
import tim3.spring.project.isamrs.service.AirlineWorkingService;
import tim3.spring.project.isamrs.service.impl.CustomUserDetailsService;

@RestController
public class AirlineController {

	@Autowired
	AirlineService airlineService;
	
	@Autowired
	AirlineWorkingService airlineWorkingService;
	
	@Autowired
	private CustomUserDetailsService userDetailsService;

	@GetMapping(value = "/getAllAirlines")
	public ResponseEntity<List<Airline>> getAllAirlines() {
		List<Airline> airlines = airlineService.getAll();
		return new ResponseEntity<>(airlines, HttpStatus.OK);
	}

	@PostMapping(value = "/createAirline", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Airline> create(@RequestBody AirlineDTO airlineDTO) {
		Airline retVal = airlineService.create(new Airline(airlineDTO));
		return new ResponseEntity<>(retVal, HttpStatus.CREATED);
	}

	@GetMapping(value = "/findAirline", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Airline> findAirline() {
		Airline airline = airlineService.getOne(1);
		if (airline != null) {
			return new ResponseEntity<>(airline, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping(value = "/saveChangesAirline", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Airline> saveChangesAirline(@RequestBody Airline airline) {
		Airline a = airlineService.getOne(airline.getId());
		if (a == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		a.setId(airline.getId());
		a.setName(airline.getName());
		a.setAddress(airline.getAddress());
		a.setPromotionalDescription(airline.getPromotionalDescription());
		a.setFlights(airline.getFlights());
		a.setAirlineServices(airline.getAirlineCustomerServices());
		a.setQuickBookingTickets(airline.getQuickBookingTickets());

		Airline air = airlineService.save(a);
		return new ResponseEntity<>(air, HttpStatus.OK);
	}

	@GetMapping(value = "/showAirlines/{criteria}")
	public ResponseEntity<List<Airline>> showAirlines(@PathVariable String criteria) {
		List<Airline> airlines = airlineService.getAll();
		if (criteria.equals("sortByNameAirlines")) {
			Collections.sort(airlines, new AirlineComparatorName());
		} else if (criteria.equals("sortByAddressAirlines")) {
			Collections.sort(airlines, new AirlineComparatorAddress());
		}
		return new ResponseEntity<>(airlines, HttpStatus.OK);
	}

	@GetMapping(value = "/findAirlines/{field}")
	public ResponseEntity<List<Airline>> findAirlines(@PathVariable String field) {
		List<Airline> airlines = (List<Airline>) airlineService.findByName(field);
		if (airlines.size() == 0) {
			airlines = (List<Airline>) airlineService.findByAddress(field);
		}

		return new ResponseEntity<>(airlines, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getAllAirlinesExcept")
	@PreAuthorize("hasRole('ROLE_AIRLINE_ADMIN')")
	public ResponseEntity<List<Airline>> getAllAirlinesExcept() {
		User logged = (User) this.userDetailsService
				.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		Airline air=((AirlineAdmin) logged).getAirline();
		List<Airline> airlines = airlineService.getAll();
		List<AirlineWorkingDestinations> airlineWorkingDestinations=this.airlineWorkingService.findByAirlineThatWorks(air);
		List<Airline> back=new ArrayList<>();
		for(Airline a: airlines) {
			int i=0;
			for(AirlineWorkingDestinations awd: airlineWorkingDestinations) {
				if(awd.getWorksWith().getId()==a.getId()) {
					i=1;
				}
			}
			if(i==0) {
				back.add(a);
			}
			
		}
		
		
		return new ResponseEntity<>(back, HttpStatus.OK);
	}
	
	@PostMapping(value = "/addDestination/{id}")
	@PreAuthorize("hasRole('ROLE_AIRLINE_ADMIN')")
	public ResponseEntity<AirlineWorkingDestinations> addDestination(@PathVariable Long id) {
		User logged = (User) this.userDetailsService
				.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		Airline air=((AirlineAdmin) logged).getAirline();
		Airline work=this.airlineService.getOne(id);
		AirlineWorkingDestinations awd=this.airlineWorkingService.create(new AirlineWorkingDestinations(air,work));
		return new ResponseEntity<>(awd, HttpStatus.CREATED);
	}
	
	
	@GetMapping(value = "/getAirlineWorkingDestinations")
	@PreAuthorize("hasRole('ROLE_AIRLINE_ADMIN')")
	public ResponseEntity<List<AirlineWorkingDestinations>> getAllAirlineWorkingDestinations() {
		User logged = (User) this.userDetailsService
				.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		Airline air=((AirlineAdmin) logged).getAirline();
		List<AirlineWorkingDestinations> airlineWorkingDestinations=this.airlineWorkingService.findByAirlineThatWorks(air);
		return new ResponseEntity<>(airlineWorkingDestinations, HttpStatus.OK);
	}
}
