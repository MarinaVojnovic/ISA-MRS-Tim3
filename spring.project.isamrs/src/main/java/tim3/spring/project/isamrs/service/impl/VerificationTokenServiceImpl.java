package tim3.spring.project.isamrs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tim3.spring.project.isamrs.model.VerificationToken;
import tim3.spring.project.isamrs.repository.VerificationTokenRepository;
import tim3.spring.project.isamrs.service.VerificationTokenService;

@Service
public class VerificationTokenServiceImpl implements VerificationTokenService{

	@Autowired
	private VerificationTokenRepository verificationTokenRepository;
	
	@Override
	public void saveToken(VerificationToken token) {
		verificationTokenRepository.save(token);
		
	}

}
