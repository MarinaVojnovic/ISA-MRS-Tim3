package team_three_spring_project_isamrs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team_three_spring_project_isamrs.model.FlightReservation;
import team_three_spring_project_isamrs.repository.FlightReservationRepository;
import team_three_spring_project_isamrs.service.FlightReservationService;

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
		public void delete(long id) {
			this.flightRepository.delete(id);
			
		}

		@Override
		public void save(FlightReservation fr) {
			this.flightRepository.save(fr);
			
		}
		@Override
		public List<FlightReservation> findByNameAndLastName(String name, String lastName){
			return this.flightRepository.findByNameAndLastName(name,lastName);
		}
	 	

}
