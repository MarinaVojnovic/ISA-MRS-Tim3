package team_three_spring_project_isamrs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import team_three_spring_project_isamrs.dto.FriendRequestDTO;

@Entity
@Table(catalog = "dbtim3", name = "friendRequest")
public class FriendRequest {
	@Id
	@GeneratedValue
	private Long id;

	// vise zahteva moze da posalje jedan korisnik
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "sent_id")
	private RegularUser sent;

	// vise zahteva moze biti poslato jednom korisniku
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "received_id")
	private RegularUser received;

	@Column(name = "accepted")
	private Boolean accepted;

	public FriendRequest(FriendRequestDTO dto) {
		super();
		this.sent = dto.getSenderId();
		this.received = dto.getReceiverId();
		this.accepted = dto.getAccepted();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public FriendRequest() {
		super();
	}

	public RegularUser getSent() {
		return sent;
	}

	public void setSent(RegularUser sent) {
		this.sent = sent;
	}

	public RegularUser getReceived() {
		return received;
	}

	public void setReceived(RegularUser received) {
		this.received = received;
	}

	public Boolean getAccepted() {
		return accepted;
	}

	public void setAccepted(Boolean accepted) {
		this.accepted = accepted;
	}

}
