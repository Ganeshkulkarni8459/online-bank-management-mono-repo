package org.dnyanyog.dto.response;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class BranchAddResponse {
	
	private String status;
	
	private String message;
	
    private String brachName;
	
	private String branchCity;
	
	private String branchState;
	
	private long serialNo;
	
	private List<String> branchNames; 

	public List<String> getBranchNames() {
		return branchNames;
	}

	public void setBranchNames(List<String> branchNames) {
		this.branchNames = branchNames;
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

	public String getBrachName() {
		return brachName;
	}

	public void setBrachName(String brachName) {
		this.brachName = brachName;
	}

	public String getBranchCity() {
		return branchCity;
	}

	public void setBranchCity(String branchCity) {
		this.branchCity = branchCity;
	}

	public String getBranchState() {
		return branchState;
	}

	public void setBranchState(String branchState) {
		this.branchState = branchState;
	}

	public long getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(long serialNo) {
		this.serialNo = serialNo;
	}
	
  
}
