package tim3.spring.project.isamrs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tim3.spring.project.isamrs.model.RegisteredUser;
import tim3.spring.project.isamrs.model.Rentacar;

@Repository
public interface RentacarRepository extends JpaRepository<Rentacar, Long> {
	
	public Rentacar findByName(String name);
	

}
