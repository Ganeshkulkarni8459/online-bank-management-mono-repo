package org.dnyanyog.controller;

import org.dnyanyog.dto.request.AdminLoginRequest;
import org.dnyanyog.dto.request.AdminResgisterRequest;
import org.dnyanyog.dto.response.AdminLoginResponse;
import org.dnyanyog.dto.response.AdminResgisterResponse;
import org.dnyanyog.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminServiceController {

	@Autowired
	AdminService authentication;

	@PostMapping(path = "/api/admin/login", consumes = { "application/json", "application/xml" }, produces = {
			"application/json", "application/xml"})
	public AdminLoginResponse loginAdmin(@RequestBody AdminLoginRequest adminLoginRequest) {
		return authentication.loginAdmin(adminLoginRequest);
	}
	@PostMapping(path = "/api/admin/register", consumes = { "application/json", "application/xml" }, produces = {
			"application/json", "application/xml" })
	public AdminResgisterResponse resgisterService(@RequestBody AdminResgisterRequest adminRequest) {
		return authentication.registerAdmin(adminRequest);
	}
	
}
