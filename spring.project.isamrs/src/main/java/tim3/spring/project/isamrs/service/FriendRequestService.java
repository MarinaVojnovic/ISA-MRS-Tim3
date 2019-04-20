package tim3.spring.project.isamrs.service;

import java.util.List;

import tim3.spring.project.isamrs.model.FriendRequest;
import tim3.spring.project.isamrs.model.RegularUser;

public interface FriendRequestService {
	public FriendRequest getOne(long id);

	public List<FriendRequest> getAll();

	public FriendRequest create(FriendRequest friendRequest);

	public FriendRequest update(FriendRequest friendRequest);

	public void delete(long id);
	
	public FriendRequest save(FriendRequest friendRequest);
	
	public List<FriendRequest> findByReceivedAndAccepted(RegularUser u, Boolean a);
	public FriendRequest findByReceivedAndSentAndAccepted(RegularUser u,RegularUser s, Boolean a);
	public List<FriendRequest> findBySentAndAccepted(RegularUser s, Boolean a);

}
