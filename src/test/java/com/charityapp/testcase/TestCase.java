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
	public void donorRegisterTest() throws ValidatorException, SQLException {
		
		Charity charity = new CharityDAO();
		Donor donor = null;	
		
		try {
			
			donor = new Donor();
			donor.setName("testuser");
			donor.setEmail("");
			donor.setPassword("test");
			donor.setDob("05-06-1997");
			donor.setGender('m');
			donor.setRole("role_donor");
			
			UserValidator.donorRegisterValidator(donor);
			charity.donorRegister(donor);
		} catch (ValidatorException e) {
			/** Display the error msg **/
			System.out.println(e.getMessage());
		}
		
	}
	
	/** Donor login **/
	@Test
	public void donorLoginTest()
	{
		Charity charity = new CharityDAO();
		Donor donor = new Donor();
		
		try {
			donor.setEmail("krishna@gmail.com");
			donor.setPassword("mypass");
			
			UserValidator.donorLoginValidator(donor);
			
			Donor donorObj = charity.donorLogin(donor);
			
			assertNotNull(donorObj);
		}
		catch(ValidatorException e)
		{
			e.printStackTrace();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	/** Donation request test **/
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
		
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		
	}
	
	/** Balance Enquiry **/
	@Test
	public void balanceEnquiryTest()
	{
		
		Transaction transaction = new Transaction();
		
		transaction.setAccountNo(1000001L);
		Charity charity = new CharityDAO();
		try {
			
			transaction = charity.balanceEnquiry(transaction);
			
			assertNotNull(transaction);
			
			System.out.println(transaction.getAmount());
		
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
		
		int rows = charity.depositeMoney(transaction);
		System.out.println(rows + " " + "rows affected!");
	}
	
	/** Transaction **/
	@Test
	public void transaction()
	{
		
	}
}