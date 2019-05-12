package team_three_spring_project_isamrs.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import team_three_spring_project_isamrs.model.Airline;
import team_three_spring_project_isamrs.model.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

	public Flight findByNumber(String number);

	public List<Flight> findByStartAirlineAndFinalAirlineAndDateOfStartAndDateOfEnd(Airline start, Airline end,
			Date startD, Date endD);

	public List<Flight> findByStartAirlineAndFinalAirlineAndDateOfStart(Airline start, Airline end, Date startD);

	public List<Flight> findByStartAirlineAndFinalAirline(Airline start, Airline end);

}
