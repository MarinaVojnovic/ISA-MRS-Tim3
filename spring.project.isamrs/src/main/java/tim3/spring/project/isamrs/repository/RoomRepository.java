package tim3.spring.project.isamrs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tim3.spring.project.isamrs.model.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long>{
	
	public Room findById(Long id);

}
