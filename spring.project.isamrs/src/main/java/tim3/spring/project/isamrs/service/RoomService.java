package tim3.spring.project.isamrs.service;

import java.util.List;

import tim3.spring.project.isamrs.model.Room;

public interface RoomService {
	public Room getOne(long id);
	public List<Room> getAll();
	public Room create(Room room);
	public Room update(Room room);
	public void delete(long id);
}
