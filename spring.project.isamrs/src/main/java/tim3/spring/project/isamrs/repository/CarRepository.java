package tim3.spring.project.isamrs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tim3.spring.project.isamrs.model.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

	public Car findByName(String number);
}