package tim3.spring.project.isamrs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tim3.spring.project.isamrs.model.FriendRequest;
import tim3.spring.project.isamrs.model.RegularUser;
import tim3.spring.project.isamrs.repository.FriendRequestRepository;
import tim3.spring.project.isamrs.service.FriendRequestService;

@Service
public class FriendRequestServiceImpl implements FriendRequestService{
	
	@Autowired
	FriendRequestRepository friendRequestRepository;

	@Override
	public FriendRequest getOne(long id) {
		return friendRequestRepository.findOne(id);
	}

	@Override
	public List<FriendRequest> getAll() {
		return friendRequestRepository.findAll();
	}

	@Override
	public FriendRequest create(FriendRequest friendRequest) {
		return friendRequestRepository.save(friendRequest);
	}

	@Override
	public FriendRequest update(FriendRequest friendRequest) {
		return null;
	}

	@Override
	public void delete(long id) {
		friendRequestRepository.delete(friendRequestRepository.getOne(id));
		
	}

	@Override
	public FriendRequest save(FriendRequest friendRequest) {
		return friendRequestRepository.save(friendRequest);
	}

	@Override
	public List<FriendRequest> findByReceivedAndAccepted(RegularUser u, Boolean a) {
		return friendRequestRepository.findByReceivedAndAccepted(u, a);
	}

	@Override
	public FriendRequest findByReceivedAndSentAndAccepted(RegularUser u, RegularUser s, Boolean a) {
		return friendRequestRepository.findByReceivedAndSentAndAccepted(u, s, a);
	}
	
	@Override
	public List<FriendRequest> findBySentAndAccepted(RegularUser s, Boolean a) {
		return friendRequestRepository.findBySentAndAccepted(s, a);
	}
}
