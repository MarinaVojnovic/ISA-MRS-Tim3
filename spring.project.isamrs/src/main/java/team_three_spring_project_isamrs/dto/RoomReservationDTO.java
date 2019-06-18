package team_three_spring_project_isamrs.dto;

import team_three_spring_project_isamrs.model.HotelCustomerService;
import team_three_spring_project_isamrs.model.Room;
import team_three_spring_project_isamrs.model.RoomReservation;

public class RoomReservationDTO {
	private Long id;
	private String roomIds;
	private String roomNumbers;
	private Long hotelId;
	private String hotelName;
	private String startDate;
	private String endDate;
	private Double price;
	private Integer numOfPass;
	private String hotelCustomerServices;
	private Double discount;
	private Integer numberHotelDiscount;
	private Long flightId;

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

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

	public RoomReservationDTO(RoomReservation room) {
		super();
		this.flightId = room.getFlightId();
		this.id = room.getId();
		this.hotelName = room.getHotel().getName();
		this.startDate = room.getStartDate();
		this.endDate = room.getEndDate();
		this.price = room.getPrice();
		this.numOfPass = room.getNumOfPass();
		this.discount = room.getDiscount();
		this.roomNumbers = "";
		this.hotelCustomerServices = "";
		for (Room r : room.getRooms()) {
			this.roomNumbers += r.getRoomNumber() + ", ";
		}
		this.roomNumbers = this.roomNumbers.substring(0, this.roomNumbers.length() - 2);
		for (HotelCustomerService cs : room.getHotelCustomerServices()) {
			this.hotelCustomerServices += cs.getName() + ", ";
		}

		if (!this.hotelCustomerServices.equals("")) {
			this.hotelCustomerServices = this.hotelCustomerServices.substring(0,
					this.hotelCustomerServices.length() - 2);
		}

	}

	public String getRoomNumbers() {
		return roomNumbers;
	}

	public void setRoomNumbers(String roomNumbers) {
		this.roomNumbers = roomNumbers;
	}

	public RoomReservationDTO(Long id, String roomIds, Long hotelId, String startDate, String endDate, Double price,
			Integer numOfPass, String hotelCustomerServices, Double discount, Integer numberHotelDiscount,
			Long flightId) {
		super();
		this.flightId = flightId;
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

	public Long getFlightId() {
		return flightId;
	}

	public void setFlightId(Long flightId) {
		this.flightId = flightId;
	}

}
