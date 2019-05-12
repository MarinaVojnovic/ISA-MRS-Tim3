package team_three_spring_project_isamrs.service;

import java.util.List;

import team_three_spring_project_isamrs.model.Hotel;
import team_three_spring_project_isamrs.model.HotelCustomerService;

public interface HotelCustomerServiceService {
	public HotelCustomerService getOne(long id);

	public List<HotelCustomerService> getAll();

	public HotelCustomerService create(HotelCustomerService hcs);

	public void delete(long id);

	public HotelCustomerService save(HotelCustomerService hcs);

	public List<HotelCustomerService> findByHotel(Hotel h);
}
