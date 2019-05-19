package team_three_spring_project_isamrs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team_three_spring_project_isamrs.model.Car;
import team_three_spring_project_isamrs.model.CarReservation;
import team_three_spring_project_isamrs.model.RegularUser;
import team_three_spring_project_isamrs.model.Rentacar;
import team_three_spring_project_isamrs.repository.CarReservationRepository;
import team_three_spring_project_isamrs.service.CarReservationService;

@Service
public class CarReservationServiceImpl implements CarReservationService {
	@Autowired
	CarReservationRepository carReservationRepository;

	public CarReservation getOne(long id) {
		return carReservationRepository.findOne(id);
	}
	
	public List<CarReservation> findByFlightId(long flightId) {
		return carReservationRepository.findByFlightId(flightId);
	}

	public List<CarReservation> getAll() {
		return carReservationRepository.findAll();
	}

	public CarReservation create(CarReservation car) {
		return carReservationRepository.save(car);
	}

	public void delete(long id) {
		carReservationRepository.delete(id);
	}

	public CarReservation save(CarReservation car) {
		return carReservationRepository.save(car);
	}

	public List<CarReservation> findByRegularUser(RegularUser regularUser) {
		return carReservationRepository.findByRegularUser(regularUser);
	}

	public List<CarReservation> findByRentacarRes(Rentacar rentacarRes) {
		return carReservationRepository.findByRentacarRes(rentacarRes);
	}

	public List<CarReservation> findByCar(Car car) {
		return carReservationRepository.findByCar(car);
	}
}
