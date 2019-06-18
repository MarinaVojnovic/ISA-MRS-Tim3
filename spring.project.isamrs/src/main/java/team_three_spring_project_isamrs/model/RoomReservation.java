package team_three_spring_project_isamrs.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(catalog = "dbtim3", name = "room_reservation")
public class RoomReservation {
	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "price")
	private Double price;

	@Column(name = "discount")
	private Double discount;

	@Column(name = "start_date")
	private String startDate;

	@Column(name = "end_date")
	private String endDate;

	@Column(name = "num_of_pass")
	private Integer numOfPass;

	@ManyToOne(fetch = FetchType.EAGER)
	private RegularUser regularUser;

	@Column(name = "fast_room_id")
	private Long roomFastReservationId;

	@Column(name = "fast_reserved")
	private Boolean fastReserved;

	@Column(name = "flight_id")
	private Long flightId;

	@ManyToOne(fetch = FetchType.EAGER)
	Hotel hotel;

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "room_room_reservation", joinColumns = @JoinColumn(name = "room_reservation_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "room_id", referencedColumnName = "id"))
	private Set<Room> rooms = new HashSet<>();

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "reservation_service_room", joinColumns = @JoinColumn(name = "room_reservation_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "hotel_customer_service_id", referencedColumnName = "id"))
	private Set<HotelCustomerService> hotelCustomerServices = new HashSet<>();

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

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public Integer getNumOfPass() {
		return numOfPass;
	}

	public void setNumOfPass(Integer numOfPass) {
		this.numOfPass = numOfPass;
	}

	public RegularUser getRegularUser() {
		return regularUser;
	}

	public void setRegularUser(RegularUser regularUser) {
		this.regularUser = regularUser;
	}

	public Long getRoomFastReservationId() {
		return roomFastReservationId;
	}

	public void setRoomFastReservationId(Long roomFastReservationId) {
		this.roomFastReservationId = roomFastReservationId;
	}

	public Boolean getFastReserved() {
		return fastReserved;
	}

	public void setFastReserved(Boolean fastReserved) {
		this.fastReserved = fastReserved;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public Set<Room> getRooms() {
		return rooms;
	}

	public void setRooms(Set<Room> rooms) {
		this.rooms = rooms;
	}

	public Set<HotelCustomerService> getHotelCustomerServices() {
		return hotelCustomerServices;
	}

	public void setHotelCustomerServices(Set<HotelCustomerService> hotelCustomerServices) {
		this.hotelCustomerServices = hotelCustomerServices;
	}

	public RoomReservation(Long id, Double price, String startDate, String endDate, Integer numOfPass,
			RegularUser regularUser, Long roomFastReservationId, Boolean fastReserved, Hotel hotel, Set<Room> rooms,
			Set<HotelCustomerService> hotelCustomerServices, Double discount) {
		super();
		this.id = id;
		this.price = price;
		this.startDate = startDate;
		this.endDate = endDate;
		this.numOfPass = numOfPass;
		this.regularUser = regularUser;
		this.roomFastReservationId = roomFastReservationId;
		this.fastReserved = fastReserved;
		this.hotel = hotel;
		this.rooms = rooms;
		this.hotelCustomerServices = hotelCustomerServices;
		this.discount = discount;
	}

	public RoomReservation() {
		super();
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Long getFlightId() {
		return flightId;
	}

	public void setFlightId(Long flightId) {
		this.flightId = flightId;
	}
}
