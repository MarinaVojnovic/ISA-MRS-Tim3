package tim3.spring.project.isamrs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tim3.spring.project.isamrs.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findOneByUsername(String username);
	
	@Query(value = "select * from users u where u.user_id =(select t.user from verification_tokens t where t.token = ?1)", nativeQuery = true)
	User findByToken(String token);
}