package tim3.spring.project.isamrs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tim3.spring.project.isamrs.dto.AirlineDTO;
import tim3.spring.project.isamrs.dto.FlightDTO;
import tim3.spring.project.isamrs.model.Airline;
import tim3.spring.project.isamrs.model.Flight;
import tim3.spring.project.isamrs.service.FlightService;

@RestController
public class FlightController {
	@Autowired
	FlightService flightService;

	@RequestMapping(value = "/createFlight", method = RequestMethod.POST)
	public ResponseEntity<Flight> create(@RequestBody FlightDTO flightDTO) {
		Flight retVal = flightService.create(new Flight(flightDTO));
		return new ResponseEntity<>(retVal, HttpStatus.CREATED);
	}

}
