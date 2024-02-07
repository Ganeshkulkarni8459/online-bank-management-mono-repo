package org.dnyanyog.dto.request;

import org.springframework.stereotype.Component;

@Component
public class ChangePinRequest {
	
	private String currPin;
	
	private String newPin;
	
	private String repeateNewPin;
	
	private String cardNumber;

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCurrPin() {
		return currPin;
	}

	public void setCurrPin(String currPin) {
		this.currPin = currPin;
	}

	public String getNewPin() {
		return newPin;
	}

	public void setNewPin(String newPin) {
		this.newPin = newPin;
	}

	public String getRepeateNewPin() {
		return repeateNewPin;
	}

	public void setRepeateNewPin(String repeateNewPin) {
		this.repeateNewPin = repeateNewPin;
	}
	
	
}
