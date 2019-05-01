package tim3.spring.project.isamrs.service;

import java.util.List;

import tim3.spring.project.isamrs.model.BranchOffice;
import tim3.spring.project.isamrs.model.Rentacar;

public interface BranchService {
	public BranchOffice getOne(long id);
	public List<BranchOffice> getAll();
	public BranchOffice create(BranchOffice b);
	public BranchOffice update(BranchOffice b);
	public void delete(long id);
	public BranchOffice save(BranchOffice b);
	public List<BranchOffice> findByRentacar(Rentacar r);
}
