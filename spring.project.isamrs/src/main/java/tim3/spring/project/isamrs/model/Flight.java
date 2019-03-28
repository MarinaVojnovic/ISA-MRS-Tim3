package tim3.spring.project.isamrs.model;

import java.util.Date;

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

import tim3.spring.project.isamrs.dto.FlightDTO;

@Entity
@Table(catalog = "dbtim3", name = "flight")
public class Flight {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "number")
	private String number;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "start_id")
	Destination startDestination;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "final_id")
	Destination finalDestination;

	@Column(name = "cost")
	private double cost;

	@Column(name = "date")
	private Date date;

	@ManyToOne(fetch = FetchType.EAGER)
	Airline airline;

	public Flight(FlightDTO flightDTO) {
		number = flightDTO.getFlightNumberRegister();
		startDestination = new Destination(flightDTO.getStartDestinationRegister());
		finalDestination = new Destination(flightDTO.getFinalDestinationRegister());
		cost = flightDTO.getCostOfFlight();
		date = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Airline getAirline() {
		return airline;
	}

	public Destination getStartDestination() {
		return startDestination;
	}

	public void setStartDestination(Destination startDestination) {
		this.startDestination = startDestination;
	}

	public Destination getFinalDestination() {
		return finalDestination;
	}

	public void setFinalDestination(Destination finalDestination) {
		this.finalDestination = finalDestination;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setAirline(Airline airline) {
		this.airline = airline;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((airline == null) ? 0 : airline.hashCode());
		long temp;
		temp = Double.doubleToLongBits(cost);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((finalDestination == null) ? 0 : finalDestination.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		result = prime * result + ((startDestination == null) ? 0 : startDestination.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Flight other = (Flight) obj;
		if (airline == null) {
			if (other.airline != null)
				return false;
		} else if (!airline.equals(other.airline))
			return false;
		if (Double.doubleToLongBits(cost) != Double.doubleToLongBits(other.cost))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (finalDestination == null) {
			if (other.finalDestination != null)
				return false;
		} else if (!finalDestination.equals(other.finalDestination))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		if (startDestination == null) {
			if (other.startDestination != null)
				return false;
		} else if (!startDestination.equals(other.startDestination))
			return false;
		return true;
	}
}
