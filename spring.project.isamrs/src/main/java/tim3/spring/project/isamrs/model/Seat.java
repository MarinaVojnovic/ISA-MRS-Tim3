package tim3.spring.project.isamrs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(catalog = "dbtim3", name = "seats")
public class Seat {
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	Flight flight;
	
	@Column(name="taken")
	Boolean taken;

	public Seat(Long id, Flight flight, Boolean taken) {
		super();
		this.id = id;
		this.flight = flight;
		this.taken = taken;
	}

	public Seat() {
		super();
	}

	public Seat(Flight flight,Boolean taken) {
		super();
		this.flight = flight;
		this.taken = taken;
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
	
	

}

