package team_three_spring_project_isamrs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import team_three_spring_project_isamrs.model.FlightStops;

@Repository
public interface FlightStopRepository extends JpaRepository<FlightStops, Long>{

}
