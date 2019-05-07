package tim3.spring.project.isamrs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tim3.spring.project.isamrs.model.RegularUser;
import tim3.spring.project.isamrs.model.Room;
import tim3.spring.project.isamrs.model.RoomReservation;

@Repository
public interface RoomReservationRepository extends JpaRepository<RoomReservation, Long> {
	
	@Query(value = "select * from room_reservation rr where ?1.id in (select id from rr.rooms)", nativeQuery = true)
	public List<RoomReservation> findByRooms2(Room room);
	
	public List<RoomReservation> findByRegularUser(RegularUser regularUser);
}
