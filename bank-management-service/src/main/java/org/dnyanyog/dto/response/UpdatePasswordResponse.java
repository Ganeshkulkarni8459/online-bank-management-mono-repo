package org.dnyanyog.dto.response;

import org.springframework.stereotype.Component;

@Component
public class UpdatePasswordResponse {
	
	private String status;
	
	private String message;
	
	private String password;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
