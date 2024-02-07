package org.dnyanyog.dto.request;

import org.springframework.stereotype.Component;

@Component
public class AdminLoginRequest {

	private String adminId;

	private String password;

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
