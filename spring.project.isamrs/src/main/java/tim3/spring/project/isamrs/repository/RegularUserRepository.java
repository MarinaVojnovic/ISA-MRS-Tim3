package tim3.spring.project.isamrs.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tim3.spring.project.isamrs.model.RegularUser;

@Repository
public interface RegularUserRepository extends JpaRepository<RegularUser, Long> {
	

}
