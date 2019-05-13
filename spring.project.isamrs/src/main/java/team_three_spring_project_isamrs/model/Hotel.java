package team_three_spring_project_isamrs.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;

import team_three_spring_project_isamrs.dto.HotelDTO;

@Entity
@Table(catalog = "dbtim3", name = "hotel")
public class Hotel {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "score")
	private Double score;

	@Column(name = "city")
	private String city;

	@Column(name = "grade_number")
	private Integer gradeNumber;

	@JsonIgnore
	@OneToMany(mappedBy = "hotelAdmin", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<HotelAdmin> hotelAdmins = new HashSet<>();

	@JsonIgnore
	@OneToMany(mappedBy = "hotel", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<RoomReservation> roomReservations = new HashSet<>();

	@Column(name = "name")
	private String name;

	@Column(name = "address")
	private String address;

	@Column(name = "promotional_description")
	private String promotionalDescription;

	@JsonIgnore
	@OneToMany(mappedBy = "hotel", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<HotelCustomerService> hotelCustomerServices = new HashSet<>();

	@JsonIgnore
	@OneToMany(mappedBy = "hotel", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<Room> rooms = new HashSet<>();

	@Version
	private Long version;

	public Hotel() {
		super();
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPromotionalDescription() {
		return promotionalDescription;
	}

	public void setPromotionalDescription(String promotionalDescription) {
		this.promotionalDescription = promotionalDescription;
	}

	public Set<HotelCustomerService> getHotelCustomerServices() {
		return hotelCustomerServices;
	}

	public void setHotelCustomerServices(Set<HotelCustomerService> hotelCustomerServices) {
		this.hotelCustomerServices = hotelCustomerServices;
	}

	public Set<Room> getRooms() {
		return rooms;
	}

	public void setRooms(Set<Room> rooms) {
		this.rooms = rooms;
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Hotel other = (Hotel) obj;
		return id == other.id;
	}

	public Hotel(String name, String address, String promotionalDescription,
			Set<HotelCustomerService> hotelCustomerServices, Set<Room> rooms, int gradeNumber, double score,
			String city) {
		super();
		this.name = name;
		this.address = address;
		this.promotionalDescription = promotionalDescription;
		this.hotelCustomerServices = hotelCustomerServices;
		this.rooms = rooms;
		this.gradeNumber = gradeNumber;
		this.score = score;
		this.city = city;
	}

	public Hotel(HotelDTO hotelDTO) {
		this.name = hotelDTO.getHotelNameRegister();
		this.address = hotelDTO.getHotelAddressRegister();
		this.promotionalDescription = hotelDTO.getHotelPromotionalDescription();
		this.score = 0.0;
		this.city = hotelDTO.getCity();
		this.gradeNumber = 0;
	}

	public Set<HotelAdmin> getHotelAdmins() {
		return hotelAdmins;
	}

	public void setHotelAdmins(Set<HotelAdmin> hotelAdmins) {
		this.hotelAdmins = hotelAdmins;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public Integer getGradeNumber() {
		return gradeNumber;
	}

	public void setGradeNumber(Integer gradeNumber) {
		this.gradeNumber = gradeNumber;
	}

	public Set<RoomReservation> getRoomReservations() {
		return roomReservations;
	}

	public void setRoomReservations(Set<RoomReservation> roomReservations) {
		this.roomReservations = roomReservations;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

}
