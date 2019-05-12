package team_three_spring_project_isamrs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import team_three_spring_project_isamrs.model.BranchOffice;
import team_three_spring_project_isamrs.model.Rentacar;

@Repository
public interface BranchRepository extends JpaRepository<BranchOffice, Long>{
	public List<BranchOffice> findByRentacar(Rentacar rentacar);
}
