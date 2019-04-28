package tim3.spring.project.isamrs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tim3.spring.project.isamrs.model.BranchOffice;
import tim3.spring.project.isamrs.model.Rentacar;
import tim3.spring.project.isamrs.repository.BranchRepository;
import tim3.spring.project.isamrs.repository.CarRepository;
import tim3.spring.project.isamrs.service.BranchService;
@Service
public class BranchServiceImpl implements BranchService{
	@Autowired
	BranchRepository branchRepository;
	public BranchOffice getOne(long id) {
		return branchRepository.findOne(id);
	}
	public List<BranchOffice> getAll(){
		return branchRepository.findAll();
		
	}
	public BranchOffice create(BranchOffice b) {
		return branchRepository.save(b);
	}
	public BranchOffice update(BranchOffice b) {
		return null;
	}
	public void delete(long id) {
		 branchRepository.delete(id);
	}
	public BranchOffice save(BranchOffice b) {
		return branchRepository.save(b);
	}
	public List<BranchOffice> findByRentacar(Rentacar r){
		return branchRepository.findByRentacar(r);
	}
}
