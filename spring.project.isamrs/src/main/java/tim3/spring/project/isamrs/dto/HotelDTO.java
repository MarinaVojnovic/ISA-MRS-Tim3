package tim3.spring.project.isamrs.dto;

public class HotelDTO {
	private String hotelNameRegister;
	private String hotelAddressRegister;
	private String hotelPromotionalDescription;

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

	public HotelDTO(String hotelNameRegister, String hotelAddressRegister, String hotelPromotionalDescription) {
		super();
		this.hotelNameRegister = hotelNameRegister;
		this.hotelAddressRegister = hotelAddressRegister;
		this.hotelPromotionalDescription = hotelPromotionalDescription;
	}

	public HotelDTO() {
		super();
	}

}
