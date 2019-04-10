package tim3.spring.project.isamrs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tim3.spring.project.isamrs.model.Destination;
import tim3.spring.project.isamrs.repository.DestinationRepository;
import tim3.spring.project.isamrs.service.DestinationService;


@Service
public class DestinationServiceImpl implements DestinationService{

	@Autowired
	DestinationRepository destinationRepository;
	
	@Override
	public Destination getOne(long id) {
		// TODO Auto-generated method stub
		return destinationRepository.findOne(id);
	}

	@Override
	public List<Destination> getAll() {
		// TODO Auto-generated method stub
		return destinationRepository.findAll();
	}

	@Override
	public Destination create(Destination destination) {
		// TODO Auto-generated method stub
		return destinationRepository.save(destination);
	}

	@Override
	public Destination update(Destination destination) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Destination save(Destination destination) {
		// TODO Auto-generated method stub
		return destinationRepository.save(destination);
	}




}
