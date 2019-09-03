package com.charityapp.model;

public class Transaction {
	
	private Integer id;
	private Long accountNo;
	private Double amount;
	private Integer pinNo;
	private Boolean active;
	private String transactionType;
	
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
	public Integer getPinNo() {
		return pinNo;
	}
	public void setPinNo(Integer pinNo) {
		this.pinNo = pinNo;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}

}
