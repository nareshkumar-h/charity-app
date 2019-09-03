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
	public Double balanceEnquiry(Long accountNo) throws SQLException;
	public Integer updateMoney(Transaction transaction);
	public void transaction(Transaction donor,Transaction admin );
	public void updateRequestAmount(DonationRequest request);

}
