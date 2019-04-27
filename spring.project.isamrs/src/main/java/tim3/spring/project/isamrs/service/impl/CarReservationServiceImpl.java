package tim3.spring.project.isamrs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tim3.spring.project.isamrs.model.Car;
import tim3.spring.project.isamrs.model.CarReservation;
import tim3.spring.project.isamrs.model.RegularUser;
import tim3.spring.project.isamrs.model.Rentacar;
import tim3.spring.project.isamrs.repository.CarRepository;
import tim3.spring.project.isamrs.repository.CarReservationRepository;
import tim3.spring.project.isamrs.service.CarReservationService;

@Service
public class CarReservationServiceImpl implements CarReservationService{
	@Autowired
	CarReservationRepository carReservationRepository;
	public CarReservation getOne(long id) {
		return carReservationRepository.findOne(id);
	}
	public List<CarReservation> getAll(){
		return carReservationRepository.findAll();
	}
	public CarReservation create(CarReservation car) {
		return carReservationRepository.save(car);
	}
	public CarReservation update(CarReservation car) {
		return null;
	}
	public void delete(long id) {
		carReservationRepository.delete(id);
	}
	public CarReservation save(CarReservation car) {
		return carReservationRepository.save(car);
	}
	public List<CarReservation> findByRegularUser(RegularUser regularUser){
		return carReservationRepository.findByRegularUser(regularUser);
	}
	public List<CarReservation> findByRentacarRes(Rentacar rentacarRes){
		return carReservationRepository.findByRentacarRes(rentacarRes);
	}
	public List<CarReservation> findByCar(Car car){
		return carReservationRepository.findByCar(car);
	}
}
