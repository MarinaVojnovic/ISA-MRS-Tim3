package team_three_spring_project_isamrs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import team_three_spring_project_isamrs.model.Hotel;
import team_three_spring_project_isamrs.model.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long>{
	
	public Room findById(Long id);
	public List<Room> findByHotel(Hotel hotel);
	public Room findByRoomNumberAndHotel(Integer roomNumber, Hotel hotel);

}
