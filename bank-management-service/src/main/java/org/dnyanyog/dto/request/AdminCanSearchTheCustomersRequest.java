package org.dnyanyog.dto.request;

import org.springframework.stereotype.Component;

@Component
public class AdminCanSearchTheCustomersRequest {
	
	private long customerId;

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	
	

}
