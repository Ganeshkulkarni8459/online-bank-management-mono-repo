package dto;


public class CustomerResgisterByAdminResponse {
	
	private String customerId;
	
	private String status;
	
	private String message;
	
	private CustomerRegisterData data;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

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
