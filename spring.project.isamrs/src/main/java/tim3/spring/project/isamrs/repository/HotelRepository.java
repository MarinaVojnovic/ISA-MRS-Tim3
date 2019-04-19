package tim3.spring.project.isamrs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tim3.spring.project.isamrs.model.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long>{
	
	public List<Hotel> findByName(String name);
	public List<Hotel> findByAddress(String name);

}
