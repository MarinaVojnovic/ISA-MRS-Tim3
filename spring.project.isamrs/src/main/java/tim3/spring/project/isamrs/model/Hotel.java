package tim3.spring.project.isamrs.model;

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

import com.fasterxml.jackson.annotation.JsonIgnore;

import tim3.spring.project.isamrs.dto.HotelDTO;

@Entity
@Table(catalog = "dbtim3", name = "hotel")
public class Hotel {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "address")
	private String address;

	@Column(name = "promotional_description")
	private String promotionalDescription;

	@JsonIgnore
	@OneToMany(mappedBy = "hotel", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<HotelService> hotelServices = new HashSet<HotelService>();

	@JsonIgnore
	@OneToMany(mappedBy = "hotel", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<Room> rooms = new HashSet<Room>();

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

	public Set<HotelService> getHotelServices() {
		return hotelServices;
	}

	public void setHotelServices(Set<HotelService> hotelServices) {
		this.hotelServices = hotelServices;
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

	public Hotel(String name, String address, String promotionalDescription, Set<HotelService> hotelServices,
			Set<Room> rooms) {
		super();
		this.name = name;
		this.address = address;
		this.promotionalDescription = promotionalDescription;
		this.hotelServices = hotelServices;
		this.rooms = rooms;
	}

	public Hotel(HotelDTO hotelDTO) {
		this.name = hotelDTO.getHotelNameRegister();
		this.address = hotelDTO.getHotelAddressRegister();
		this.promotionalDescription = hotelDTO.getHotelPromotionalDescription();
		this.hotelServices = new HashSet<HotelService>();
		this.rooms = new HashSet<Room>();
	}

}