package tim3.spring.project.isamrs.service;

import java.util.List;

import tim3.spring.project.isamrs.model.Hotel;
import tim3.spring.project.isamrs.model.Rentacar;

public interface HotelService {
	public Hotel getOne(long id);
	public List<Hotel> getAll();
	public Hotel create(Hotel hotel);
	public Hotel update(Hotel hotel);
	public void delete(long id);
	public Hotel save(Hotel hotel);
}
