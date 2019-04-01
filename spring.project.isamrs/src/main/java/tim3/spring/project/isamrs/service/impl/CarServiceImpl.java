package tim3.spring.project.isamrs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tim3.spring.project.isamrs.model.Car;
import tim3.spring.project.isamrs.model.Rentacar;
import tim3.spring.project.isamrs.repository.CarRepository;
import tim3.spring.project.isamrs.service.CarService;


@Service
public class CarServiceImpl implements CarService{
	@Autowired
	CarRepository carRepository;

	@Override
	public Car getOne(long id) {
		// TODO Auto-generated method stub
		return carRepository.findOne(id);
	}

	@Override
	public List<Car> getAll() {
		// TODO Auto-generated method stub
		return carRepository.findAll();
	}

	@Override
	public Car create(Car car) {
		// TODO Auto-generated method stub
		return carRepository.save(car);
	}

	@Override
	public Car update(Car car) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(long id) {
		carRepository.delete(id);
		
	}
	
	@Override
	public Car save(Car car) {
		return carRepository.save(car);
	}

}