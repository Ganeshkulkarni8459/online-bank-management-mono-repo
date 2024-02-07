package org.dnyanyog.dto.response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerResgisterByAdminResponse {
	
	private String status;
	
	private String message;
	
	@Autowired
	private CustomerRegisterData data;

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

	public CustomerRegisterData getData() {
		return data;
	}

	public void setData(CustomerRegisterData data) {
		this.data = data;
	}
	

}
