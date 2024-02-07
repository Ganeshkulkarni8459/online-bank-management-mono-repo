package org.dnyanyog.dto.response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddAccountResponse {
	
	private String status;
	
	private String message;
	
	@Autowired
	private AddAccountData data;

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

	public AddAccountData getData() {
		return data;
	}

	public void setData(AddAccountData data) {
		this.data = data;
	}

}
