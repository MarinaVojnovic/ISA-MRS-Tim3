package tim3.spring.project.isamrs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tim3.spring.project.isamrs.model.FriendRequest;
import tim3.spring.project.isamrs.model.RegularUser;

@Repository
public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long> {
	public List<FriendRequest> findByReceivedAndAccepted(RegularUser u, Boolean a);
	public FriendRequest findByReceivedAndSentAndAccepted(RegularUser u,RegularUser s, Boolean a);
	public List<FriendRequest> findBySentAndAccepted(RegularUser s, Boolean a);
}
