package tim3.spring.project.isamrs.service;

import java.util.List;

import tim3.spring.project.isamrs.model.Car;
import tim3.spring.project.isamrs.model.Rentacar;

public interface CarService {
	public Car getOne(long id);
	public List<Car> getAll();
	public Car create(Car car);
	public Car update(Car car);
	public void delete(long id);
	public Car save(Car car);
	public List<Car> findByRentacar(Rentacar r);
}
