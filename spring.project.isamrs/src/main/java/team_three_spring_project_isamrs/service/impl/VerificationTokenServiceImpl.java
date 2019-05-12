package team_three_spring_project_isamrs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team_three_spring_project_isamrs.model.VerificationToken;
import team_three_spring_project_isamrs.repository.VerificationTokenRepository;
import team_three_spring_project_isamrs.service.VerificationTokenService;

@Service
public class VerificationTokenServiceImpl implements VerificationTokenService {

	@Autowired
	private VerificationTokenRepository verificationTokenRepository;

	@Override
	public void saveToken(VerificationToken token) {
		verificationTokenRepository.save(token);

	}

}
