package team_three_spring_project_isamrs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team_three_spring_project_isamrs.model.Airline;
import team_three_spring_project_isamrs.model.AirlineWorkingDestinations;
import team_three_spring_project_isamrs.repository.AirlineWorkingRepository;
import team_three_spring_project_isamrs.service.AirlineWorkingService;

@Service
public class AirlineWorkingServiceImpl implements AirlineWorkingService {

	@Autowired
	AirlineWorkingRepository airlineWorkingRepository;

	@Override
	public AirlineWorkingDestinations getOne(long id) {
		return airlineWorkingRepository.findOne(id);
	}

	@Override
	public List<AirlineWorkingDestinations> getAll() {
		return airlineWorkingRepository.findAll();
	}

	@Override
	public AirlineWorkingDestinations create(AirlineWorkingDestinations awd) {
		return airlineWorkingRepository.save(awd);
	}

	@Override
	public void delete(long id) {

	}

	@Override
	public AirlineWorkingDestinations save(AirlineWorkingDestinations awd) {
		return airlineWorkingRepository.save(awd);
	}

	@Override
	public List<AirlineWorkingDestinations> findByAirlineThatWorks(Airline air) {
		return airlineWorkingRepository.findByAirlineThatWorks(air);
	}

}
