package team_three_spring_project_isamrs.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;


@Entity
@Table(catalog = "dbtim3", name = "seats")
public class Seat {
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	@JoinColumn
	Flight flight;
	
	@Column(name="taken")
	Boolean taken;
	
	@Column(name="quickBooking")
	Boolean quickBooking;
	
	@Column(name="class")
	FlightClass fc;
	
	@Column(name="discount")
	int discount;
	
	@Version
	private Long version;
	
	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	@OneToOne(mappedBy = "seat", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
	private FlightReservation flightReservation;

	public Seat(Long id, Flight flight, Boolean quickBooking,Boolean taken, FlightClass fc, FlightReservation flightReservation,int discount) {
		super();
		this.id = id;
		this.flight = flight;
		this.quickBooking=quickBooking;
		this.taken = taken;
		this.fc = fc;
		this.flightReservation = flightReservation;
		this.discount=discount;
	}

	public FlightReservation getFlightReservation() {
		return flightReservation;
	}

	public void setFlightReservation(FlightReservation flightReservation) {
		this.flightReservation = flightReservation;
	}

	public Seat(Long id, Flight flight, Boolean quickBooking, Boolean taken,FlightClass fc,int discount) {
		super();
		this.id = id;
		this.flight = flight;
		this.quickBooking=quickBooking;
		this.taken = taken;
		this.fc=fc;
		this.discount=discount;
	}

	public Seat() {
		super();
	}

	public Seat(Flight flight,Boolean quickBooking,Boolean taken,FlightClass fc,int discount) {
		super();
		this.flight = flight;
		this.quickBooking=quickBooking;
		this.taken = taken;
		this.fc=fc;
		this.discount=discount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Flight getFlight() {
		return flight;
	}


	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public Boolean getTaken() {
		return taken;
	}

	public void setTaken(Boolean taken) {
		this.taken = taken;
	}

	public FlightClass getFc() {
		return fc;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public void setFc(FlightClass fc) {
		this.fc = fc;
	}

	public Boolean getQuickBooking() {
		return quickBooking;
	}

	public void setQuickBooking(Boolean quickBooking) {
		this.quickBooking = quickBooking;
	}
	
	
	

}

