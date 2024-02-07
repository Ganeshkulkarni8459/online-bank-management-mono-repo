package dto;

public class CustomerTranferAmountRequest {
	
	private String fromcardNo;
	
	private String tocardNo;
	
    private double balance;
	
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

	public double getBalance() {
		return balance;
	}

	public void setBalance(double d) {
		this.balance = d;
	}

	public String getAtmPin() {
		return atmPin;
	}

	public void setAtmPin(String atmPin) {
		this.atmPin = atmPin;
	}

	

}
