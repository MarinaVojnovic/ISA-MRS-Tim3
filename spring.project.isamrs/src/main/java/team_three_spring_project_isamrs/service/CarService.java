package team_three_spring_project_isamrs.service;

import java.util.List;

import team_three_spring_project_isamrs.model.Car;
import team_three_spring_project_isamrs.model.Rentacar;

public interface CarService {
	public Car getOne(long id);
	public List<Car> getAll();
	public Car create(Car car);
	public void delete(long id);
	public Car save(Car car);
	public List<Car> findByRentacar(Rentacar r);
}
