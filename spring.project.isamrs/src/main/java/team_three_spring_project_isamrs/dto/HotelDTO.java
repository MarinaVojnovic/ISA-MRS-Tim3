package team_three_spring_project_isamrs.dto;

public class HotelDTO {
	private String hotelNameRegister;
	private String hotelAddressRegister;
	private String hotelPromotionalDescription;
	private String city;

	public String getHotelNameRegister() {
		return hotelNameRegister;
	}

	public void setHotelNameRegister(String hotelNameRegister) {
		this.hotelNameRegister = hotelNameRegister;
	}

	public String getHotelAddressRegister() {
		return hotelAddressRegister;
	}

	public void setHotelAddressRegister(String hotelAddressRegister) {
		this.hotelAddressRegister = hotelAddressRegister;
	}

	public String getHotelPromotionalDescription() {
		return hotelPromotionalDescription;
	}

	public void setHotelPromotionalDescription(String hotelPromotionalDescription) {
		this.hotelPromotionalDescription = hotelPromotionalDescription;
	}

	public HotelDTO(String hotelNameRegister, String hotelAddressRegister, String hotelPromotionalDescription, String city) {
		super();
		this.hotelNameRegister = hotelNameRegister;
		this.hotelAddressRegister = hotelAddressRegister;
		this.hotelPromotionalDescription = hotelPromotionalDescription;
		this.city = city;
	}

	public HotelDTO() {
		super();
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
