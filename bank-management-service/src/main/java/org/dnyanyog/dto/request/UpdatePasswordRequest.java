package org.dnyanyog.dto.request;

import org.springframework.stereotype.Component;

@Component
public class UpdatePasswordRequest {
	
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

}
