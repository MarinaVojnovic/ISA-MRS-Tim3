package tim3.spring.project.isamrs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
		System.out.println(airlineDTO.getAirlineAddressRegister()+"*******");
		Airline retVal = airlineService.create(new Airline(airlineDTO));
		return new ResponseEntity<>(retVal, HttpStatus.CREATED);
	}
}
