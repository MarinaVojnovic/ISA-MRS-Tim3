package team_three_spring_project_isamrs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import team_three_spring_project_isamrs.model.Airline;
import team_three_spring_project_isamrs.model.Flight;
import team_three_spring_project_isamrs.model.FlightReservation;
import team_three_spring_project_isamrs.model.RegularUser;
import team_three_spring_project_isamrs.service.FlightReservationService;
import team_three_spring_project_isamrs.service.FlightService;
import team_three_spring_project_isamrs.service.impl.CustomUserDetailsService;
@RestController
public class FlightReservationController {
	@Autowired
	FlightReservationService flightReservationService;
	
	@Autowired
	FlightService flightService;
	
	
	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@GetMapping(value = "/findAirlineFromRes/{resId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Airline> findAirlineFromRes(@PathVariable Long resId) {
		FlightReservation res = flightReservationService.getOne(resId);
		Airline airline = res.getFlightReservation().getAirline();
		return new ResponseEntity<>(airline, HttpStatus.OK);
	}
	@GetMapping(value = "/findFlightFromRes/{resId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Flight> findFlightFromRes(@PathVariable Long resId) {
		FlightReservation res = flightReservationService.getOne(resId);
		Flight flight = res.getFlightReservation();
		return new ResponseEntity<>(flight, HttpStatus.OK);
	}
	@DeleteMapping(value = "/cancelFlightReservation/{resId}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<?> cancelFlightReservation(@PathVariable Long resId) {
		System.out.println("USLO U CANCEL FLIGHT RESERVATION");
		RegularUser user = (RegularUser) this.userDetailsService
				.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		System.out.println("A");
		int brojac=-1;
		
		  for (FlightReservation fr : user.getFlightReservations()) { brojac++; if
		  (fr.getId()==resId) { user.getCarReservations().remove(brojac); } }
		 
		System.out.println("B");
		userDetailsService.saveUser(user);
		System.out.println("C");
		FlightReservation flightRes = flightReservationService.getOne(resId);
		System.out.println("D");
		Flight flight = flightRes.getFlightReservation();
		System.out.println("E");
		int brojac2=-1;
		
		/*
		 * for (FlightReservation fr : flight.getFlightReservation()) { brojac2++; if
		 * (fr.getId()==resId) { flight.getFlightReservation().remove(brojac2); } }
		 */
		 
		System.out.println("F");
		flightService.save(flight);
		System.out.println("G");
		flightReservationService.delete(resId);
		System.out.println("H");
		
	
		return new ResponseEntity<>(flightRes, HttpStatus.OK);
		
		
		
	}
}
