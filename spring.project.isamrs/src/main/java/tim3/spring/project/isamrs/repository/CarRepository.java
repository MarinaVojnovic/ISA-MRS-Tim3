package tim3.spring.project.isamrs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tim3.spring.project.isamrs.model.Car;
import tim3.spring.project.isamrs.model.Rentacar;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

	public Car findByName(String number);
	
	public List<Car> findByRentacar(Rentacar rentacar);
}
