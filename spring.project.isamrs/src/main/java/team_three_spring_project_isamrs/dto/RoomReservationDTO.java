package team_three_spring_project_isamrs.dto;

public class RoomReservationDTO {
	private Long id;
	private String roomIds;
	private Long hotelId;
	private String startDate;
	private String endDate;
	private Double price;
	private Integer numOfPass;
	private String hotelCustomerServices;
	private Double discount;
	private Integer numberHotelDiscount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoomIds() {
		return roomIds;
	}

	public void setRoomIds(String roomIds) {
		this.roomIds = roomIds;
	}

	public Long getHotelId() {
		return hotelId;
	}

	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getNumOfPass() {
		return numOfPass;
	}

	public void setNumOfPass(Integer numOfPass) {
		this.numOfPass = numOfPass;
	}

	public String getHotelCustomerServices() {
		return hotelCustomerServices;
	}

	public void setHotelCustomerServices(String hotelCustomerServices) {
		this.hotelCustomerServices = hotelCustomerServices;
	}

	public RoomReservationDTO(Long id, String roomIds, Long hotelId, String startDate, String endDate, Double price,
			Integer numOfPass, String hotelCustomerServices, Double discount, Integer numberHotelDiscount) {
		super();
		this.id = id;
		this.roomIds = roomIds;
		this.hotelId = hotelId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.price = price;
		this.numOfPass = numOfPass;
		this.hotelCustomerServices = hotelCustomerServices;
		this.discount = discount;
		this.numberHotelDiscount = numberHotelDiscount;
	}

	public RoomReservationDTO() {
		super();
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Integer getNumberHotelDiscount() {
		return numberHotelDiscount;
	}

	public void setNumberHotelDiscount(Integer numberHotelDiscount) {
		this.numberHotelDiscount = numberHotelDiscount;
	}

}
