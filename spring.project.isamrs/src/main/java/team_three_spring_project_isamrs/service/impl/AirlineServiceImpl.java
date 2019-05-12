package team_three_spring_project_isamrs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team_three_spring_project_isamrs.model.Airline;
import team_three_spring_project_isamrs.repository.AirlineRepository;
import team_three_spring_project_isamrs.service.AirlineService;

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
	public Airline save(Airline airline) {
		return airlineRepository.save(airline);
	}

	public List<Airline> findByName(String name) {
		return airlineRepository.findByName(name);
	}

	public List<Airline> findByAddress(String address) {
		return airlineRepository.findByAddress(address);
	}

}
