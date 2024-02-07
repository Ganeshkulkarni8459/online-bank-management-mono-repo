package org.dnyanyog.dto.request;

import org.springframework.stereotype.Component;

@Component
public class GetTotalBalnaceRequest {
	
	private long customerId;
	
	private String atmPin;

	public long getCsutomerId() {
		return customerId;
	}

	public void setCsutomerId(long customerId) {
		this.customerId = customerId;
	}

	public String getAtmPin() {
		return atmPin;
	}

	public void setAtmPin(String atmPin) {
		this.atmPin = atmPin;
	}
	
	
	


}
