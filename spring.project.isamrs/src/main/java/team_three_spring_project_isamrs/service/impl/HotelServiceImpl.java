package team_three_spring_project_isamrs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team_three_spring_project_isamrs.model.Hotel;
import team_three_spring_project_isamrs.repository.HotelRepository;
import team_three_spring_project_isamrs.service.HotelService;

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
