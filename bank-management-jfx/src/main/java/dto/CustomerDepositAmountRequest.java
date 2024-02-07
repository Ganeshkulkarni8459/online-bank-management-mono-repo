package dto;

public class CustomerDepositAmountRequest {
	
//	private double amount;
//	
//	private String pin;
	
	
	private String balance;
	
	private String atmPin;
	
	private String cardNo;
	
	


	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String depositAmount) {
		this.balance = depositAmount;
	}

	public String getAtmPin() {
		return atmPin;
	}

	public void setAtmPin(String atmPin) {
		this.atmPin = atmPin;
	}



	
	

}