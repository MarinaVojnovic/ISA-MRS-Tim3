package tim3.spring.project.isamrs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tim3.spring.project.isamrs.model.Car;
import tim3.spring.project.isamrs.model.CarReservation;
import tim3.spring.project.isamrs.model.RegularUser;
import tim3.spring.project.isamrs.model.Rentacar;

@Repository
public interface CarReservationRepository extends JpaRepository<CarReservation, Long>{
	public List<CarReservation> findByRegularUser(RegularUser regularUser);
	public List<CarReservation> findByCar(Car car);
	public List<CarReservation> findByRentacarRes(Rentacar rentacarRes);
}
