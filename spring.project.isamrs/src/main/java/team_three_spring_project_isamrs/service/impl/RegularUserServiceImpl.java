package team_three_spring_project_isamrs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team_three_spring_project_isamrs.model.RegularUser;
import team_three_spring_project_isamrs.repository.RegularUserRepository;
import team_three_spring_project_isamrs.service.RegularUserService;

@Service
public class RegularUserServiceImpl implements RegularUserService {

	@Autowired
	private RegularUserRepository regularUserRepository;

	@Override
	public RegularUser findById(Long id) {
		return regularUserRepository.getOne(id);
	}

}
