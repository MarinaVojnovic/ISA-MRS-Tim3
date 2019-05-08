package tim3.spring.project.isamrs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tim3.spring.project.isamrs.model.Room;
import tim3.spring.project.isamrs.model.RoomFastReservation;

@Repository
public interface RoomFastReservationRepository extends JpaRepository<RoomFastReservation, Long> {
	public List<RoomFastReservation> findByRoom(Room room);

	@Query(value = "select * from room_fast_reservation rfr where rfr.room_id in (select r.id from room r where r.hotel_id = ?1)", nativeQuery = true)
	public List<RoomFastReservation> findByHotel2(Long hotelId);
}
