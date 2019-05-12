package team_three_spring_project_isamrs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import team_three_spring_project_isamrs.model.Airline;
import team_three_spring_project_isamrs.model.AirlineWorkingDestinations;

@Repository
public interface AirlineWorkingRepository extends JpaRepository<AirlineWorkingDestinations, Long> {

	public List<AirlineWorkingDestinations> findByAirlineThatWorks(Airline air);
}
