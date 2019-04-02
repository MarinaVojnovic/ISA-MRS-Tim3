package tim3.spring.project.isamrs.service;

import java.util.List;

import tim3.spring.project.isamrs.model.RegisteredUser;

public interface RegisteredUserService {
	public RegisteredUser getOne(long id);
	public List<RegisteredUser> getAll();
	public RegisteredUser create(RegisteredUser user);
	public RegisteredUser update(RegisteredUser user);
	public void delete(long id);
	public RegisteredUser save(RegisteredUser user);
	public RegisteredUser findByUsernameAndPassword(String username, String password);
}
