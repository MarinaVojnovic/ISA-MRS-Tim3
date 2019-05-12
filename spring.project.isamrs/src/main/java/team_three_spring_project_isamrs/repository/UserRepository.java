package team_three_spring_project_isamrs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import team_three_spring_project_isamrs.model.RegularUser;
import team_three_spring_project_isamrs.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findOneByUsername(String username);

	@Query(value = "select * from users u where u.id =(select t.user from verification_tokens t where t.token = ?1)", nativeQuery = true)
	User findByToken(String token);

	public List<RegularUser> findByFirstNameAndLastName(String name, String surname);

	public List<RegularUser> findByLastName(String surname);

	public List<RegularUser> findByFirstName(String name);

	public User findByUsername(String username);
}