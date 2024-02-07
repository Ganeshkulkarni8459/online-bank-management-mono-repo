package org.dnyanyog.dto.request;

import org.springframework.stereotype.Component;

@Component
public class CustomerWithdrawAmountRequest {
	
    private int balance;
	
	private String atmPin;
	
	private String cardNo;
	
	


	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public String getAtmPin() {
		return atmPin;
	}

	public void setAtmPin(String atmPin) {
		this.atmPin = atmPin;
	}





}