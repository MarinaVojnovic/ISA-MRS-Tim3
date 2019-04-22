package tim3.spring.project.isamrs.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tim3.spring.project.isamrs.model.Airline;
import tim3.spring.project.isamrs.model.Flight;
import tim3.spring.project.isamrs.repository.FlightRepository;
import tim3.spring.project.isamrs.service.FlightService;

@Service
public class FlightServiceImpl implements FlightService{
	@Autowired
	FlightRepository flightRepository;
	

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
	public List<Flight> findByStartAirlineAndFinalAirlineAndDateOfStartAndDateOfEnd(Airline start, Airline end,
			Date startD, Date endD) {
		return this.flightRepository.findByStartAirlineAndFinalAirlineAndDateOfStartAndDateOfEnd(start, end, startD, endD);
	}

	@Override
	public List<Flight> findByStartAirlineAndFinalAirlineAndDateOfStart(Airline start, Airline end, Date startD) {
		return this.flightRepository.findByStartAirlineAndFinalAirlineAndDateOfStart(start, end, startD);
	}

	@Override
	public List<Flight> findByStartAirlineAndFinalAirline(Airline start, Airline end) {
		return this.flightRepository.findByStartAirlineAndFinalAirline(start,end);
	}
	

	

}
