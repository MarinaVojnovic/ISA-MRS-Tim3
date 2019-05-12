package team_three_spring_project_isamrs.service;

import java.util.List;

import team_three_spring_project_isamrs.model.BranchOffice;
import team_three_spring_project_isamrs.model.Rentacar;

public interface BranchService {
	public BranchOffice getOne(long id);
	public List<BranchOffice> getAll();
	public BranchOffice create(BranchOffice b);
	public void delete(long id);
	public BranchOffice save(BranchOffice b);
	public List<BranchOffice> findByRentacar(Rentacar r);
}
