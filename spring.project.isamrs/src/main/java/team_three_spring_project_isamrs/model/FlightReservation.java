package team_three_spring_project_isamrs.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(catalog = "dbtim3", name = "flight_reservation")
public class FlightReservation {
	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "price")
	private Double price;
	
	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	@Column(name="discount")
	private Integer discount;

	// vise rezervacija za let moze da ima jedan korisnik
	@ManyToOne(fetch = FetchType.EAGER)
	RegularUser regularUserFlightReservation;

	// vise rezervacija za let moze da ima jedan let
	@ManyToOne(fetch = FetchType.EAGER)
	Flight flightReservation;
	
	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "seat_id")
	private Seat seat;
	
	@Column(name="confirmed")
	Boolean confirmed;
	
	@Column(name="passportNum")
	int passportNum;
	
	@Column(name="dateSold")
	Date dateSold;
	
	@Column(name="name")
	String name;
	
	@Column(name="lastName")
	String lastName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public RegularUser getRegularUserFlightReservation() {
		return regularUserFlightReservation;
	}

	public void setRegularUserFlightReservation(RegularUser regularUserFlightReservation) {
		this.regularUserFlightReservation = regularUserFlightReservation;
	}

	public Flight getFlightReservation() {
		return flightReservation;
	}

	public void setFlightReservation(Flight flightReservation) {
		this.flightReservation = flightReservation;
	}

	public Seat getSeat() {
		return seat;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
	}

	public Boolean getConfirmed() {
		return confirmed;
	}

	public void setConfirmed(Boolean confirmed) {
		this.confirmed = confirmed;
	}

	public int getPassportNum() {
		return passportNum;
	}

	public void setPassportNum(int passportNum) {
		this.passportNum = passportNum;
	}

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public FlightReservation() {
		super();
	}

	public FlightReservation(Long id, Double price, RegularUser regularUserFlightReservation, Flight flightReservation,
			Seat seat, Boolean confirmed, int passportNum, Date dateSold, String name, String lastName) {
		super();
		this.id = id;
		this.price = price;
		this.regularUserFlightReservation = regularUserFlightReservation;
		this.flightReservation = flightReservation;
		this.seat = seat;
		this.confirmed = confirmed;
		this.passportNum = passportNum;
		this.dateSold = dateSold;
		this.name = name;
		this.lastName = lastName;
	}

	public FlightReservation(Double price, RegularUser regularUserFlightReservation, Flight flightReservation,
			Seat seat, Boolean confirmed, int passportNum, Date dateSold, String name, String lastName) {
		super();
		this.price = price;
		this.regularUserFlightReservation = regularUserFlightReservation;
		this.flightReservation = flightReservation;
		this.seat = seat;
		this.confirmed = confirmed;
		this.passportNum = passportNum;
		this.dateSold = dateSold;
		this.name = name;
		this.lastName = lastName;
	}

	public Date getDateSold() {
		return dateSold;
	}

	public void setDateSold(Date dateSold) {
		this.dateSold = dateSold;
	}
	
	
	

}
