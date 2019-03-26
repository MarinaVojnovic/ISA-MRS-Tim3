package tim3.spring.project.isamrs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tim3.spring.project.isamrs.model.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long>{
	
	public Hotel findByName(String name);

}
