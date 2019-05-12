package team_three_spring_project_isamrs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import team_three_spring_project_isamrs.model.VerificationToken;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
}
