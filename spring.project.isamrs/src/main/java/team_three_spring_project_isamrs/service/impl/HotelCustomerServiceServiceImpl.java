package team_three_spring_project_isamrs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team_three_spring_project_isamrs.model.Hotel;
import team_three_spring_project_isamrs.model.HotelCustomerService;
import team_three_spring_project_isamrs.repository.HotelCustomerServiceRepository;
import team_three_spring_project_isamrs.service.HotelCustomerServiceService;

@Service
public class HotelCustomerServiceServiceImpl implements HotelCustomerServiceService {

	@Autowired
	HotelCustomerServiceRepository hotelCustomerServiceRepository;

	@Override
	public HotelCustomerService getOne(long id) {
		return hotelCustomerServiceRepository.findOne(id);
	}

	@Override
	public List<HotelCustomerService> getAll() {
		return hotelCustomerServiceRepository.findAll();
	}

	@Override
	public HotelCustomerService create(HotelCustomerService hcs) {
		return hotelCustomerServiceRepository.save(hcs);
	}

	@Override
	public void delete(long id) {
		hotelCustomerServiceRepository.delete(id);

	}

	@Override
	public HotelCustomerService save(HotelCustomerService hcs) {
		return hotelCustomerServiceRepository.save(hcs);
	}

	@Override
	public List<HotelCustomerService> findByHotel(Hotel h) {
		return hotelCustomerServiceRepository.findByHotel(h);
	}

}
