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
		System.out.println("aaa");
		this.id=room.getId();
		System.out.println("bbb");
		this.hotelName=room.getHotel().getName();
		System.out.println("ccc");
		this.startDate=room.getStartDate();
		System.out.println("ddd");
		this.endDate=room.getEndDate();
		System.out.println("eee");
		this.price=room.getPrice();
		System.out.println("fff");
		this.numOfPass=room.getNumOfPass();
		System.out.println("ggg");
		this.discount=room.getDiscount();
		System.out.println("hhh");
		this.roomNumbers="";
		this.hotelCustomerServices="";
		for (Room r : room.getRooms()) {
			System.out.println("jjj");
			System.out.println(r.getRoomNumber());
			this.roomNumbers+=r.getRoomNumber()+", ";
			System.out.println("kkk");
		}
		System.out.println("lll");
		this.roomNumbers=this.roomNumbers.substring(0, this.roomNumbers.length()-2);
		System.out.println("mmm");
		for (HotelCustomerService cs : room.getHotelCustomerServices()) {
			System.out.println("nnn");
			this.hotelCustomerServices+=cs.getName()+", ";
			System.out.println("vvv");
		}
		System.out.println("rrr");
		
		if (this.hotelCustomerServices!="") {
			this.hotelCustomerServices=this.hotelCustomerServices.substring(0, this.hotelCustomerServices.length()-2);
		}
		
		
	}

	public String getRoomNumbers() {
		return roomNumbers;
	}

	public void setRoomNumbers(String roomNumbers) {
		this.roomNumbers = roomNumbers;
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
