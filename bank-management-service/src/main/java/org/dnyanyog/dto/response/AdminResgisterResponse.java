package org.dnyanyog.dto.response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdminResgisterResponse {

	private String status;
	private String message;
	
	private String updatedPassword;
	
	public String getUpdatedPassword() {
		return updatedPassword;
	}

	public void setUpdatedPassword(String updatedPassword) {
		this.updatedPassword = updatedPassword;
	}

	@Autowired
	private AdminResgisterData adminData;

	public AdminResgisterData getAdminData() {
		return adminData;
	}

	public void setAdminData(AdminResgisterData adminData) {
		this.adminData = adminData;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
