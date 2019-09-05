package com.charityapp.service;

import java.sql.SQLException;

import javax.xml.validation.Validator;

import com.charityapp.dao.CharityDAO;
import com.charityapp.dao.CharityImpl;
import com.charityapp.exception.ValidatorException;
import com.charityapp.model.Donor;
import com.charityapp.validator.UserValidator;

public class DonorService {
	
	/** Donor register service **/
	public static Integer donorRegisterService(Donor donor)
	{
		CharityDAO charity = new CharityImpl();
		int rows = 0;
		try {
			
			UserValidator.donorRegisterValidator(donor);
			rows = charity.donorRegister(donor);
		
		}  catch (ValidatorException e) {
			
			System.out.println(e.getMessage());
		
		} catch(SQLException e) {
			
			e.printStackTrace();
		
		}
		return rows;
	}
	
	/** Donor login service **/
	public static Donor donorLoginService(Donor donor)
	{
		CharityDAO charity = new CharityImpl();
		
		try {
			UserValidator.donorLoginValidator(donor);
			donor = charity.donorLogin(donor);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch(ValidatorException e) {
			System.out.println(e.getMessage());
		}
		
		return donor;
	}
	
}
