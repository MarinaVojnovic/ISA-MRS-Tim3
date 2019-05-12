package team_three_spring_project_isamrs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team_three_spring_project_isamrs.model.Hotel;
import team_three_spring_project_isamrs.model.Room;
import team_three_spring_project_isamrs.repository.RoomRepository;
import team_three_spring_project_isamrs.service.RoomService;

@Service
public class RoomServiceImpl implements RoomService {
	@Autowired
	RoomRepository roomRepository;

	@Override
	public Room getOne(long id) {
		return roomRepository.findOne(id);
	}

	@Override
	public List<Room> getAll() {
		return roomRepository.findAll();
	}

	@Override
	public Room create(Room room) {
		return roomRepository.save(room);
	}

	@Override
	public void delete(long id) {
		roomRepository.delete(id);

	}

	@Override
	public Room save(Room room) {
		return roomRepository.save(room);
	}

	@Override
	public List<Room> findByHotel(Hotel h) {
		return roomRepository.findByHotel(h);
	}
}
