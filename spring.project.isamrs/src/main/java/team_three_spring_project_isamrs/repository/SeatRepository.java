package team_three_spring_project_isamrs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import team_three_spring_project_isamrs.model.Flight;
import team_three_spring_project_isamrs.model.FlightClass;
import team_three_spring_project_isamrs.model.Seat;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long>{
	public List<Seat> findByFlightAndFc(Flight fs,FlightClass fc);

}
