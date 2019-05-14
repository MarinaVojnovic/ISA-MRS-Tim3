package team_three_spring_project_isamrs.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team_three_spring_project_isamrs.model.Airline;
import team_three_spring_project_isamrs.model.Flight;
import team_three_spring_project_isamrs.repository.FlightRepository;
import team_three_spring_project_isamrs.service.FlightService;

@Service
public class FlightServiceImpl implements FlightService {
	@Autowired
	FlightRepository flightRepository;

	@Override
	public Flight getOne(long id) {
		return flightRepository.findOne(id);
	}

	@Override
	public List<Flight> getAll() {
		return flightRepository.findAll();
	}

	@Override
	public Flight create(Flight flight) {
		return flightRepository.save(flight);
	}

	@Override
	public Flight save(Flight flight) {
		return flightRepository.save(flight);
	}

	@Override
	public void delete(long id) {

	}

	@Override
	public List<Flight> findByStartAirlineAndFinalAirlineAndDateOfStartAndDateOfEnd(Airline start, Airline end,
			Date startD, Date endD) {
		return this.flightRepository.findByStartAirlineAndFinalAirlineAndDateOfStartAndDateOfEnd(start, end, startD,
				endD);
	}

	@Override
	public List<Flight> findByStartAirlineAndFinalAirlineAndDateOfStart(Airline start, Airline end, Date startD) {
		return this.flightRepository.findByStartAirlineAndFinalAirlineAndDateOfStart(start, end, startD);
	}

	@Override
	public List<Flight> findByStartAirlineAndFinalAirline(Airline start, Airline end) {
		return this.flightRepository.findByStartAirlineAndFinalAirline(start, end);
	}

}
