package org.dnyanyog.service;

import java.util.List;

import org.dnyanyog.dto.request.BranchAddRequest;
import org.dnyanyog.dto.response.BranchAddResponse;
import org.dnyanyog.entity.Branch;
import org.dnyanyog.entity.CustomerResgistration;
import org.dnyanyog.repo.BranchServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BranchService {
	

	@Autowired
	BranchServiceRepository repo;
	
	@Autowired
	List<String> branchNames;

	public BranchAddResponse addBranch(BranchAddRequest branchAddRequest) {
		
		Branch branch = new Branch();

		branch.setBranchName(branchAddRequest.getBranchName());
		branch.setBranchCity(branchAddRequest.getBranchCity());
		branch.setBranchState(branchAddRequest.getBranchState());

		branch = repo.save(branch);
		
		BranchAddResponse branchAddResponse= new BranchAddResponse();
		
		branchAddResponse.setStatus("Success");
		branchAddResponse.setMessage("Branch Add Successfully");
		branchAddResponse.setSerialNo(branch.getSerialNo());
		
		branchAddResponse.setBrachName(branch.getBranchName());
		branchAddResponse.setBranchCity(branch.getBranchCity());
		branchAddResponse.setBranchState(branch.getBranchState());

		return branchAddResponse;

	}
	public List<String> getAllBranchNames() {
		List<Branch> branch = repo.findAll();

		// List<Long> userIds = new ArrayList<>(); == @Autowired List<Long> userIds;

		for (Branch b : branch) {
			branchNames.add(b.getBranchName());
		}

		return branchNames;
	}

}
