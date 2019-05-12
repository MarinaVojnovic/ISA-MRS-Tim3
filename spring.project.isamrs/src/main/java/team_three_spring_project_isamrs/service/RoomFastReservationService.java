package team_three_spring_project_isamrs.service;

import java.util.List;

import team_three_spring_project_isamrs.model.Hotel;
import team_three_spring_project_isamrs.model.Room;
import team_three_spring_project_isamrs.model.RoomFastReservation;

public interface RoomFastReservationService {
	public RoomFastReservation getOne(long id);

	public List<RoomFastReservation> getAll();

	public RoomFastReservation create(RoomFastReservation rfr);

	public void delete(long id);

	public RoomFastReservation save(RoomFastReservation rfr);

	public List<RoomFastReservation> findByRoom(Room r);

	public List<RoomFastReservation> findByHotel(Hotel h);
}
