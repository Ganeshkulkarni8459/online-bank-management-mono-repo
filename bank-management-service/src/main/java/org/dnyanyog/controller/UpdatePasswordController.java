package org.dnyanyog.controller;

import org.dnyanyog.dto.request.AdminResgisterRequest;
import org.dnyanyog.dto.response.AdminResgisterResponse;
import org.dnyanyog.service.UpdateAdminPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UpdatePasswordController {
	
	@Autowired
	UpdateAdminPassword service;
	
	@PostMapping(path = "/api/admin/update/password", consumes = { "application/json", "application/xml" }, produces = {
			"application/json", "application/xml" })
	public AdminResgisterResponse updatePassword(@RequestBody AdminResgisterRequest adminRequest) {
		return service.updatePassword(adminRequest);
	}

}
