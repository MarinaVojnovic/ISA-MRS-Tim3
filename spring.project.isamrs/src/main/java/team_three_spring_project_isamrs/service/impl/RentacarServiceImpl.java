package team_three_spring_project_isamrs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team_three_spring_project_isamrs.model.Rentacar;
import team_three_spring_project_isamrs.repository.RentacarRepository;
import team_three_spring_project_isamrs.service.RentacarService;

@Service
public class RentacarServiceImpl implements RentacarService {

	@Autowired
	RentacarRepository rentacarRepository;

	@Override
	public Rentacar getOne(long id) {
		return rentacarRepository.findOne(id);
	}

	@Override
	public List<Rentacar> getAll() {
		return rentacarRepository.findAll();
	}

	@Override
	public Rentacar create(Rentacar rentacar) {
		return rentacarRepository.save(rentacar);
	}

	@Override
	public List<Rentacar> findByName(String name) {
		return rentacarRepository.findByName(name);
	}
	
	@Override
	public List<Rentacar> findByCity(String city) {
		return rentacarRepository.findByCity(city);
	}

	@Override
	public List<Rentacar> findByAddress(String address) {
		return rentacarRepository.findByAddress(address);
	}

	@Override
	public Rentacar save(Rentacar rentacar) {
		return rentacarRepository.save(rentacar);
	}

}
