package team_three_spring_project_isamrs.service;

import java.util.List;

import team_three_spring_project_isamrs.model.Hotel;
import team_three_spring_project_isamrs.model.Room;

public interface RoomService {
	public Room getOne(long id);

	public List<Room> getAll();

	public Room create(Room room);

	public void delete(long id);

	public Room save(Room room);

	public List<Room> findByHotel(Hotel h);
	
	public Room findByRoomNumberAndHotel(Integer roomNumber, Hotel hotel);
}
