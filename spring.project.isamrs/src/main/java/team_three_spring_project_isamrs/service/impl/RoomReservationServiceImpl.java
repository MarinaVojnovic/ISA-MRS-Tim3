package team_three_spring_project_isamrs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team_three_spring_project_isamrs.model.Hotel;
import team_three_spring_project_isamrs.model.RegularUser;
import team_three_spring_project_isamrs.model.Room;
import team_three_spring_project_isamrs.model.RoomReservation;
import team_three_spring_project_isamrs.repository.RoomReservationRepository;
import team_three_spring_project_isamrs.service.RoomReservationService;

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

	@Override
	public List<RoomReservation> findByHotel(Hotel hotel) {
		return roomReservationRepository.findByHotel(hotel);
	}

}
