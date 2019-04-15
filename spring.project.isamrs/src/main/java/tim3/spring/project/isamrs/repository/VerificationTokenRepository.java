package tim3.spring.project.isamrs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tim3.spring.project.isamrs.model.VerificationToken;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
}
