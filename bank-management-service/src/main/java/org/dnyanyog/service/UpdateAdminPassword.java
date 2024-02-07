package org.dnyanyog.service;

import java.util.List;

import org.dnyanyog.dto.request.AdminResgisterRequest;
import org.dnyanyog.dto.response.AdminResgisterResponse;
import org.dnyanyog.entity.Admin;
import org.dnyanyog.repo.UpdatePasswordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateAdminPassword {
	
	@Autowired
	AdminResgisterResponse adminResponse;
	
	@Autowired
	UpdatePasswordRepository repo;
	
	public AdminResgisterResponse updatePassword(AdminResgisterRequest request) {
		List<Admin> adminOptional = repo.findByAdminEmail(request.getAdminEmail());

		if (adminOptional.isEmpty()) {
			adminResponse.setStatus("Fail");
			adminResponse.setMessage("Email Not Found or Incorrect Password");

		} else {

			Admin admin = adminOptional.get(0);
			admin.setPassword(request.getUpdatedPassword());
			repo.save(admin);

			adminResponse.setStatus("Success");
			adminResponse.setMessage("Password Updated Successfully");
		}

		return adminResponse;
	}

}
