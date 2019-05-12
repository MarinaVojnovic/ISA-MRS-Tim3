package team_three_spring_project_isamrs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import team_three_spring_project_isamrs.model.Car;
import team_three_spring_project_isamrs.model.Rentacar;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

	public Car findByName(String number);
	
	public List<Car> findByRentacar(Rentacar rentacar);
}
