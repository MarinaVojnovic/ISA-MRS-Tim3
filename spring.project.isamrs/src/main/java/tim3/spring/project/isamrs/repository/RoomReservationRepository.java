package tim3.spring.project.isamrs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tim3.spring.project.isamrs.model.RegularUser;
import tim3.spring.project.isamrs.model.Room;
import tim3.spring.project.isamrs.model.RoomReservation;

@Repository
public interface RoomReservationRepository extends JpaRepository<RoomReservation, Long> {
	
	public List<RoomReservation> findByRoomsContaining(Room room);
	public List<RoomReservation> findByRegularUser(RegularUser regularUser);
}
