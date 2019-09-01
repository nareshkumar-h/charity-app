package com.charityapp.dao;

import java.sql.SQLException;

import com.charityapp.model.DonationRequest;
import com.charityapp.model.Donor;

public interface Charity {
	
	public void donorRegister(Donor donor) throws SQLException;
	public Donor donorLogin(Donor donor) throws SQLException;
	public void donationRequest(DonationRequest request);

}
