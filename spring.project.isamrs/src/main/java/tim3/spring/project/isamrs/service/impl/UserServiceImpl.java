package tim3.spring.project.isamrs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tim3.spring.project.isamrs.model.RegularUser;
import tim3.spring.project.isamrs.model.User;
import tim3.spring.project.isamrs.repository.UserRepository;
import tim3.spring.project.isamrs.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	public User findUserByToken(String token){
		return userRepository.findByToken(token);
	}
	
	public void save(User user) {
		userRepository.save(user);
	}

	public String editProfile(User user) {
		User userToEdit = userRepository.findOneByUsername(user.getUsername());
		if (userToEdit == null) {
			return "User with given username does not exist.";
		}

		String firstName = user.getFirstName();
		if (firstName != null) {
			userToEdit.setFirstName(firstName);
		}

		String lastName = user.getLastName();
		if (lastName != null) {
			userToEdit.setLastName(lastName);
		}

		String phoneNumber = user.getPhoneNumber();
		if (phoneNumber != null) {
			userToEdit.setPhoneNumber(user.getPhoneNumber());
		}

		try {
			userRepository.save(userToEdit);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "Database error.";
		}

		return null;
	}

	@Override
	public User findById(Long id) {
		return userRepository.getOne(id);
	}

	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RegularUser> findByFirstNameAndLastName(String name, String surname) {
		return userRepository.findByFirstNameAndLastName(name, surname);
	}

	@Override
	public List<RegularUser> findByFirstName(String name) {
		// TODO Auto-generated method stub
		return userRepository.findByFirstName(name);
	}

	@Override
	public List<RegularUser> findByLastName(String surname) {
		return userRepository.findByLastName(surname);
	}

}
