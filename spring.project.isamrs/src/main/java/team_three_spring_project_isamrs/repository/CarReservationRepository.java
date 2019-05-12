package team_three_spring_project_isamrs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import team_three_spring_project_isamrs.model.Car;
import team_three_spring_project_isamrs.model.CarReservation;
import team_three_spring_project_isamrs.model.RegularUser;
import team_three_spring_project_isamrs.model.Rentacar;

@Repository
public interface CarReservationRepository extends JpaRepository<CarReservation, Long>{
	public List<CarReservation> findByRegularUser(RegularUser regularUser);
	public List<CarReservation> findByCar(Car car);
	public List<CarReservation> findByRentacarRes(Rentacar rentacarRes);
}
