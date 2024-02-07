package org.dnyanyog.controller;

import java.util.List;

import org.dnyanyog.dto.request.BranchAddRequest;
import org.dnyanyog.dto.response.BranchAddResponse;
import org.dnyanyog.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BranchController {
	
	@Autowired
	BranchService service;
	
	@PostMapping(path = "/api/admin/add/branch")
	public BranchAddResponse addBranch(@RequestBody BranchAddRequest branchAddRequest) {
		
		return service.addBranch(branchAddRequest);
	}
	@GetMapping("/api/admin/get/branchNames")
    public List<String> getAllBranchNames() {
        return service.getAllBranchNames();
    }
}
