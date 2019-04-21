package tim3.spring.project.isamrs.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class HotelAdmin extends User {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1831516149566167290L;

	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "hotel_id", referencedColumnName = "id")
	private Hotel hotel;

	public HotelAdmin() {
		super();
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
