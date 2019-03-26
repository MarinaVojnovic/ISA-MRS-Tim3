package tim3.spring.project.isamrs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tim3.spring.project.isamrs.model.Hotel;
import tim3.spring.project.isamrs.repository.HotelRepository;
import tim3.spring.project.isamrs.service.HotelService;

@Service
public class HotelServiceImpl implements HotelService {
	
	@Autowired
	HotelRepository hotelRepository;

	@Override
	public Hotel getOne(long id) {
		return hotelRepository.findOne(id);
	}

	@Override
	public List<Hotel> getAll() {
		return hotelRepository.findAll();
	}

	@Override
	public Hotel create(Hotel hotel) {
		return hotelRepository.save(hotel);
	}

	@Override
	public Hotel update(Hotel hotel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub

	}
}
