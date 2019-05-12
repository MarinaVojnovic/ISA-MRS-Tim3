package team_three_spring_project_isamrs.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class HotelAdmin extends User {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1831516149566167290L;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "hotel_id")
	private Hotel hotelAdmin;

	public HotelAdmin() {
		super();
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Hotel getHotel() {
		return hotelAdmin;
	}

	public void setHotel(Hotel hotelAdmin) {
		this.hotelAdmin = hotelAdmin;
	}

}
