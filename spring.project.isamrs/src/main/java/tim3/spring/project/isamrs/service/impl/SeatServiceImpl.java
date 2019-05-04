package tim3.spring.project.isamrs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tim3.spring.project.isamrs.model.Flight;
import tim3.spring.project.isamrs.model.FlightClass;
import tim3.spring.project.isamrs.model.Seat;
import tim3.spring.project.isamrs.repository.SeatRepository;
import tim3.spring.project.isamrs.service.SeatService;
@Service
public class SeatServiceImpl implements SeatService {
	@Autowired
	SeatRepository seatRepository;


	@Override
	public List<Seat> findByFlightAndFc(Flight f,FlightClass fc) {
		return this.seatRepository.findByFlightAndFc(f,fc);
	}


	@Override
	public Seat getOne(long id) {
		// TODO Auto-generated method stub
		return this.seatRepository.findOne(id);
	}


	@Override
	public List<Seat> getAll() {
		// TODO Auto-generated method stub
		return this.seatRepository.findAll();
	}


	@Override
	public Seat update(Seat seat) {
		return this.seatRepository.save(seat);
	}


	@Override
	public void save(Seat s) {
		this.seatRepository.save(s);
		
	};

}
