package tim3.spring.project.isamrs.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
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
	
	@GetMapping(value = "/findConcreteAirline/{id}")
	public ResponseEntity<Airline> findConcreteAirline(@PathVariable String id) {
		Airline retVal = airlineService.getOne(Long.parseLong(id));
		return new ResponseEntity<>(retVal, HttpStatus.OK);
	}

	@GetMapping(value = "/getAllAirlines")
	public ResponseEntity<List<Airline>> getAllAirlines() {
		List<Airline> airlines = airlineService.getAll();
		return new ResponseEntity<>(airlines, HttpStatus.OK);
	}

	@GetMapping(value = "/getAirlinesWithoutAdmin")
	public ResponseEntity<List<Airline>> getAirlinesWithoutAdmin() {
		List<Airline> retVal = airlineService.getAll();
		for (Iterator<Airline> iterator = retVal.iterator(); iterator.hasNext();) {
			Airline airline = iterator.next();
			if (airline.getAirlineAdmin() != null) {
				iterator.remove();
			}
		}
		return new ResponseEntity<>(retVal, HttpStatus.OK);
	}

	@PostMapping(value = "/createAirline", consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_SYSTEM_ADMIN')")
	public ResponseEntity<Airline> create(@RequestBody AirlineDTO airlineDTO) {
		Airline retVal = airlineService.create(new Airline(airlineDTO));
		return new ResponseEntity<>(retVal, HttpStatus.CREATED);
	}

	@GetMapping(value = "/findAirline", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Airline> findAirline() {
		AirlineAdmin airlineAdmin = (AirlineAdmin) this.userDetailsService
				.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		Airline airline = airlineAdmin.getAirline();
		if (airline != null) {
			return new ResponseEntity<>(airline, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping(value = "/saveChangesAirline", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_AIRLINE_ADMIN')")
	public ResponseEntity<Airline> saveChangesAirline(@RequestBody AirlineDTO airline) {
		AirlineAdmin airlineAdmin = (AirlineAdmin) this.userDetailsService
				.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		Airline a = airlineAdmin.getAirline();
		if (a == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		a.setName(airline.getAirlineNameRegister());
		a.setAddress(airline.getAirlineAddressRegister());
		a.setPromotionalDescription(airline.getAirlinePromotionalDescription());

		airlineAdmin.setAirline(a);

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
		Airline air = ((AirlineAdmin) logged).getAirline();
		List<Airline> airlines = airlineService.getAll();
		List<AirlineWorkingDestinations> airlineWorkingDestinations = this.airlineWorkingService
				.findByAirlineThatWorks(air);
		List<Airline> back = new ArrayList<>();
		for (Airline a : airlines) {
			int i = 0;
			for (AirlineWorkingDestinations awd : airlineWorkingDestinations) {
				if (awd.getWorksWith().getId() == a.getId()) {
					i = 1;
				}
			}
			if (i == 0) {
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
		Airline air = ((AirlineAdmin) logged).getAirline();
		Airline work = this.airlineService.getOne(id);
		AirlineWorkingDestinations awd = this.airlineWorkingService.create(new AirlineWorkingDestinations(air, work));
		return new ResponseEntity<>(awd, HttpStatus.CREATED);
	}

	@GetMapping(value = "/getAirlineWorkingDestinations")
	@PreAuthorize("hasRole('ROLE_AIRLINE_ADMIN')")
	public ResponseEntity<List<AirlineWorkingDestinations>> getAllAirlineWorkingDestinations() {
		User logged = (User) this.userDetailsService
				.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		Airline air = ((AirlineAdmin) logged).getAirline();
		List<AirlineWorkingDestinations> airlineWorkingDestinations = this.airlineWorkingService
				.findByAirlineThatWorks(air);
		return new ResponseEntity<>(airlineWorkingDestinations, HttpStatus.OK);
	}
}
