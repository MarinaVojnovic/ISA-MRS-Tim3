package tim3.spring.project.isamrs.dto;

public class AirlineDTO {
	private String airlineNameRegister;
	private String airlineAddressRegister;
	private String airlinePromotionalDescription;
	private String city;

	public String getAirlineNameRegister() {
		return airlineNameRegister;
	}

	public void setAirlineNameRegister(String airlineNameRegister) {
		this.airlineNameRegister = airlineNameRegister;
	}

	public String getAirlineAddressRegister() {
		return airlineAddressRegister;
	}

	public void setAirlineAddressRegister(String airlineAddressRegister) {
		this.airlineAddressRegister = airlineAddressRegister;
	}

	public String getAirlinePromotionalDescription() {
		return airlinePromotionalDescription;
	}

	public void setAirlinePromotionalDescription(String airlinePromotionalDescription) {
		this.airlinePromotionalDescription = airlinePromotionalDescription;
	}

	public AirlineDTO() {
		super();
	}

	public AirlineDTO(String airlineNameRegister, String airlineAddressRegister, String airlinePromotionalDescription,
			String city) {
		super();
		this.airlineNameRegister = airlineNameRegister;
		this.airlineAddressRegister = airlineAddressRegister;
		this.airlinePromotionalDescription = airlinePromotionalDescription;
		this.city = city;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
