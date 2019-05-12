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
	
	@Column(name="class")
	FlightClass fc;
	
	@OneToOne(mappedBy = "seat", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
	private FlightReservation flightReservation;

	public Seat(Long id, Flight flight, Boolean taken, FlightClass fc, FlightReservation flightReservation) {
		super();
		this.id = id;
		this.flight = flight;
		this.taken = taken;
		this.fc = fc;
		this.flightReservation = flightReservation;
	}

	public FlightReservation getFlightReservation() {
		return flightReservation;
	}

	public void setFlightReservation(FlightReservation flightReservation) {
		this.flightReservation = flightReservation;
	}

	public Seat(Long id, Flight flight, Boolean taken,FlightClass fc) {
		super();
		this.id = id;
		this.flight = flight;
		this.taken = taken;
		this.fc=fc;
	}

	public Seat() {
		super();
	}

	public Seat(Flight flight,Boolean taken,FlightClass fc) {
		super();
		this.flight = flight;
		this.taken = taken;
		this.fc=fc;
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

	public void setFc(FlightClass fc) {
		this.fc = fc;
	}
	
	
	

}

