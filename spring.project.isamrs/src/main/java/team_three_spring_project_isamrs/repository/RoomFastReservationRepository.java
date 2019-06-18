package team_three_spring_project_isamrs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import team_three_spring_project_isamrs.model.Room;
import team_three_spring_project_isamrs.model.RoomFastReservation;
import team_three_spring_project_isamrs.model.RoomReservation;

@Repository
public interface RoomFastReservationRepository extends JpaRepository<RoomFastReservation, Long> {
	public List<RoomFastReservation> findByRoom(Room room);

	@Query(value = "select * from room_fast_reservation rfr where rfr.room_id in (select r.id from room r where r.hotel_id = ?1)", nativeQuery = true)
	public List<RoomFastReservation> findByHotel2(Long hotelId);
	
}
