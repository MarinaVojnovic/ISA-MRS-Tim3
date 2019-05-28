package team_three_spring_project_isamrs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import team_three_spring_project_isamrs.model.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long>{
	
	public List<Hotel> findByName(String name);
	public List<Hotel> findByAddress(String name);
	public List<Hotel> findByCity(String city);

}
