package tim3.spring.project.isamrs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tim3.spring.project.isamrs.model.Airline;
import tim3.spring.project.isamrs.repository.AirlineRepository;
import tim3.spring.project.isamrs.service.AirlineService;

@Service
public class AirlineServiceImpl implements AirlineService {

	@Autowired
	AirlineRepository airlineRepository;

	@Override
	public Airline getOne(long id) {
		return airlineRepository.findOne(id);
	}

	@Override
	public List<Airline> getAll() {
		return airlineRepository.findAll();
	}

	@Override
	public Airline create(Airline airline) {
		return airlineRepository.save(airline);
	}

	@Override
	public Airline update(Airline airline) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub

	}

}
