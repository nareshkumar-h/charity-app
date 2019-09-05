package com.charityapp.consoleUI;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Scanner;

import com.charityapp.model.Donor;
import com.charityapp.service.DonorService;

public class DonorUI {
	
	/** Donor Register UI **/
	public static void donorRegisterUI()
	{
		
		Scanner ip = new Scanner(System.in);
		
		System.out.println("++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("++++++++++++++ DONOR REGISTER ++++++++++++");
		System.out.println("++++++++++++++++++++++++++++++++++++++++++");
		
		System.out.println("Enter your name");
		String donorName = ip.next();
		
		System.out.println("Enter your Email");
		String donorEmail = ip.next();
		
		System.out.println("Enter your password");
		String donorPassword = ip.next();
		
		System.out.println("Enter your date of birth");
		String donorDOB = ip.next();
		
		System.out.println("Enter your gender");
		Character donorGender = ip.next().charAt(0);
		
		String donorRole = "role_donor";
		
		Donor donor = new Donor();
		donor.setName(donorName);
		donor.setEmail(donorEmail);
		donor.setPassword(donorPassword);
		donor.setDob(donorDOB);
		donor.setGender(donorGender);
		donor.setRole(donorRole);
		DonorService.donorRegisterService(donor);
		
		ip.close();
	}
	
	/** Donor Login UI **/
	public static Donor donorLoginUI()
	{
		Scanner ip = new Scanner(System.in);
		
		System.out.println("++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("++++++++++++++ DONOR Login +++++++++++++++");
		System.out.println("++++++++++++++++++++++++++++++++++++++++++");
		
		System.out.println("Enter your email");
		String donorEmail = ip.next();
		
		System.out.println("Enter your password");
		String donorPassword = ip.next();
		
		Donor donor = new Donor();
		donor.setEmail(donorEmail);
		donor.setPassword(donorPassword);
		donor = DonorService.donorLoginService(donor);

		ip.close();
		
		return donor;
		
	}

}
