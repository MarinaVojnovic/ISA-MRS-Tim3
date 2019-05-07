package tim3.spring.project.isamrs.dto;

import java.util.ArrayList;
import java.util.List;

public class FlightReservationReturnDTO {
	List<InvitedFriendDTO> invitedFriends=new ArrayList<>();
	long idFlightReservation;
	
	public FlightReservationReturnDTO(List<InvitedFriendDTO> invitedFriends, long idFlightReservation) {
		super();
		this.invitedFriends = invitedFriends;
		this.idFlightReservation = idFlightReservation;
	}

	public List<InvitedFriendDTO> getInvitedFriends() {
		return invitedFriends;
	}

	public void setInvitedFriends(List<InvitedFriendDTO> invitedFriends) {
		this.invitedFriends = invitedFriends;
	}

	public long getIdFlightReservation() {
		return idFlightReservation;
	}

	public void setIdFlightReservation(long idFlightReservation) {
		this.idFlightReservation = idFlightReservation;
	}

	public FlightReservationReturnDTO() {
		super();
	}
	
	

}
