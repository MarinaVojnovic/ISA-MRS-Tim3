package tim3.spring.project.isamrs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tim3.spring.project.isamrs.model.Destination;
import tim3.spring.project.isamrs.model.Flight;
import tim3.spring.project.isamrs.repository.DestinationRepository;
import tim3.spring.project.isamrs.repository.FlightRepository;
import tim3.spring.project.isamrs.service.FlightService;

@Service
public class FlightServiceImpl implements FlightService{
	@Autowired
	FlightRepository flightRepository;
	
	@Autowired
	DestinationRepository destinationRepository;

	@Override
	public Flight getOne(long id) {
		// TODO Auto-generated method stub
		return flightRepository.findOne(id);
	}

	@Override
	public List<Flight> getAll() {
		// TODO Auto-generated method stub
		return flightRepository.findAll();
	}

	@Override
	public Flight create(Flight flight) {
		// TODO Auto-generated method stub
		return flightRepository.save(flight);
	}

	@Override
	public Flight update(Flight flight) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public List<Flight> findByStartDestinationAndFinalDestination(int startDestination, int finalDestination) {
		// TODO Auto-generated method stub
		Destination d1=destinationRepository.getOne((long) startDestination);
		Destination d2=destinationRepository.getOne((long )finalDestination);
		return flightRepository.findByStartDestinationAndFinalDestination(d1, d2);
	}
	

}
