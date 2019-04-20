package tim3.spring.project.isamrs.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import tim3.spring.project.isamrs.model.RegularUser;
import tim3.spring.project.isamrs.model.User;

import org.springframework.stereotype.Repository;

@Repository
public interface RegularUserRepository extends JpaRepository<RegularUser, Long> {
	

}
