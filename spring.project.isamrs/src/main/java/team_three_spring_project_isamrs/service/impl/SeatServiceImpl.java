package team_three_spring_project_isamrs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import team_three_spring_project_isamrs.model.Flight;
import team_three_spring_project_isamrs.model.FlightClass;
import team_three_spring_project_isamrs.model.Seat;
import team_three_spring_project_isamrs.repository.SeatRepository;
import team_three_spring_project_isamrs.service.SeatService;

@Service
@Transactional(readOnly=true)
public class SeatServiceImpl implements SeatService {
	@Autowired
	SeatRepository seatRepository;

	@Override
	public List<Seat> findByFlightAndFc(Flight f, FlightClass fc) {
		return this.seatRepository.findByFlightAndFc(f, fc);
	}

	@Override
	public Seat getOne(long id) {
		return this.seatRepository.findOne(id);
	}

	@Override
	public List<Seat> getAll() {
		return this.seatRepository.findAll();
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW)
	public Seat update(Seat seat) {
		return this.seatRepository.save(seat);
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW)
	public void save(Seat s) {
		this.seatRepository.save(s);

	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW)
	public void delete(long id) {
		this.seatRepository.delete(id);
		
	}

	@Override
	public List<Seat> findByQuickBooking(Boolean b) {
		return this.seatRepository.findByQuickBooking(b);
	}

}
