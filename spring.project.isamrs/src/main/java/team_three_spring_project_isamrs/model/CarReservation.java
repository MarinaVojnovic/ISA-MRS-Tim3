package team_three_spring_project_isamrs.model;

import team_three_spring_project_isamrs.dto.CarReservationDTO;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(catalog = "dbtim3", name = "car_reservation")
public class CarReservation {
	@Id
	@GeneratedValue
	private Long id;
	

	/*
	 * public FlightReservation getResFlight() { return resFlight; }
	 * 
	 * public void setResFlight(FlightReservation resFlight) { this.resFlight =
	 * resFlight; }
	 */

	@Column(name = "price")
	private Double price;
	
	@Column(name = "start_date")
	private Date startDate;
	
	@Column(name = "end_date")
	private Date endDate;
	
	@Column(name="num_of_pass")
	private Integer numOfPass;
	
	@ManyToOne(fetch = FetchType.EAGER)
	RegularUser regularUser;
	
	/*
	 * @ManyToOne(fetch=FetchType.EAGER) FlightReservation resFlight;
	 */
	
	@ManyToOne(fetch = FetchType.EAGER)
	Car car;

	@ManyToOne(fetch = FetchType.EAGER)
	Rentacar rentacarRes;
	
	public CarReservation() {
		
	}
	
	public CarReservation(Date startDate, Date endDate) {
		this.startDate=startDate;
		this.endDate=endDate;
	}
	public CarReservation(CarReservationDTO dto) {
		this.startDate=dto.getStartDate();
		this.endDate=dto.getEndDate();
		this.numOfPass=dto.getPassengers();
		
	}

	public Rentacar getRentacarRes() {
		return rentacarRes;
	}


	public void setRentacarRes(Rentacar rentacarRes) {
		this.rentacarRes = rentacarRes;
	}


	public RegularUser getRegularUser() {
		return regularUser;
	}


	public void setRegularUser(RegularUser regularUser) {
		this.regularUser = regularUser;
	}


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


	public Date getStartDate() {
		return startDate;
	}


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	public Date getEndDate() {
		return endDate;
	}


	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	public Integer getNumOfPass() {
		return numOfPass;
	}


	public void setNumOfPass(Integer numOfPass) {
		this.numOfPass = numOfPass;
	}


	public Car getCar() {
		return car;
	}


	public void setCar(Car car) {
		this.car = car;
	}
	
	
}
