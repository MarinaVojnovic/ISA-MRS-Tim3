package tim3.spring.project.isamrs.dto;

public class InvitedFriendDTO {
	String email;
	Long reservationId;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getReservationId() {
		return reservationId;
	}
	public void setReservationId(Long reservationId) {
		this.reservationId = reservationId;
	}
	public InvitedFriendDTO(String email, Long reservationId) {
		super();
		this.email = email;
		this.reservationId = reservationId;
	}
	public InvitedFriendDTO() {
		super();
	}
	

}
