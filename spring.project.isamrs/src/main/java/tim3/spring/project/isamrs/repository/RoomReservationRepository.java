package tim3.spring.project.isamrs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tim3.spring.project.isamrs.model.RegularUser;
import tim3.spring.project.isamrs.model.RoomReservation;

@Repository
public interface RoomReservationRepository extends JpaRepository<RoomReservation, Long> {

	@Query(value = "select * from room_reservation rr where ?1 in (select rrr.room_id from room_room_reservation rrr where rrr.room_reservation_id = rr.id)", nativeQuery = true)
	public List<RoomReservation> findByRooms2(Long roomId);

	public List<RoomReservation> findByRegularUser(RegularUser regularUser);
}
