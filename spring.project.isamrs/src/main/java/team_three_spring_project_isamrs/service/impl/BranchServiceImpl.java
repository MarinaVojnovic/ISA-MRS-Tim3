package team_three_spring_project_isamrs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team_three_spring_project_isamrs.model.BranchOffice;
import team_three_spring_project_isamrs.model.Rentacar;
import team_three_spring_project_isamrs.repository.BranchRepository;
import team_three_spring_project_isamrs.service.BranchService;

@Service
public class BranchServiceImpl implements BranchService {
	@Autowired
	BranchRepository branchRepository;

	public BranchOffice getOne(long id) {
		return branchRepository.findOne(id);
	}

	public List<BranchOffice> getAll() {
		return branchRepository.findAll();

	}

	public BranchOffice create(BranchOffice b) {
		return branchRepository.save(b);
	}

	public void delete(long id) {
		branchRepository.delete(id);
	}

	public BranchOffice save(BranchOffice b) {
		return branchRepository.save(b);
	}

	public List<BranchOffice> findByRentacar(Rentacar r) {
		return branchRepository.findByRentacar(r);
	}
}
