package team_three_spring_project_isamrs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import team_three_spring_project_isamrs.model.Hotel;
import team_three_spring_project_isamrs.repository.HotelRepository;
import team_three_spring_project_isamrs.service.HotelService;

@Service
@Transactional(readOnly = true)
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
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public Hotel create(Hotel hotel) {
		return hotelRepository.save(hotel);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public Hotel save(Hotel hotel) {
		return hotelRepository.save(hotel);
	}

	public List<Hotel> findByName(String name) {
		return hotelRepository.findByName(name);
	}

	public List<Hotel> findByAddress(String address) {
		return hotelRepository.findByAddress(address);
	}
}
