package com.charityapp.dao;

import java.sql.SQLException;
import java.util.List;

import com.charityapp.model.DonationRequest;
import com.charityapp.model.Donor;
import com.charityapp.model.Transaction;

public interface Charity {
	
	public void donorRegister(Donor donor) throws SQLException;
	public Donor donorLogin(Donor donor) throws SQLException;
	public void donationRequest(DonationRequest request);
	public List<DonationRequest> listDonationRequest() throws SQLException;
	public Transaction balanceEnquiry(Transaction transaction) throws SQLException;
	public Integer depositeMoney(Transaction transaction);

}
