package com.charityapp.service;

import java.sql.SQLException;

import com.charityapp.dao.CharityDAO;
import com.charityapp.dao.CharityImpl;
import com.charityapp.model.DonationRequest;
import com.charityapp.model.Transaction;

public class TransactionService {
	
	/** Get balance service **/
	public static Double getBalanceService(Long accountNo)
	{
		CharityDAO charity = new CharityImpl();
		Double amount = null;
		try {
			amount = charity.balanceEnquiry(accountNo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return amount;
	}
	
	/** Get donation request balance service **/
	public static Double getDonationBalanceService(Integer requestId)
	{
		
		Double requestAmount = null;
		CharityDAO charity = new CharityImpl();
		
		requestAmount = charity.donationRequestBalance(requestId);
		
		return requestAmount;
		
	}
	
	/** Update money service **/
	public static void updateMoneyService(Transaction transaction)
	{
		CharityDAO charity = new CharityImpl();
		
		charity.updateMoney(transaction);
	}
	
	/** Update request amount **/
	public static void updateRequestAmountService(DonationRequest request)
	{
		CharityDAO charity = new CharityImpl();
		charity.updateRequestAmount(request);
	}
	
/** Transaction **/
	
	public static Boolean transaction(Transaction donor,Transaction admin)
	{
		
		CharityDAO charity = new CharityImpl();
		
		Boolean isMoneyUpdated = false;

		/** Update donor money **/
		Boolean isDonoerMoneyUpdated = charity.updateMoney(donor);
		
		/** Update admin money **/
		Boolean isAdminMoneyUpdated = charity.updateMoney(admin);
		
		if(isDonoerMoneyUpdated == true && isAdminMoneyUpdated == true)
		{
			isMoneyUpdated = true;
		}
		
		return isMoneyUpdated;
		
	}
}
