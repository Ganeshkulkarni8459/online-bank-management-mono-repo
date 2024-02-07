package org.dnyanyog.dto.response;

import org.springframework.stereotype.Component;

@Component
public class GetTotalBalanceResponse {
	
	private String status;
	
	private String message;
	
	private int balance;

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

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	

	
	

}
