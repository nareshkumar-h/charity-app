package com.charityapp.model;

public class DonationRequest {
	
	private int requestId;
	private String requestType;
	private String description;
	private Double requestAmount;
	private Integer adminId;
	private String date;
	private Boolean active;
	private Double amount;
	private Long accountNo;
	
	public Long getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(Long accountNo) {
		this.accountNo = accountNo;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public int getRequestId() {
		return requestId;
	}
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}
	public String getRequestType() {
		return requestType;
	}
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getRequestAmount() {
		return requestAmount;
	}
	public void setRequestAmount(Double requestAmount) {
		this.requestAmount = requestAmount;
	}
	
	public Integer getAdminId() {
		return adminId;
	}
	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	@Override
	public String toString() {
		return "DonationRequest [requestId=" + requestId + ", requestType=" + requestType + ", description="
				+ description + ", requestAmount=" + requestAmount + ", adminId=" + adminId + ", date=" + date
				+ ", active=" + active + ", amount=" + amount + ", accountNo=" + accountNo + "]";
	}
	
	
	
}
