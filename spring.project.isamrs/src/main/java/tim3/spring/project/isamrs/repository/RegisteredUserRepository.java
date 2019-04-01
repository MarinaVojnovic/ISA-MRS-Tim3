package tim3.spring.project.isamrs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import tim3.spring.project.isamrs.model.RegisteredUser;

@Repository
public interface RegisteredUserRepository extends JpaRepository<RegisteredUser, Long>{
	public RegisteredUser findByUsername(String username);
	public RegisteredUser findByUsernameAndPassword(String username, String password);
}
