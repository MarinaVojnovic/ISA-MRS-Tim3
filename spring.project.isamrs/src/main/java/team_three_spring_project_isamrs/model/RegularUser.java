package team_three_spring_project_isamrs.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity

public class RegularUser extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1970215241208904436L;
	// jedan korisnik moze da primi vie zahteva

	@JsonIgnore
	@OneToMany(mappedBy = "received", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private List<FriendRequest> received = new ArrayList<>();

	public Set<RoomReservation> getRoomReservations() {
		return roomReservations;
	}

	public void setRoomReservations(Set<RoomReservation> roomReservations) {
		this.roomReservations = roomReservations;
	}

	// vise zahteva moze biti poslato od jednog korisnika
	@JsonIgnore
	@OneToMany(mappedBy = "sent", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private List<FriendRequest> sent = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy = "regularUser", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private List<CarReservation> carReservations = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy = "regularUser", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<RoomReservation> roomReservations = new HashSet<>();

	@JsonIgnore
	@OneToMany(mappedBy = "regularUserFlightReservation", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private List<FlightReservation> flightReservations = new ArrayList<>();

	public List<FlightReservation> getFlightReservations() {
		return flightReservations;
	}

	public void setFlightReservations(List<FlightReservation> flightReservations) {
		this.flightReservations = flightReservations;
	}

	public List<FriendRequest> getReceived() {
		return received;
	}

	public void setReceived(List<FriendRequest> received) {
		this.received = received;
	}

	public List<FriendRequest> getSent() {
		return sent;
	}

	public void setSent(List<FriendRequest> sent) {
		this.sent = sent;
	}

	public List<CarReservation> getCarReservations() {
		return carReservations;
	}

	public void setCarReservations(List<CarReservation> carReservations) {
		this.carReservations = carReservations;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
