package tim3.spring.project.isamrs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tim3.spring.project.isamrs.model.Hotel;
import tim3.spring.project.isamrs.model.HotelCustomerService;
import tim3.spring.project.isamrs.repository.HotelCustomerServiceRepository;
import tim3.spring.project.isamrs.service.HotelCustomerServiceService;

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
	public HotelCustomerService update(HotelCustomerService hcs) {
		return null;
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
