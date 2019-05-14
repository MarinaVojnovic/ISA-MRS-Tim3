package team_three_spring_project_isamrs.service;

import java.util.List;

import team_three_spring_project_isamrs.model.Hotel;
import team_three_spring_project_isamrs.model.RegularUser;
import team_three_spring_project_isamrs.model.Room;
import team_three_spring_project_isamrs.model.RoomReservation;

public interface RoomReservationService {
	public RoomReservation getOne(long id);

	public List<RoomReservation> getAll();

	public RoomReservation create(RoomReservation rfr);

	public void delete(long id);

	public RoomReservation save(RoomReservation rfr);

	public List<RoomReservation> findByRoomsContaining(Room r);

	public List<RoomReservation> findByRegularUser(RegularUser regularUser);
	
	public List<RoomReservation> findByHotel(Hotel hotel);
}
