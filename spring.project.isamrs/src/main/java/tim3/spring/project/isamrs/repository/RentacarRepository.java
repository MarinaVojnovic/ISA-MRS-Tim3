package tim3.spring.project.isamrs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tim3.spring.project.isamrs.model.Rentacar;

@Repository
public interface RentacarRepository extends JpaRepository<Rentacar, Long> {
	
    public List<Rentacar> findByName(String name);
	
	
	public List<Rentacar> findByAddress(String address);

	

}
