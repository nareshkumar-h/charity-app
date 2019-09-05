package com.charityapp.testcase;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import com.charityapp.dao.CharityDAO;
import com.charityapp.dao.CharityImpl;
import com.charityapp.exception.ValidatorException;
import com.charityapp.model.DonationRequest;
import com.charityapp.model.Donor;
import com.charityapp.model.Transaction;
import com.charityapp.service.DonationService;
import com.charityapp.service.DonorService;
import com.charityapp.service.TransactionService;
import com.charityapp.util.ConnectionUtil;
import com.charityapp.validator.UserValidator;

public class TransactionTestCase {

	/** Connection test **/
	@Test
	public void connectionTest() {
		Connection conn = ConnectionUtil.getConnection();
		assertNotNull(conn);
	}

	/** Create Donation request test **/
	@Test
	public void donationRequestTest() {

		CharityDAO charity = new CharityImpl();
		DonationRequest request = new DonationRequest();

		request.setRequestType("FOOD");
		request.setDescription("");
		request.setRequestAmount(500.40);
		request.setAdminId(1);
		request.setAccountNo(1000001L);
		
		DonationService.donotionRequestService(request);

//		try {
//			UserValidator.donationRequestValidator(request);
//			charity.donationRequest(request);
//		} catch (ValidatorException e) {
//			System.out.println(e.getMessage());
//		}

	}

	/** List donation requests 
	 * @throws SQLException **/
	@Test
	public void listDonationRequestTest() throws SQLException {

//		Charity charity = new CharityDAO();

		List<DonationRequest> list;

//		try {

//			list = charity.listDonationRequest();
//			assertNotNull(list);
//			System.out.println("RequestType:" + list.get(0).getRequestType());
			
			list = DonationService.donationRequestService();
			System.out.println(list);
			System.out.println("===Admin ID===Account No===");
			for(DonationRequest request : list)
			{
				
				System.out.println(request.getRequestType() + request.getAccountNo());
				System.out.println("========================");
			}
//			for(List<DonationRequest> lists : list)
//			{
//				requ
//			}

//		} catch (SQLException e) {
//
//			e.printStackTrace();
//
//		}

	}

	/** Balance Enquiry **/
	@Test
	public void balanceEnquiryTest() {

			Double amount = null;
			Long accountNo = 1000001L;
			amount = TransactionService.getBalanceService(accountNo);
			Double expectAmount = 46500D;
			assertEquals(expectAmount,amount);

	}

//	/** Deposite Money **/
	@Test
	public void depositeMoneyTest()
	{
		CharityDAO charity = new CharityImpl();
		Transaction transaction = new  Transaction();
		
		transaction.setAccountNo(1000001L);
		transaction.setAmount(5000.00);
//		transaction.setPinNo(1234);
		transaction.setTransactionType("debit");
		
		Boolean moneyUpdate = charity.updateMoney(transaction);
		assertEquals(true, moneyUpdate);
	}

	/** Withdraw Money **/
	@Test
	public void withdrawMoneyTest()	
	{
		CharityDAO charity = new CharityImpl();
		Transaction transaction = new  Transaction();
		
		transaction.setAccountNo(1000001L);
		transaction.setAmount(11500.00);
//		transaction.setPinNo(1234);
		transaction.setTransactionType("credit");
		
		Boolean moneyUpdate = charity.updateMoney(transaction);
		assertEquals(true, moneyUpdate);

	}

	/** Get donation request balance **/

	@Test
	public void deonationRequestBalance() {
		CharityDAO charity = new CharityImpl();
		Double balanceAmount = charity.donationRequestBalance(5);
		Double expectAmount = 4000D;
		assertEquals(expectAmount, balanceAmount);
	}

	/** Transaction **/
	@Test
	public void transaction() throws SQLException {

//		Charity charity = new CharityD

		
		Transaction donor = new Transaction();
		Transaction admin = new Transaction();
		
		Double donorAmount = 500D;
		Double adminAmount = 500D;

		donor.setAccountNo(1000001L);
//		donor.setPinNo(1234);
		donor.setAmount(donorAmount);
		donor.setTransactionType("debit");

		admin.setAccountNo(1000002L);
//		admin.setPinNo(1111);
		admin.setAmount(adminAmount);
		admin.setTransactionType("credit");

		System.out.println("Donor balance:" + donorAmount + "Admin balance:" + adminAmount);

		TransactionService.transaction(donor, admin);

		/** Update request amount **/

		DonationRequest request = new DonationRequest();

		Double balanceAmount = 500D;
		request.setAmount(balanceAmount);
		request.setRequestId(5);

		TransactionService.updateRequestAmountService(request);

	}
}