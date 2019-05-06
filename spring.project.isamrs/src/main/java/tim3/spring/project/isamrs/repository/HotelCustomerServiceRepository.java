package tim3.spring.project.isamrs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tim3.spring.project.isamrs.model.Hotel;
import tim3.spring.project.isamrs.model.HotelCustomerService;

@Repository
public interface HotelCustomerServiceRepository extends JpaRepository<HotelCustomerService, Long> {
	public List<HotelCustomerService> findByHotel(Hotel hotel);
}
