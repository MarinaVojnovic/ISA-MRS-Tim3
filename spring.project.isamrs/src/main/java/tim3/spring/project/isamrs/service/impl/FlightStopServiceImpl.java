package tim3.spring.project.isamrs.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tim3.spring.project.isamrs.model.FlightStops;
import tim3.spring.project.isamrs.repository.FlightRepository;
import tim3.spring.project.isamrs.repository.FlightStopRepository;
import tim3.spring.project.isamrs.service.FlightStopService;


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
	public FlightStops update(FlightStops flight) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}

}
