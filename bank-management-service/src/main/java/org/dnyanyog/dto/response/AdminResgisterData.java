package org.dnyanyog.dto.response;

import org.springframework.stereotype.Component;

@Component
public class AdminResgisterData {

	private String adminEmail;
	
	private String password;
	
	private String adminId;

	public String getAdminEmail() {
		return adminEmail;
	}

	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	

	

}
