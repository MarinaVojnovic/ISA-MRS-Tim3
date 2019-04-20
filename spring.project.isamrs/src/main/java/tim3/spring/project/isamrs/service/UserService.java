package tim3.spring.project.isamrs.service;

import java.util.List;

import tim3.spring.project.isamrs.model.RegularUser;
import tim3.spring.project.isamrs.model.User;

public interface UserService {
    User findById(Long id);
    User findByUsername(String username);
    List<User> findAll ();
    List<RegularUser> findByFirstNameAndLastName(String name,String surname);
    List<RegularUser> findByFirstName(String name);
    List<RegularUser> findByLastName(String surname);
    String editProfile(User user);
    User findUserByToken(String token);
    void save(User user);
}
