package com.charityapp.service;

import java.sql.SQLException;

import com.charityapp.dao.Charity;
import com.charityapp.dao.CharityDAO;
import com.charityapp.model.DonationRequest;
import com.charityapp.model.Transaction;

public class TransactionService {
	
	/** Get balance service **/
	public static Double getBalanceService(Long accountNo)
	{
		Charity charity = new CharityDAO();
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
		Charity charity = new CharityDAO();
		
		requestAmount = charity.donationRequestBalance(requestId);
		
		return requestAmount;
		
	}
	
	/** Update money service **/
	public static void updateMoneyService(Transaction transaction)
	{
		Charity charity = new CharityDAO();
		
		charity.updateMoney(transaction);
	}
	
	/** Update request amount **/
	public static void updateRequestAmountService(DonationRequest request)
	{
		Charity charity = new CharityDAO();
		charity.updateRequestAmount(request);
	}
	
/** Transaction **/
	
	public static void transaction(Transaction donor,Transaction admin)
	{
		
		Charity charity = new CharityDAO();

		/** Update donor money **/
		charity.updateMoney(donor);
		
		/** Update admin money **/
		charity.updateMoney(admin);
		
	}
}
