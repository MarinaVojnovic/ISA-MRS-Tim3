package team_three_spring_project_isamrs.service;

import java.util.List;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.Lock;

import team_three_spring_project_isamrs.model.Hotel;
import team_three_spring_project_isamrs.model.Room;
import team_three_spring_project_isamrs.model.RoomFastReservation;

public interface RoomFastReservationService {
	
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	public RoomFastReservation getOne(long id);

	public List<RoomFastReservation> getAll();

	public RoomFastReservation create(RoomFastReservation rfr);

	public void delete(long id);

	public RoomFastReservation save(RoomFastReservation rfr);

	public List<RoomFastReservation> findByRoom(Room r);

	public List<RoomFastReservation> findByHotel(Hotel h);
}
