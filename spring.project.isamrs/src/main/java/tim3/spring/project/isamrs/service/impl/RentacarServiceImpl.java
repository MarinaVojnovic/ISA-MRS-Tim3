package tim3.spring.project.isamrs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tim3.spring.project.isamrs.model.Rentacar;
import tim3.spring.project.isamrs.repository.RentacarRepository;
import tim3.spring.project.isamrs.service.RentacarService;

@Service
public class RentacarServiceImpl implements RentacarService{
	
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
	public Rentacar update(Rentacar rentacar) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public Rentacar save(Rentacar rentacar) {
		return rentacarRepository.save(rentacar);
	}

	@Override
	public List<Rentacar> findByName(String name) {
		// TODO Auto-generated method stub
		return rentacarRepository.findByName(name);
	}

	@Override
	public List<Rentacar> findByAddress(String address) {
		// TODO Auto-generated method stub
		return rentacarRepository.findByAddress(address);
	}

}
