package tim3.spring.project.isamrs.service;

import java.util.List;

import tim3.spring.project.isamrs.model.Car;
import tim3.spring.project.isamrs.model.CarReservation;
import tim3.spring.project.isamrs.model.RegularUser;
import tim3.spring.project.isamrs.model.Rentacar;

public interface CarReservationService {
	public CarReservation getOne(long id);
	public List<CarReservation> getAll();
	public CarReservation create(CarReservation car);
	public CarReservation update(CarReservation car);
	public void delete(long id);
	public CarReservation save(CarReservation car);
	public List<CarReservation> findByRegularUser(RegularUser regularUser);
	public List<CarReservation> findByRentacarRes(Rentacar rentacarRes);
	public List<CarReservation> findByCar(Car car);
}
