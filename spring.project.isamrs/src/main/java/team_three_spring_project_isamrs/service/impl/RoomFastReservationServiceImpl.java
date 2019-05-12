package team_three_spring_project_isamrs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team_three_spring_project_isamrs.model.Hotel;
import team_three_spring_project_isamrs.model.Room;
import team_three_spring_project_isamrs.model.RoomFastReservation;
import team_three_spring_project_isamrs.repository.RoomFastReservationRepository;
import team_three_spring_project_isamrs.service.RoomFastReservationService;

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

	@Override
	public List<RoomFastReservation> findByHotel(Hotel h) {
		return roomFastReservationRepository.findByHotel2(h.getId());
	}

}
