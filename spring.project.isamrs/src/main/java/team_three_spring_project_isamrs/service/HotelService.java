package team_three_spring_project_isamrs.service;

import java.util.List;

import team_three_spring_project_isamrs.model.Hotel;

public interface HotelService {
	public Hotel getOne(long id);

	public List<Hotel> getAll();

	public Hotel create(Hotel hotel);

	public Hotel save(Hotel hotel);

	public List<Hotel> findByName(String name);

	public List<Hotel> findByAddress(String address);
}
