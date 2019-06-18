package team_three_spring_project_isamrs.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import team_three_spring_project_isamrs.dto.RoomDTO;

@Entity
@Table(catalog = "dbtim3", name = "room")
public class Room {
	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "score")
	private Double score;

	@Column(name = "grade_number")
	private Integer gradeNumber;

	@Column(name = "room_number")
	private Integer roomNumber;

	@Column(name = "price")
	private Double price;

	@Column(name = "number_people")
	private Integer numberPeople;

	@JsonIgnore
	@OneToMany(mappedBy = "room", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<RoomFastReservation> roomFastReservation = new HashSet<>();

	@ManyToOne(fetch = FetchType.EAGER)
	Hotel hotel;

	@JsonIgnore
	@ManyToMany(mappedBy = "rooms")
	private Set<RoomReservation> roomReservations = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getNumberPeople() {
		return numberPeople;
	}

	public void setNumberPeople(Integer numberPeople) {
		this.numberPeople = numberPeople;
	}

	public Room() {
		super();
	}

	public Room(RoomDTO roomDTO) {
		this.roomNumber = roomDTO.getRoomNumberRegister();
		this.numberPeople = roomDTO.getRoomPeopleNumberRegister();
		this.price = roomDTO.getRoomPriceRegister();
		this.score = 0.0;
		this.gradeNumber = 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hotel == null) ? 0 : hotel.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((numberPeople == null) ? 0 : numberPeople.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((roomNumber == null) ? 0 : roomNumber.hashCode());
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
		Room other = (Room) obj;
		if (hotel == null) {
			if (other.hotel != null)
				return false;
		} else if (!hotel.equals(other.hotel))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (numberPeople == null) {
			if (other.numberPeople != null)
				return false;
		} else if (!numberPeople.equals(other.numberPeople))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (roomNumber == null) {
			if (other.roomNumber != null)
				return false;
		} else if (!roomNumber.equals(other.roomNumber))
			return false;
		return true;
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
}
