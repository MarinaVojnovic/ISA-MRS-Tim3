package tim3.spring.project.isamrs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tim3.spring.project.isamrs.model.Airline;
import tim3.spring.project.isamrs.model.AirlineWorkingDestinations;
import tim3.spring.project.isamrs.repository.AirlineWorkingRepository;
import tim3.spring.project.isamrs.service.AirlineWorkingService;

@Service
public class AirlineWorkingServiceImpl implements AirlineWorkingService{

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
	public AirlineWorkingDestinations update(AirlineWorkingDestinations awd) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
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
