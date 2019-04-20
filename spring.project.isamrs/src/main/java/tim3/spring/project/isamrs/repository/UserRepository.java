package tim3.spring.project.isamrs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tim3.spring.project.isamrs.model.RegularUser;
import tim3.spring.project.isamrs.model.User;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	User findOneByUsername(String username);
	
	@Query(value = "select * from users u where u.id =(select t.user from verification_tokens t where t.token = ?1)", nativeQuery = true)
	User findByToken(String token);
	
	public List<RegularUser> findByFirstNameAndLastName(String name, String surname);
	public List<RegularUser> findByLastName(String surname);
	public List<RegularUser> findByFirstName(String name);
}