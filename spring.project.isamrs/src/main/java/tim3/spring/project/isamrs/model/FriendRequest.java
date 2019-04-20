package tim3.spring.project.isamrs.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import tim3.spring.project.isamrs.dto.FriendRequestDTO;


@Entity
@Table(catalog = "dbtim3", name = "friendRequest")
public class FriendRequest {
	@Id
	@GeneratedValue
	private Long id;
	
	//vise zahteva moze da posalje jedan korisnik
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sent_id")
    private RegularUser sent;
	
	
	//vise zahteva moze biti poslato jednom korisniku
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="received_id")
    private RegularUser received;
	
	@Column(name = "accepted")
	private Boolean accepted;
	
	

	public FriendRequest(FriendRequestDTO dto) {
		super();
		this.sent=dto.getSenderId();
		this.received=dto.getReceiverId();
		this.accepted=dto.getAccepted();
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
	
	
	
	
//	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	@JoinColumn(name = "sent_id")
//	RegularUser sent;
//	
//	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	@JoinColumn(name = "received_id")
//	RegularUser received;
//	
//	@Column(name = "accepted")
//	private Boolean accepted;
//
//	public FriendRequest() {
//		super();
//	}
//
//	public FriendRequest(Long id, RegularUser sent, RegularUser received, Boolean accepted) {
//		super();
//		this.id = id;
//		this.sent = sent;
//		this.received = received;
//		this.accepted = accepted;
//	}
//
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public RegularUser getSent() {
//		return sent;
//	}
//
//	public void setSent(RegularUser sent) {
//		this.sent = sent;
//	}
//
//	public RegularUser getReceived() {
//		return received;
//	}
//
//	public void setReceived(RegularUser received) {
//		this.received = received;
//	}
//
//	public Boolean getAccepted() {
//		return accepted;
//	}
//
//	public void setAccepted(Boolean accepted) {
//		this.accepted = accepted;
//	}
//
//	public FriendRequest(FriendRequestDTO dto) {
//		this.received=dto.getReceiverId();
//		this.sent=dto.getSenderId();
//		this.accepted=dto.getAccepted();
//	}
//	
//	

}
