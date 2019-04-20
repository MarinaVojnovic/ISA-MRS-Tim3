package tim3.spring.project.isamrs.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tim3.spring.project.isamrs.dto.FlightDTO;
import tim3.spring.project.isamrs.dto.SearchFlightDTO;
import tim3.spring.project.isamrs.model.Flight;
import tim3.spring.project.isamrs.service.FlightService;

@RestController
public class FlightController {
	@Autowired
	FlightService flightService;

	@PostMapping(value = "/createFlight", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Flight> create(@RequestBody FlightDTO flightDTO) {
		Flight retVal = flightService.create(new Flight(flightDTO));
		return new ResponseEntity<>(retVal, HttpStatus.CREATED);
	}

	@GetMapping(value = "/getAllFlights", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Flight>> getAllFlights() {
		List<Flight> flights = flightService.getAll();
		return new ResponseEntity<>(flights, HttpStatus.OK);
	}

	@PostMapping(value = "/searchFlight", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Flight>> searchFlights(@RequestBody SearchFlightDTO searchFlight) {
		List<Flight> flights = flightService.findByStartDestinationAndFinalDestination(
				searchFlight.getStartDestination(), searchFlight.getFinalDestination());
		List<Flight> filtered = new ArrayList<>();
		double from, to;
		int fromL, toL;
		String name;
		if (!searchFlight.getFrom().equals("")) {
			from = Double.parseDouble(searchFlight.getFrom());
		} else {
			from = -1;
		}
		if (!searchFlight.getTo().equals("")) {
			to = Double.parseDouble(searchFlight.getTo());
		} else {
			to = 10000000;
		}
		if (!searchFlight.getFromL().equals("")) {
			fromL = Integer.parseInt(searchFlight.getFromL());
		} else {
			fromL = -1;
		}
		if (!searchFlight.getToL().equals("")) {
			toL = Integer.parseInt(searchFlight.getToL());
		} else {
			toL = 1000000;
		}
		if (!searchFlight.getName().equalsIgnoreCase("")) {
			name = searchFlight.getName();
		} else {
			name = "";
		}
		for (Flight f : flights) {
			if (f.getCost() > from && f.getCost() < to && f.getLengthOfFlight() > fromL
					&& f.getLengthOfFlight() < toL) {
				if (!name.equals("")) {
					if (f.getAirline().getName().equalsIgnoreCase(name)) {
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
