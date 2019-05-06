package tim3.spring.project.isamrs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tim3.spring.project.isamrs.model.Room;
import tim3.spring.project.isamrs.model.RoomFastReservation;

@Repository
public interface RoomFastReservationRepository extends JpaRepository<RoomFastReservation, Long> {
	public List<RoomFastReservation> findByRoom(Room room);
}
