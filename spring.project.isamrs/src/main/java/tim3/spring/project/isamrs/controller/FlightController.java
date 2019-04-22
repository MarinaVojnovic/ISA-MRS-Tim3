package tim3.spring.project.isamrs.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.text.ParseException;

import tim3.spring.project.isamrs.dto.AddFlightDTO;
import tim3.spring.project.isamrs.dto.FlightDTO;
import tim3.spring.project.isamrs.dto.SearchFlightDTO;
import tim3.spring.project.isamrs.model.Airline;
import tim3.spring.project.isamrs.model.AirlineAdmin;
import tim3.spring.project.isamrs.model.Flight;
import tim3.spring.project.isamrs.model.FlightStops;
import tim3.spring.project.isamrs.model.User;
import tim3.spring.project.isamrs.service.AirlineService;
import tim3.spring.project.isamrs.service.FlightService;
import tim3.spring.project.isamrs.service.FlightStopService;
import tim3.spring.project.isamrs.service.impl.CustomUserDetailsService;

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

	@PostMapping(value = "/createFlight")
	@PreAuthorize("hasRole('ROLE_AIRLINE_ADMIN')")
	public ResponseEntity<Flight> create(@RequestBody FlightDTO flightDTO) {
		User logged = (User) this.userDetailsService
				.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		Airline air=((AirlineAdmin) logged).getAirline();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		@SuppressWarnings("deprecation")
		Date dateOfFlight=new Date(Integer.parseInt(flightDTO.getDateOfFlight().split("\\-")[0])-1900,Integer.parseInt(flightDTO.getDateOfFlight().split("\\-")[1])-1,Integer.parseInt(flightDTO.getDateOfFlight().split("\\-")[2]));
		@SuppressWarnings("deprecation")
		Date dateOfArrival=new Date(Integer.parseInt(flightDTO.getDateOfArrival().split("\\-")[0])-1900,Integer.parseInt(flightDTO.getDateOfArrival().split("\\-")[1])-1,Integer.parseInt(flightDTO.getDateOfArrival().split("\\-")[2]));
		Flight fl=this.flightService.create(new Flight(new AddFlightDTO(flightDTO.getFlightNumberRegister(),this.airlineService.getOne(Long.parseLong(flightDTO.getStartDestinationRegister())),this.airlineService.getOne(Long.parseLong(flightDTO.getFinalDestinationRegister())),air,flightDTO.getCostOfFlight(),dateOfFlight,dateOfArrival,flightDTO.getLength(),flightDTO.getNumOfSeats(),flightDTO.getNumOfStops())));
		if(flightDTO.getNumOfStops()>0) {
			String[] stops=flightDTO.getStops().split(" ");
			for(String s: stops) {
				FlightStops fs=this.flightStopService.create(new FlightStops(fl,this.airlineService.getOne(Long.parseLong(s))));
			}
		}
		
		return new ResponseEntity<>(fl, HttpStatus.CREATED);
	}

	@GetMapping(value = "/getAllFlights", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Flight>> getAllFlights() {
		List<Flight> flights = flightService.getAll();
		return new ResponseEntity<>(flights, HttpStatus.OK);
	}

	@SuppressWarnings("deprecation")
	@GetMapping(value = "/searchFlight/{startDestination}/{finalDestination}/{dateOfFlight}/{dateOfArrival}/{from}/{to}/{fromL}/{toL}/{name}")
	public ResponseEntity<List<Flight>> searchFlights(@PathVariable Long startDestination,@PathVariable Long finalDestination, @PathVariable String dateOfFlight,@PathVariable String dateOfArrival,@PathVariable String from,@PathVariable String to, @PathVariable String fromL,@PathVariable String toL,@PathVariable String name) {
		Date dateOfFl=new Date(Integer.parseInt(dateOfFlight.split("\\-")[0])-1900,Integer.parseInt(dateOfFlight.split("\\-")[1])-1,Integer.parseInt(dateOfFlight.split("\\-")[2]));
		Date dateOfArr=new Date(0,0,0);
		if(!dateOfArrival.equals("noDate")) {
			dateOfArr=new Date(Integer.parseInt(dateOfArrival.split("\\-")[0])-1900,Integer.parseInt(dateOfArrival.split("\\-")[1])-1,Integer.parseInt(dateOfArrival.split("\\-")[2]));
		}
		List<Flight> flights=new ArrayList<Flight>();
		if(dateOfArrival.equals("noDate")) {
			flights=this.flightService.findByStartAirlineAndFinalAirlineAndDateOfStart(this.airlineService.getOne(startDestination), this.airlineService.getOne(finalDestination), dateOfFl);
			
		}else {
			flights=this.flightService.findByStartAirlineAndFinalAirlineAndDateOfStartAndDateOfEnd(this.airlineService.getOne(startDestination), this.airlineService.getOne(finalDestination), dateOfFl, dateOfArr);
		}
		List<Flight> filtered=new ArrayList<Flight>();
		double fromm, too;
		int fromLL, toLL;
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
		for (Flight f : flights) {
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

		return new ResponseEntity<>(filtered, HttpStatus.OK);
	}

}
