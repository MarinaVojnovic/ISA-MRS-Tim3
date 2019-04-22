package tim3.spring.project.isamrs.dto;

public class RoomDTO {
	private long id;
	private int roomNumberRegister;
	private int roomPeopleNumberRegister;
	private double roomPriceRegister;

	public int getRoomNumberRegister() {
		return roomNumberRegister;
	}

	public void setRoomNumberRegister(int roomNumberRegister) {
		this.roomNumberRegister = roomNumberRegister;
	}

	public int getRoomPeopleNumberRegister() {
		return roomPeopleNumberRegister;
	}

	public void setRoomPeopleNumberRegister(int roomPeopleNumberRegister) {
		this.roomPeopleNumberRegister = roomPeopleNumberRegister;
	}

	public double getRoomPriceRegister() {
		return roomPriceRegister;
	}

	public void setRoomPriceRegister(double roomPriceRegister) {
		this.roomPriceRegister = roomPriceRegister;
	}

	public RoomDTO(int roomNumberRegister, int roomPeopleNumberRegister, double roomPriceRegister, long id) {
		super();
		this.id = id;
		this.roomNumberRegister = roomNumberRegister;
		this.roomPeopleNumberRegister = roomPeopleNumberRegister;
		this.roomPriceRegister = roomPriceRegister;
	}

	public RoomDTO() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
