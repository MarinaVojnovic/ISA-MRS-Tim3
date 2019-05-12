package team_three_spring_project_isamrs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import team_three_spring_project_isamrs.model.Hotel;
import team_three_spring_project_isamrs.model.HotelCustomerService;

@Repository
public interface HotelCustomerServiceRepository extends JpaRepository<HotelCustomerService, Long> {
	public List<HotelCustomerService> findByHotel(Hotel hotel);
}
