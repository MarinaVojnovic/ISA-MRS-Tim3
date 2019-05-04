package tim3.spring.project.isamrs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tim3.spring.project.isamrs.model.FlightReservation;
import tim3.spring.project.isamrs.model.User;
import tim3.spring.project.isamrs.repository.FlightRepository;
import tim3.spring.project.isamrs.repository.FlightReservationRepository;
import tim3.spring.project.isamrs.service.FlightReservationService;

@Service
public class FlightReservationServiceImpl implements FlightReservationService{
	 	@Autowired
	 	FlightReservationRepository flightRepository;

		@Override
		public FlightReservation getOne(long id) {
			return this.flightRepository.getOne(id);
		}

		@Override
		public List<FlightReservation> getAll() {
			return this.flightRepository.findAll();
		}

		@Override
		public FlightReservation create(FlightReservation flight) {
			return this.flightRepository.save(flight);
		}

		@Override
		public FlightReservation update(FlightReservation flight) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void delete(FlightReservation res) {
			this.flightRepository.delete(res);
			
		}

		@Override
		public void save(FlightReservation fr) {
			this.flightRepository.save(fr);
			
		}
	 	

}
