package team_three_spring_project_isamrs.service;

import java.util.List;

import team_three_spring_project_isamrs.model.FriendRequest;
import team_three_spring_project_isamrs.model.RegularUser;

public interface FriendRequestService {
	public FriendRequest getOne(long id);

	public List<FriendRequest> getAll();

	public FriendRequest create(FriendRequest friendRequest);

	public void delete(long id);

	public FriendRequest save(FriendRequest friendRequest);

	public List<FriendRequest> findByReceivedAndAccepted(RegularUser u, Boolean a);

	public FriendRequest findByReceivedAndSentAndAccepted(RegularUser u, RegularUser s, Boolean a);

	public List<FriendRequest> findBySentAndAccepted(RegularUser s, Boolean a);

}
