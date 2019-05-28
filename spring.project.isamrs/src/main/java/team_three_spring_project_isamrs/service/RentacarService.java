package team_three_spring_project_isamrs.service;

import java.util.List;

import team_three_spring_project_isamrs.model.Rentacar;

public interface RentacarService {
	public Rentacar getOne(long id);

	public List<Rentacar> getAll();

	public Rentacar create(Rentacar rentacar);

	public Rentacar save(Rentacar rentacar);

	public List<Rentacar> findByName(String name);
	
	public List<Rentacar> findByCity(String city);

	public List<Rentacar> findByAddress(String address);
}
