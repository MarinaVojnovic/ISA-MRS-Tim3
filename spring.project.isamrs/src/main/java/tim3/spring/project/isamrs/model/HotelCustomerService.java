package tim3.spring.project.isamrs.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import tim3.spring.project.isamrs.dto.HotelCustomerServiceDTO;

@Entity
@Table(catalog = "dbtim3", name = "hotel_customer_service")
public class HotelCustomerService {
	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "price")
	private Double price;

	@ManyToOne(fetch = FetchType.EAGER)
	Hotel hotel;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "hotelCustomerServices")
	private Set<RoomFastReservation> roomFastReservations = new HashSet<>();
	
	@JsonIgnore
	@ManyToMany(mappedBy = "hotelCustomerServices")
	private Set<RoomReservation> roomReservations = new HashSet<>();

	public HotelCustomerService() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public HotelCustomerService(Long id, String name, Double price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
	}

	public HotelCustomerService(HotelCustomerServiceDTO hcsDTO) {
		super();
		this.name = hcsDTO.getName();
		this.price = hcsDTO.getPrice();
	}

	public Set<RoomFastReservation> getRoomFastReservations() {
		return roomFastReservations;
	}

	public void setRoomFastReservations(Set<RoomFastReservation> roomFastReservations) {
		this.roomFastReservations = roomFastReservations;
	}

	public Set<RoomReservation> getRoomReservations() {
		return roomReservations;
	}

	public void setRoomReservations(Set<RoomReservation> roomReservations) {
		this.roomReservations = roomReservations;
	}
}
