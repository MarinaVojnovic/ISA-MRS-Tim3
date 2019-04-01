package tim3.spring.project.isamrs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tim3.spring.project.isamrs.model.RegisteredUser;
import tim3.spring.project.isamrs.repository.CarRepository;
import tim3.spring.project.isamrs.repository.RegisteredUserRepository;
import tim3.spring.project.isamrs.service.RegisteredUserService;

@Service
public class RegisteredUserServiceImpl implements RegisteredUserService{
	@Autowired
	RegisteredUserRepository registeredUserRepository;
	
	@Override
	public RegisteredUser getOne(long id) {
		// TODO Auto-generated method stub
		return registeredUserRepository.findOne(id);
	}

	@Override
	public List<RegisteredUser> getAll() {
		// TODO Auto-generated method stub
		return registeredUserRepository.findAll();
	}

	@Override
	public RegisteredUser create(RegisteredUser user) {
		// TODO Auto-generated method stub
		return registeredUserRepository.save(user);
	}

	@Override
	public RegisteredUser update(RegisteredUser user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(long id) {
		registeredUserRepository.delete(id);
		
	}

	@Override
	public RegisteredUser save(RegisteredUser user) {
		// TODO Auto-generated method stub
		return registeredUserRepository.save(user);
	}

	@Override
	public RegisteredUser findByUsernameAndPassword(String username, String password) {
		// TODO Auto-generated method stub
		return registeredUserRepository.findByUsernameAndPassword(username, password);
	}

}
