package tim3.spring.project.isamrs.service;

import java.util.List;

import tim3.spring.project.isamrs.model.Hotel;
import tim3.spring.project.isamrs.model.HotelCustomerService;

public interface HotelCustomerServiceService {
	public HotelCustomerService getOne(long id);

	public List<HotelCustomerService> getAll();

	public HotelCustomerService create(HotelCustomerService hcs);

	public HotelCustomerService update(HotelCustomerService hcs);

	public void delete(long id);

	public HotelCustomerService save(HotelCustomerService hcs);

	public List<HotelCustomerService> findByHotel(Hotel h);
}
