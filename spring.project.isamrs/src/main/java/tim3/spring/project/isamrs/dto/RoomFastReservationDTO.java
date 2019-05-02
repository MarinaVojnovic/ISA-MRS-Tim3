package tim3.spring.project.isamrs.dto;

public class RoomFastReservationDTO {
	private Long id;
	private Long roomId;
	private String startDate;
	private String endDate;
	private Double originalPrice;
	private Double discount;
	private Double newPrice;
	private String hotelCustomerServices;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRoomId() {
		return roomId;
	}

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Double getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(Double originalPrice) {
		this.originalPrice = originalPrice;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Double getNewPrice() {
		return newPrice;
	}

	public void setNewPrice(Double newPrice) {
		this.newPrice = newPrice;
	}

	public String getHotelCustomerServices() {
		return hotelCustomerServices;
	}

	public void setHotelCustomerServices(String hotelCustomerServices) {
		this.hotelCustomerServices = hotelCustomerServices;
	}

	public RoomFastReservationDTO(Long id, Long roomId, String startDate, String endDate, Double originalPrice,
			Double discount, Double newPrice, String hotelCustomerServices) {
		super();
		this.id = id;
		this.roomId = roomId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.originalPrice = originalPrice;
		this.discount = discount;
		this.newPrice = newPrice;
		this.hotelCustomerServices = hotelCustomerServices;
	}

	public RoomFastReservationDTO() {
		super();
	}

}
