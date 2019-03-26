package tim3.spring.project.isamrs.service;

import java.util.List;

import tim3.spring.project.isamrs.model.Car;

public interface CarService {
	public Car getOne(long id);
	public List<Car> getAll();
	public Car create(Car car);
	public Car update(Car car);
	public void delete(long id);
}
