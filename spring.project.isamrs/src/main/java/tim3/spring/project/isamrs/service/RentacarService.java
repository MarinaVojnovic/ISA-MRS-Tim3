package tim3.spring.project.isamrs.service;

import java.util.List;

import tim3.spring.project.isamrs.model.Rentacar;

public interface RentacarService {
	public Rentacar getOne(long id);

	public List<Rentacar> getAll();

	public Rentacar create(Rentacar rentacar);

	public Rentacar update(Rentacar rentacar);

	public void delete(long id);
}
