package org.dnyanyog.dto.request;

import org.springframework.stereotype.Component;

@Component
public class CustomerTranferAmountRequest {
	
	private String fromcardNo;
	
	private String tocardNo;
	
    private int balance;
	
	private String atmPin;

	public String getFromcardNo() {
		return fromcardNo;
	}

	public void setFromcardNo(String fromcardNo) {
		this.fromcardNo = fromcardNo;
	}

	public String getTocardNo() {
		return tocardNo;
	}

	public void setTocardNo(String tocardNo) {
		this.tocardNo = tocardNo;
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
