package team_three_spring_project_isamrs.service;

import java.util.List;

import team_three_spring_project_isamrs.model.Car;
import team_three_spring_project_isamrs.model.CarReservation;
import team_three_spring_project_isamrs.model.RegularUser;
import team_three_spring_project_isamrs.model.Rentacar;

public interface CarReservationService {
	public CarReservation getOne(long id);
	public List<CarReservation> getAll();
	public CarReservation create(CarReservation car);
	public void delete(long id);
	public CarReservation save(CarReservation car);
	public List<CarReservation> findByRegularUser(RegularUser regularUser);
	public List<CarReservation> findByRentacarRes(Rentacar rentacarRes);
	public List<CarReservation> findByCar(Car car);
}
