package team_three_spring_project_isamrs.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import team_three_spring_project_isamrs.comparator.AirlineComparatorAddress;
import team_three_spring_project_isamrs.comparator.AirlineComparatorName;
import team_three_spring_project_isamrs.dto.AirlineDTO;
import team_three_spring_project_isamrs.model.Airline;
import team_three_spring_project_isamrs.model.AirlineAdmin;
import team_three_spring_project_isamrs.model.AirlineWorkingDestinations;
import team_three_spring_project_isamrs.model.Flight;
import team_three_spring_project_isamrs.model.Seat;
import team_three_spring_project_isamrs.model.User;
import team_three_spring_project_isamrs.service.AirlineService;
import team_three_spring_project_isamrs.service.AirlineWorkingService;
import team_three_spring_project_isamrs.service.FlightService;
import team_three_spring_project_isamrs.service.SeatService;
import team_three_spring_project_isamrs.service.impl.CustomUserDetailsService;

@RestController
public class AirlineController {

	@Autowired
	AirlineService airlineService;

	@Autowired
	AirlineWorkingService airlineWorkingService;

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Autowired
	private FlightService flightService;

	@Autowired
	private SeatService seatService;

	@GetMapping(value = "/gradeAirline/{id}/{grade}", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<Airline> gradeAirline(@PathVariable Long id, @PathVariable Integer grade) {
		Airline airline = airlineService.getOne(id);
		airline.setScore(airline.getScore() + grade);
		airline.setGradeNumber(airline.getGradeNumber() + 1);
		airlineService.save(airline);
		return new ResponseEntity<>(airline, HttpStatus.CREATED);
	}

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
		a.setCity(airline.getCity());

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
		List<Airline> airlines = airlineService.findByName(field);
		if (airlines.isEmpty()) {
			airlines = airlineService.findByCity(field);
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
			if (a.getId().equals(air.getId())) {
				i = 1;
			}

			for (AirlineWorkingDestinations awd : airlineWorkingDestinations) {
				if (awd.getWorksWith().getId().equals(a.getId()) || a.getId().equals(air.getId())) {
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

	@GetMapping(value = "/getConcreteDestinations/{airlineId}")
	public ResponseEntity<List<AirlineWorkingDestinations>> getConcreteAirlineWorkingDestinations(
			@PathVariable Long airlineId) {
		Airline air = airlineService.getOne(airlineId);
		List<AirlineWorkingDestinations> airlineWorkingDestinations = this.airlineWorkingService
				.findByAirlineThatWorks(air);
		return new ResponseEntity<>(airlineWorkingDestinations, HttpStatus.OK);
	}

	@GetMapping(value = "/findDest/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Airline> searchFlights(@PathVariable Long id) {
		Airline a = airlineService.getOne(id);
		return new ResponseEntity<>(a, HttpStatus.OK);
	}

	@GetMapping(value = "/getAllFlightsAirline")
	@PreAuthorize("hasRole('ROLE_AIRLINE_ADMIN')")
	public ResponseEntity<List<Flight>> getAllFlightsAirline() throws ParseException {
		User logged = (User) this.userDetailsService
				.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		Airline air = ((AirlineAdmin) logged).getAirline();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		List<Flight> flights = this.flightService.findByStartAirline(air);
		List<Flight> returnFlights=new ArrayList<>();
		for(Flight f: flights) {
			Date date=df.parse(f.getDateOfStart());
			Date today=new Date();
			if(date.getTime() > today.getTime()) {
				returnFlights.add(f);
			}
		}
		return new ResponseEntity<>(returnFlights, HttpStatus.OK);
	}

	@PostMapping(value = "/addQuickBooking/{sed}/{popust}")
	@PreAuthorize("hasRole('ROLE_AIRLINE_ADMIN')")
	public ResponseEntity<Boolean> addQuickBooking(@PathVariable String sed, @PathVariable int popust) {
		String[] sedista = sed.split("\\*");
		for (String st : sedista) {
			Seat s = this.seatService.getOne(Long.parseLong(st));
			s.setQuickBooking(true);
			s.setDiscount(popust);
			this.seatService.save(s);

		}
		return new ResponseEntity<>(true, HttpStatus.OK);
	}

	@DeleteMapping(value = "/deleteSeats/{sed}")
	@PreAuthorize("hasRole('ROLE_AIRLINE_ADMIN')")
	public ResponseEntity<Boolean> deleteSeats(@PathVariable String sed) {
		String[] sedista = sed.split("\\*");
		for (String st : sedista) {
			this.seatService.delete(Long.parseLong(st));

		}
		return new ResponseEntity<>(true, HttpStatus.OK);
	}

}
