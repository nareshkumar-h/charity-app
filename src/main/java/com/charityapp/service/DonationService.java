package com.charityapp.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.charityapp.dao.Charity;
import com.charityapp.dao.CharityDAO;
import com.charityapp.exception.ValidatorException;
import com.charityapp.model.DonationRequest;
import com.charityapp.validator.UserValidator;

public class DonationService {
	
	/** Get all request details service **/
	public static List<DonationRequest> donationRequestService() {
		Charity charity = new CharityDAO();
		List<DonationRequest> list = null;
		try {
			list = new ArrayList<DonationRequest>();
			list = charity.listDonationRequest();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/** Donation Request service **/
	public static void donotionRequestService(DonationRequest request)
	{
		Charity charity = new  CharityDAO();
		
		try {
		UserValidator.donationRequestValidator(request);
		charity.donationRequest(request);
		}
		catch(ValidatorException e) {
			System.out.println(e.getMessage());
		}
	}
}
