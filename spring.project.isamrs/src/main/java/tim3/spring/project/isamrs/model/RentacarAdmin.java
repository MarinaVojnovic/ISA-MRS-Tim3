package tim3.spring.project.isamrs.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class RentacarAdmin extends User {

	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "rentacar_id", referencedColumnName = "id")
	private Rentacar rentacar;

	public RentacarAdmin() {
		super();
	}

	public Rentacar getRentacar() {
		return rentacar;
	}

	public void setRentacar(Rentacar rentacar) {
		this.rentacar = rentacar;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private static final long serialVersionUID = 1892679582107777957L;

}
