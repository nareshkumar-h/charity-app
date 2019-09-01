package com.charityapp.validator;

import com.charityapp.exception.ValidatorException;
import com.charityapp.model.Donor;

public class UserValidator {
	
	/** Validate donor details **/
	public static void donorRegisterValidator(Donor donor) throws ValidatorException
	{
		String name = donor.getName();
		String email = donor.getEmail();
		String password = donor.getPassword();
		
		/** Donor name validation **/
		if(name == null || "".equals(name.trim()))
		{
			throw new ValidatorException("Invalid donor name!");
		}
		/** Email validation **/
		if(email == null || "".equals(email.trim()))
		{
			throw new ValidatorException("Invalid email!");
		}
		/** Password validation **/
		if(password == null || "".equals(password.trim()))
		{
			throw new ValidatorException("Invalid password!");
		}
	}
	
	public static void donorLoginValidator(Donor donor) throws ValidatorException
	{
		String email = donor.getEmail();
		String password = donor.getPassword();
		
		if(email == null || "".equals(email.trim()))
		{
			throw new ValidatorException("Invalid email!");
		}
		
		if(password == null || "".equals(password.trim()))
		{
			throw new ValidatorException("Invlaid password!");
		}

	}
}
	
