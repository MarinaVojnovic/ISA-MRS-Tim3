package team_three_spring_project_isamrs.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class RentacarAdmin extends User {

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "rentacar_id")
	private Rentacar rentacarAdmin;

	public RentacarAdmin() {
		super();
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private static final long serialVersionUID = 1892679582107777957L;

	public Rentacar getRentacar() {
		return rentacarAdmin;
	}

	public void setRentacar(Rentacar rentacarAdmin) {
		this.rentacarAdmin = rentacarAdmin;
	}

}
