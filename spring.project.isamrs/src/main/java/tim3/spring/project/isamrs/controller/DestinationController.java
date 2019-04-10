package tim3.spring.project.isamrs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tim3.spring.project.isamrs.service.DestinationService;
import tim3.spring.project.isamrs.model.Destination;
@RestController
public class DestinationController {
	@Autowired
	DestinationService destinationService;
	
	@RequestMapping(value="/getAllDestinations",method = RequestMethod.GET)
	public ResponseEntity<List<Destination>> getAllAirlines() {
		List<Destination> destinations = destinationService.getAll();
		return new ResponseEntity<>(destinations, HttpStatus.OK); 
	}

}

