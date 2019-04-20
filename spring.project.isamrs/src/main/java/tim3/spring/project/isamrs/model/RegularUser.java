package tim3.spring.project.isamrs.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;

@Entity

public class RegularUser extends User{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1970215241208904436L;
	//jedan korisnik moze da primi vie zahteva
	
	@JsonIgnore
	@OneToMany(mappedBy = "received",fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
    private List<FriendRequest> received = new ArrayList<FriendRequest>();
	
	//vise zahteva moze biti poslato od jednog korisnika
	@JsonIgnore
	@OneToMany(mappedBy = "sent",fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
    private List<FriendRequest> sent = new ArrayList<FriendRequest>();
	
	
	
//	@OneToOne(mappedBy = "sent")
//	FriendRequest sent;
//	
//	@OneToOne(mappedBy = "received")
//	FriendRequest received;
	
	

}
