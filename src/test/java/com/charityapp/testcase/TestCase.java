package com.charityapp.testcase;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import com.charityapp.dao.Charity;
import com.charityapp.dao.CharityDAO;
import com.charityapp.exception.ValidatorException;
import com.charityapp.model.Donor;
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
	
	/** Donor register 
	 * @throws SQLException **/
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
}