package com.charityapp.service;

import java.sql.SQLException;

import javax.xml.validation.Validator;

import com.charityapp.dao.Charity;
import com.charityapp.dao.CharityDAO;
import com.charityapp.exception.ValidatorException;
import com.charityapp.model.Donor;
import com.charityapp.validator.UserValidator;

public class DonorService {
	
	/** Donor register service **/
	public static void donorRegisterService(Donor donor)
	{
		Charity charity = new CharityDAO();
		
		try {
			
			UserValidator.donorRegisterValidator(donor);
			charity.donorRegister(donor);
		
		}  catch (ValidatorException e) {
			
			System.out.println(e.getMessage());
		
		} catch(SQLException e) {
			
			e.printStackTrace();
		
		}
	}
	
	/** Donor login service **/
	public static Donor donorLoginService(Donor donor)
	{
		Charity charity = new CharityDAO();
		
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
