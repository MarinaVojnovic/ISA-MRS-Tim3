package team_three_spring_project_isamrs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team_three_spring_project_isamrs.model.Car;
import team_three_spring_project_isamrs.model.Rentacar;
import team_three_spring_project_isamrs.repository.CarRepository;
import team_three_spring_project_isamrs.service.CarService;

@Service
public class CarServiceImpl implements CarService {
	@Autowired
	CarRepository carRepository;

	@Override
	public Car getOne(long id) {
		return carRepository.findOne(id);
	}

	@Override
	public List<Car> getAll() {
		return carRepository.findAll();
	}

	@Override
	public Car create(Car car) {
		return carRepository.save(car);
	}

	@Override
	public void delete(long id) {
		carRepository.delete(id);

	}

	@Override
	public Car save(Car car) {
		return carRepository.save(car);
	}

	public List<Car> findByRentacar(Rentacar r) {
		return carRepository.findByRentacar(r);
	}

}