package tim3.spring.project.isamrs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tim3.spring.project.isamrs.model.Room;
import tim3.spring.project.isamrs.model.RoomFastReservation;
import tim3.spring.project.isamrs.repository.RoomFastReservationRepository;
import tim3.spring.project.isamrs.service.RoomFastReservationService;

@Service
public class RoomFastReservationServiceImpl implements RoomFastReservationService {

	@Autowired
	RoomFastReservationRepository roomFastReservationRepository;

	@Override
	public RoomFastReservation getOne(long id) {
		return roomFastReservationRepository.findOne(id);
	}

	@Override
	public List<RoomFastReservation> getAll() {
		return roomFastReservationRepository.findAll();
	}

	@Override
	public RoomFastReservation create(RoomFastReservation rfr) {
		return roomFastReservationRepository.save(rfr);
	}

	@Override
	public RoomFastReservation update(RoomFastReservation rfr) {
		return null;
	}

	@Override
	public void delete(long id) {
		roomFastReservationRepository.delete(id);

	}

	@Override
	public RoomFastReservation save(RoomFastReservation rfr) {
		return roomFastReservationRepository.save(rfr);
	}

	@Override
	public List<RoomFastReservation> findByRoom(Room r) {
		return roomFastReservationRepository.findByRoom(r);
	}

}
