package tim3.spring.project.isamrs.dto;

import tim3.spring.project.isamrs.model.RegularUser;

public class FriendRequestDTO {
	RegularUser senderId;
	RegularUser receiverId;
	Boolean accepted;
	public FriendRequestDTO(RegularUser senderId, RegularUser receiverId, Boolean accepted) {
		super();
		this.senderId = senderId;
		this.receiverId = receiverId;
		this.accepted = accepted;
	}
	public RegularUser getSenderId() {
		return senderId;
	}
	public void setSenderId(RegularUser senderId) {
		this.senderId = senderId;
	}
	public RegularUser getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(RegularUser receiverId) {
		this.receiverId = receiverId;
	}
	public Boolean getAccepted() {
		return accepted;
	}
	public void setAccepted(Boolean accepted) {
		this.accepted = accepted;
	}
	
	
	
	

}
