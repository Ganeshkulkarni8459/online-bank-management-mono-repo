package org.dnyanyog.dto.request;

import org.springframework.stereotype.Component;

@Component
public class AdminResgisterRequest {

	private String adminEmail;

	private String password;

	private String adminId;
	
	private String updatedPassword;

	public String getUpdatedPassword() {
		return updatedPassword;
	}

	public void setUpdatedPassword(String updatedPassword) {
		this.updatedPassword = updatedPassword;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

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

	

}
