package com.charityapp.testcase;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import com.charityapp.dao.Charity;
import com.charityapp.dao.CharityDAO;
import com.charityapp.exception.ValidatorException;
import com.charityapp.model.DonationRequest;
import com.charityapp.model.Donor;
import com.charityapp.model.Transaction;
import com.charityapp.service.DonorService;
import com.charityapp.util.ConnectionUtil;
import com.charityapp.validator.UserValidator;

public class TestCase {

	/** Connection test **/
	@Test
	public void connectionTest()
	{
		Connection conn = ConnectionUtil.getConnection();
		assertNotNull(conn);
	}
	
	/** Donor register **/
	@Test
	public void donorRegister()
	{
		Donor donor = new Donor();
		donor.setName(" ");
		donor.setEmail(" ");
		donor.setPassword("mypass");
		donor.setDob("05-06-1997");
		donor.setGender('m');
		donor.setRole("role_donor");
		DonorService.donorRegisterService(donor);
	}

	
	/** Donor login **/
	@Test
	public void donorLogin()
	{
		Donor donor = new Donor();
		donor.setEmail("krishna@gmail.com");
		donor.setPassword("mypass");
		donor = DonorService.donorLoginService(donor);
		assertNotNull(donor);
		System.out.println(donor.getRole());
	}
	
	/** Create Donation request test **/
	@Test
	public void donationRequestTest()
	{
		
		Charity charity = new  CharityDAO();
		DonationRequest request = new DonationRequest();
			
			request.setRequestType("Education");
			request.setDescription("");
			request.setRequestAmount(500.40);
			request.setAdminId(1);
			
			try {
			UserValidator.donationRequestValidator(request);
			charity.donationRequest(request);
			}
			catch(ValidatorException e)
			{
				System.out.println(e.getMessage());
			}
		
	}
	
	/** List donation requests **/
	@Test
	public void listDonationRequestTest()
	{
		
		Charity charity = new CharityDAO();
		
		List<DonationRequest> list;
		
		try {
			
			list = charity.listDonationRequest();
			assertNotNull(list);
			System.out.println("RequestType:" + list.get(0).getRequestType());
		
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		
	}
	
	/** Balance Enquiry **/
	@Test
	public void balanceEnquiryTest()
	{
		
		Double amount = null;
		Charity charity = new CharityDAO();
		try {
			
			amount = charity.balanceEnquiry(1000001L);
			
			assertNotNull(amount);
			
			System.out.println(amount);
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/** Deposite Money **/
	@Test
	public void depositeMoneyTest()
	{
		Charity charity = new CharityDAO();
		Transaction transaction = new  Transaction();
		
		transaction.setAccountNo(1000001L);
		transaction.setAmount(5000.00);
		transaction.setPinNo(1234);
		
		int rows = charity.updateMoney(transaction);
		System.out.println(rows + " " + "rows affected!");
	}
	
	/** Withdraw Money **/
//	@Test
//	public void withdrawMoneyTest()
//	{
//		Charity charity = new CharityDAO();
//		Transaction transaction = new  Transaction();
//		
//		transaction.setAccountNo(1000001L);
//		transaction.setAmount(1500.00);
//		transaction.setPinNo(1234);
//		
//		int rows = charity.updateMoney(transaction);
//		System.out.println(rows + " " + "rows affected!");
//	}
	
	/** Transaction **/
	@Test
	public void transaction() throws SQLException
	{
		
		Charity charity = new CharityDAO();
		Transaction donor = new Transaction();
		Transaction admin = new Transaction();
//		charity.transaction(formDonor, toAdmin);
		Double donorBalance = charity.balanceEnquiry(1000001L);
		Double adminBalance = charity.balanceEnquiry(1000002L);
		System.out.println("Donor balance:"+donorBalance+"Admin balance:"+adminBalance);
		
		Double donorAmount = donorBalance - 500;
		Double adminAmount = adminBalance + 500;
		
		donor.setAccountNo(1000001L);
		donor.setPinNo(1234);
		donor.setAmount(donorAmount);
		
		admin.setAccountNo(1000002L);
		admin.setPinNo(1111);
		admin.setAmount(adminAmount);
		
		System.out.println("Donor balance:"+donorAmount+"Admin balance:"+adminAmount);
		
		charity.transaction(donor, admin);
		
		/** Update request amount **/
		
		DonationRequest request = new DonationRequest();
		request.setAmount(500D);
		request.setRequestId(4);
		charity.updateRequestAmount(request);
		
	}
}