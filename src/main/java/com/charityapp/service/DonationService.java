package com.charityapp.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.charityapp.dao.CharityDAO;
import com.charityapp.dao.CharityImpl;
import com.charityapp.exception.DBException;
import com.charityapp.exception.ValidatorException;
import com.charityapp.model.DonationRequest;
import com.charityapp.validator.UserValidator;

public class DonationService {
	
	/** Get all request details service **/
	public static List<DonationRequest> donationRequestService() throws SQLException {
		CharityDAO charity = new CharityImpl();
		List<DonationRequest> list = null;
		try {
			list = new ArrayList<DonationRequest>();
			list = charity.listDonationRequest();
		} catch (DBException e) {
			System.out.println(e.getMessage());
		}
		return list;
	}
	
	/** Donation Request service **/
	public static Boolean donotionRequestService(DonationRequest request)
	{
		CharityDAO charity = new  CharityImpl();
		Boolean requestStatus = false;
		try {
		UserValidator.donationRequestValidator(request);
		requestStatus = charity.donationRequest(request);
		}
		catch(ValidatorException e) {
			System.out.println(e.getMessage());
		}
		catch(DBException e)
		{
			System.out.println(e.getMessage());
		}
		return requestStatus;
	}
}
