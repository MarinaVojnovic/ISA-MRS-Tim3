package tim3.spring.project.isamrs.service;

import java.util.List;

import tim3.spring.project.isamrs.model.Hotel;
import tim3.spring.project.isamrs.model.Room;
import tim3.spring.project.isamrs.model.RoomFastReservation;

public interface RoomFastReservationService {
	public RoomFastReservation getOne(long id);

	public List<RoomFastReservation> getAll();

	public RoomFastReservation create(RoomFastReservation rfr);

	public RoomFastReservation update(RoomFastReservation rfr);

	public void delete(long id);

	public RoomFastReservation save(RoomFastReservation rfr);

	public List<RoomFastReservation> findByRoom(Room r);
	
	public List<RoomFastReservation> findByHotel(Hotel h);
}
