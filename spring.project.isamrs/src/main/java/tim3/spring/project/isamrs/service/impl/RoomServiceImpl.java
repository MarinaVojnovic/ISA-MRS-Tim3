package tim3.spring.project.isamrs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tim3.spring.project.isamrs.model.Room;
import tim3.spring.project.isamrs.repository.RoomRepository;
import tim3.spring.project.isamrs.service.RoomService;

@Service
public class RoomServiceImpl implements RoomService{
	@Autowired
	RoomRepository roomRepository;

	@Override
	public Room getOne(long id) {
		// TODO Auto-generated method stub
		return roomRepository.findOne(id);
	}

	@Override
	public List<Room> getAll() {
		// TODO Auto-generated method stub
		return roomRepository.findAll();
	}

	@Override
	public Room create(Room room) {
		// TODO Auto-generated method stub
		return roomRepository.save(room);
	}

	@Override
	public Room update(Room room) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(long id) {
		roomRepository.delete(id);
		
	}
	
	@Override
	public Room save(Room room) {
		return roomRepository.save(room);
	}
}
