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
@Table(catalog = "dbtim3", name = "room_fast_reservation")
public class RoomFastReservation {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	private Room room;

	@Column(name = "start_date")
	private String startDate;

	@Column(name = "end_date")
	private String endDate;

	@Column(name = "reserved")
	private Boolean reserved;

	@Column(name = "original_price")
	private Double originalPrice;

	@Column(name = "discount")
	private Double discount;

	@Column(name = "new_price")
	private Double newPrice;

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "fast_service_room", joinColumns = @JoinColumn(name = "fast_reservation_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "hotel_customer_service_id", referencedColumnName = "id"))
	private Set<HotelCustomerService> hotelCustomerServices = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Double getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(Double originalPrice) {
		this.originalPrice = originalPrice;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Double getNewPrice() {
		return newPrice;
	}

	public void setNewPrice(Double newPrice) {
		this.newPrice = newPrice;
	}

	public Set<HotelCustomerService> getHotelCustomerServices() {
		return hotelCustomerServices;
	}

	public void setHotelCustomerServices(Set<HotelCustomerService> hotelCustomerServices) {
		this.hotelCustomerServices = hotelCustomerServices;
	}

	public RoomFastReservation() {
		super();
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Boolean getReserved() {
		return reserved;
	}

	public void setReserved(Boolean reserved) {
		this.reserved = reserved;
	}

	public RoomFastReservation(Long id, Room room, String startDate, String endDate, Boolean reserved,
			Double originalPrice, Double discount, Double newPrice, Set<HotelCustomerService> hotelCustomerServices) {
		super();
		this.id = id;
		this.room = room;
		this.startDate = startDate;
		this.endDate = endDate;
		this.reserved = reserved;
		this.originalPrice = originalPrice;
		this.discount = discount;
		this.newPrice = newPrice;
		this.hotelCustomerServices = hotelCustomerServices;
	}

}
