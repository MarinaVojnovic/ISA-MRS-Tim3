package tim3.spring.project.isamrs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tim3.spring.project.isamrs.model.Destination;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, Long>{
	public Destination findByName(String name);
}