package team_three_spring_project_isamrs.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team_three_spring_project_isamrs.model.FlightStops;
import team_three_spring_project_isamrs.repository.FlightStopRepository;
import team_three_spring_project_isamrs.service.FlightStopService;


@Service
public class FlightStopServiceImpl implements FlightStopService{
	@Autowired
	FlightStopRepository flightStopRepository;

	@Override
	public FlightStops getOne(long id) {
		return this.flightStopRepository.findOne(id);
	}

	@Override
	public List<FlightStops> getAll() {
		return this.flightStopRepository.findAll();
	}

	@Override
	public FlightStops create(FlightStops flight) {
		return this.flightStopRepository.save(flight);
	}

	@Override
	public void delete(long id) {
		
	}

}
