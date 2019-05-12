package team_three_spring_project_isamrs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import team_three_spring_project_isamrs.model.Airline;

@Repository
public interface AirlineRepository extends JpaRepository<Airline, Long> {

	public List<Airline> findByName(String name);
	public List<Airline> findByAddress(String name);
}
