package tim3.spring.project.isamrs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tim3.spring.project.isamrs.model.RegularUser;
import tim3.spring.project.isamrs.repository.RegularUserRepository;
import tim3.spring.project.isamrs.service.RegularUserService;

@Service
public class RegularUserServiceImpl implements RegularUserService{
	
	@Autowired
	private RegularUserRepository regularUserRepository;
	
	
	@Override
	public RegularUser findById(Long id) {
		return regularUserRepository.getOne(id);
	}

}
