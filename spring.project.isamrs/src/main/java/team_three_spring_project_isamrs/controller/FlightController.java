package team_three_spring_project_isamrs.controller;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import team_three_spring_project_isamrs.dto.AddFlightDTO;
import team_three_spring_project_isamrs.dto.FlightDTO;
import team_three_spring_project_isamrs.dto.FoundFlightsDTO;
import team_three_spring_project_isamrs.dto.SeatsDTO;
import team_three_spring_project_isamrs.model.Airline;
import team_three_spring_project_isamrs.model.AirlineAdmin;
import team_three_spring_project_isamrs.model.Flight;
import team_three_spring_project_isamrs.model.FlightClass;
import team_three_spring_project_isamrs.model.FlightStops;
import team_three_spring_project_isamrs.model.Seat;
import team_three_spring_project_isamrs.model.User;
import team_three_spring_project_isamrs.service.AirlineService;
import team_three_spring_project_isamrs.service.FlightService;
import team_three_spring_project_isamrs.service.FlightStopService;
import team_three_spring_project_isamrs.service.SeatService;
import team_three_spring_project_isamrs.service.impl.CustomUserDetailsService;

@RestController
public class FlightController {
	@Autowired
	FlightService flightService;

	@Autowired
	AirlineService airlineService;

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Autowired
	private FlightStopService flightStopService;

	@Autowired
	private SeatService seatService;

	@GetMapping(value = "/gradeFlight/{id}/{grade}", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<Flight> gradeFlight(@PathVariable Long id, @PathVariable Integer grade) {
		Flight flight = flightService.getOne(id);
		flight.setScore(flight.getScore() + grade);
		flight.setGradeNumber(flight.getGradeNumber() + 1);
		flightService.save(flight);
		return new ResponseEntity<>(flight, HttpStatus.CREATED);
	}

	@GetMapping(value = "/findConcreteFlights/{airlineId}")
	public ResponseEntity<Set<Flight>> findConcreteFlights(@PathVariable String airlineId) {
		Airline retVal = airlineService.getOne(Long.parseLong(airlineId));
		return new ResponseEntity<>(retVal.getFlights(), HttpStatus.OK);
	}

	@PostMapping(value = "/createFlight")
	@PreAuthorize("hasRole('ROLE_AIRLINE_ADMIN')")
	public ResponseEntity<Flight> create(@RequestBody FlightDTO flightDTO) throws ParseException {
		User logged = (User) this.userDetailsService
				.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		Airline air = ((AirlineAdmin) logged).getAirline();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date dateOfFlight = new Date(df.parse(flightDTO.getDateOfFlight()).getTime());
		Date dateOfArrival = new Date(df.parse(flightDTO.getDateOfArrival()).getTime());
		long l = (dateOfArrival.getTime() - dateOfFlight.getTime()) / 60000;
		int length = (int) l;
		Flight fl = this.flightService.create(new Flight(new AddFlightDTO(flightDTO.getFlightNumberRegister(),
				this.airlineService.getOne(Long.parseLong(flightDTO.getStartDestinationRegister())),
				this.airlineService.getOne(Long.parseLong(flightDTO.getFinalDestinationRegister())), air,
				flightDTO.getCostOfFlight(), df.format(dateOfFlight), df.format(dateOfArrival), length,
				flightDTO.getNumOfSeatsEconomy(), flightDTO.getNumOfSeatsBusiness(), flightDTO.getNumOfSeatsFirst(),
				flightDTO.getNumOfStops())));
		if (flightDTO.getNumOfStops() > 0) {
			String[] stops = flightDTO.getStops().split(" ");
			for (String s : stops) {
				this.flightStopService.create(new FlightStops(fl, this.airlineService.getOne(Long.parseLong(s))));
			}
		}
		air.getFlights().add(fl);

		return new ResponseEntity<>(fl, HttpStatus.CREATED);
	}

	@GetMapping(value = "/getAllFlights", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Flight>> getAllFlights() {
		List<Flight> flights = flightService.getAll();
		return new ResponseEntity<>(flights, HttpStatus.OK);
	}

	@GetMapping(value = "/searchFlightUnregistered/{startDate}/{endDate}/{startDestination}/{endDestination}")
	public ResponseEntity<List<Flight>> searchFlightUnregistered(@PathVariable String startDate,
			@PathVariable String endDate, @PathVariable Long startDestination, @PathVariable Long endDestination) {
		List<Flight> allFlights = flightService.getAll();
		List<Flight> retVal = new ArrayList<>();
		String startDatee = "";
		String endDatee = "";
		for (Flight flight : allFlights) {
			startDatee = flight.getDateOfStart().substring(0, 10);
			if (!startDate.equals("0000-00-00") && !startDate.equals(startDatee)) {
				continue;
			}
			endDatee = flight.getDateOfEnd().substring(0, 10);
			if (!endDate.equals("0000-00-00") && !endDate.equals(endDatee)) {
				continue;
			}
			if (startDestination != 0 && !startDestination.equals(flight.getStartAirline().getId())) {
				continue;
			}
			if (endDestination != 0 && !endDestination.equals(flight.getFinalAirline().getId())) {
				continue;
			}
			retVal.add(flight);
		}
		return new ResponseEntity<>(retVal, HttpStatus.OK);
	}

	@GetMapping(value = "/searchFlight/{startDestination}/{finalDestination}/{dateOfFlight}/{dateOfArrival}/{from}/{to}/{fromL}/{toL}/{name}")
	public ResponseEntity<FoundFlightsDTO> searchFlights(@PathVariable Long startDestination,
			@PathVariable Long finalDestination, @PathVariable String dateOfFlight, @PathVariable String dateOfArrival,
			@PathVariable String from, @PathVariable String to, @PathVariable String fromL, @PathVariable String toL,
			@PathVariable String name) throws ParseException {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date dateOfFl = df.parse(dateOfFlight);
		Date dateOfArr = new Date();
		if (!dateOfArrival.equals("noDate")) {
			dateOfArr = df.parse(dateOfArrival);
		}
		List<Flight> flights;
		List<Flight> returnFlights = new ArrayList<>();
		flights = this.flightService.findByStartAirlineAndFinalAirline(this.airlineService.getOne(startDestination),
				this.airlineService.getOne(finalDestination));
		if (!dateOfArrival.equals("noDate")) {
			returnFlights = this.flightService.findByStartAirlineAndFinalAirline(
					this.airlineService.getOne(finalDestination), this.airlineService.getOne(startDestination));
		}
		List<Flight> flights2 = new ArrayList<>();
		List<Flight> returnFLights2 = new ArrayList<>();
		for (Flight f : flights) {
			if (df.parse(f.getDateOfStart()).equals(dateOfFl)) {
				flights2.add(f);
			}
		}
		if (!dateOfArrival.equals("noDate")) {
			for (Flight f : returnFlights) {
				if (df.parse(f.getDateOfStart()).equals(dateOfArr)) {
					returnFLights2.add(f);
				}
			}
		}

		List<Flight> filtered = new ArrayList<>();
		List<Flight> filteredReturn = new ArrayList<>();
		double fromm;
		double too;
		int fromLL;
		double toLL;
		String namee;
		if (!from.equals("noFrom")) {
			fromm = Double.parseDouble(from);
		} else {
			fromm = -1;
		}
		if (!to.equals("noTo")) {
			too = Double.parseDouble(to);
		} else {
			too = 10000000;
		}
		if (!fromL.equals("noFromL")) {
			fromLL = Integer.parseInt(fromL);
		} else {
			fromLL = -1;
		}
		if (!toL.equals("noToL")) {
			toLL = Integer.parseInt(toL);
		} else {
			toLL = 1000000;
		}
		if (!name.equalsIgnoreCase("noName")) {
			namee = name;
		} else {
			namee = "";
		}
		for (Flight f : flights2) {
			if (f.getCost() > fromm && f.getCost() < too && f.getLengthOfFlight() > fromLL
					&& f.getLengthOfFlight() < toLL) {
				if (!namee.equals("")) {
					if (f.getAirline().getName().equalsIgnoreCase(namee)) {
						filtered.add(f);
					}
				} else {
					filtered.add(f);
				}
			}

		}

		for (Flight f : returnFLights2) {
			if (f.getCost() > fromm && f.getCost() < too && f.getLengthOfFlight() > fromLL
					&& f.getLengthOfFlight() < toLL) {
				if (!namee.equals("")) {
					if (f.getAirline().getName().equalsIgnoreCase(namee)) {
						filteredReturn.add(f);
					}
				} else {
					filteredReturn.add(f);
				}
			}

		}
		FoundFlightsDTO ff = new FoundFlightsDTO(filtered, filteredReturn);

		return new ResponseEntity<>(ff, HttpStatus.OK);
	}

	@GetMapping(value = "/getFlight/{id}")
	public ResponseEntity<SeatsDTO> getFlight(@PathVariable Long id) {
		Flight fl = this.flightService.getOne(id);

		List<Seat> seatsFirst = this.seatService.findByFlightAndFc(fl, FlightClass.FIRST);
		List<Seat> seatsBusiness = this.seatService.findByFlightAndFc(fl, FlightClass.BUSINESS);
		List<Seat> seatsEconomy = this.seatService.findByFlightAndFc(fl, FlightClass.ECONOMY);
		SeatsDTO seats = new SeatsDTO(seatsFirst, seatsBusiness, seatsEconomy);
		return new ResponseEntity<>(seats, HttpStatus.OK);
	}

	@GetMapping(value = "/getSeat/{id}")
	public ResponseEntity<Seat> getSeat(@PathVariable Long id) {
		Seat s = this.seatService.getOne(id);
		return new ResponseEntity<>(s, HttpStatus.OK);
	}

	@GetMapping(value = "getFlights")
	public ResponseEntity<List<Flight>> getFlights() {
		User logged = (User) this.userDetailsService
				.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		List<Flight> flights = this.flightService.findByStartAirline(((AirlineAdmin) logged).getAirline());
		return new ResponseEntity<>(flights, HttpStatus.OK);
	}

}
