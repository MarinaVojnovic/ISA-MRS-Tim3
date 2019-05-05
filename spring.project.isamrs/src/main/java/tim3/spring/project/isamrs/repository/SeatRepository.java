package tim3.spring.project.isamrs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tim3.spring.project.isamrs.model.Flight;
import tim3.spring.project.isamrs.model.FlightClass;
import tim3.spring.project.isamrs.model.Hotel;
import tim3.spring.project.isamrs.model.Seat;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long>{
	public List<Seat> findByFlightAndFc(Flight fs,FlightClass fc);

}
