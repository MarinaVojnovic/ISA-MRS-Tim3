package tim3.spring.project.isamrs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tim3.spring.project.isamrs.model.RegularUser;
import tim3.spring.project.isamrs.model.Room;
import tim3.spring.project.isamrs.model.RoomReservation;
import tim3.spring.project.isamrs.repository.RoomReservationRepository;
import tim3.spring.project.isamrs.service.RoomReservationService;

@Service
public class RoomReservationServiceImpl implements RoomReservationService {

	@Autowired
	RoomReservationRepository roomReservationRepository;

	@Override
	public RoomReservation getOne(long id) {
		return roomReservationRepository.findOne(id);
	}

	@Override
	public List<RoomReservation> getAll() {
		return roomReservationRepository.findAll();
	}

	@Override
	public RoomReservation create(RoomReservation rfr) {
		return roomReservationRepository.save(rfr);
	}

	@Override
	public RoomReservation update(RoomReservation rfr) {
		return null;
	}

	@Override
	public void delete(long id) {
		roomReservationRepository.delete(id);

	}

	@Override
	public RoomReservation save(RoomReservation rfr) {
		return roomReservationRepository.save(rfr);
	}

	@Override
	public List<RoomReservation> findByRoomsContaining(Room r) {
		return roomReservationRepository.findByRooms2(r.getId());
	}

	@Override
	public List<RoomReservation> findByRegularUser(RegularUser regularUser) {
		return roomReservationRepository.findByRegularUser(regularUser);
	}

}
