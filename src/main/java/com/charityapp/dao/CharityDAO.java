package com.charityapp.dao;

import java.sql.SQLException;
import java.util.List;

import com.charityapp.exception.DBException;
import com.charityapp.model.Admin;
import com.charityapp.model.DonationRequest;
import com.charityapp.model.Donor;
import com.charityapp.model.Transaction;

public interface CharityDAO {
	
	public Integer donorRegister(Donor donor) throws SQLException;
	public Donor donorLogin(Donor donor) throws SQLException;
	public Boolean donationRequest(DonationRequest request) throws DBException;
	public List<DonationRequest> listDonationRequest() throws DBException, SQLException;
	public Double balanceEnquiry(Long accountNo) throws SQLException;
	public Boolean updateMoney(Transaction transaction);
//	public void transaction(Transaction donor,Transaction admin );
	public void updateRequestAmount(DonationRequest request);
	public Double donationRequestBalance(Integer requestId);
	public Admin adminLogin(Admin admin);

}
