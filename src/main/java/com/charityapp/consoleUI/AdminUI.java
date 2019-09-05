package com.charityapp.consoleUI;

import java.util.List;
import java.util.Scanner;

import com.charityapp.model.Admin;
import com.charityapp.model.DonationRequest;
import com.charityapp.service.AdminService;
import com.charityapp.service.DonationService;
import com.charityapp.service.DonorService;

public class AdminUI {
	
	/** Admin login **/
	public static Admin adminLoginUI()
	{
		Admin adminObj = new Admin();
		System.out.println("Admin Login");
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter your email");
		String adminEmail = input.next();
		
		System.out.println("Enter your password");
		String adminPassword = input.next();
		
		Admin admin = new Admin();
		
		admin.setEmail(adminEmail);
		admin.setPassword(adminPassword);
		
		/** Call login service **/
		adminObj = AdminService.adminLoginService(admin);
		
		input.close();
		
		return adminObj;
	}
	
	/** Get donation request **/
	public static List<DonationRequest> listDonationRequest()
	{
		List<DonationRequest> list;
		list = DonationService.donationRequestService();
		
		System.out.println("Donation Requests!");
		
		System.out.println("=======================================================");
		System.out.println("|| ID || Type || description || Amount || AccountNo ||");
		System.out.println("=======================================================");
		
		for(DonationRequest request : list)
		{
			System.out.println("|| " + request.getRequestId() + " ||" 
		+request.getRequestType() + "||" + request.getDescription() + "||" + request.getRequestAmount() +"||" + request.getAccountNo() + "||");
		}
		return list;
	}
	
	/** Donation Request **/
	
	public static void donationRequestUI(Admin admin)
	{
		System.out.println("Donation Request");
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter the request type");
		String requestType = input.next();
		
		System.out.println("Enter the description");
		String descritption = input.next();
		
		System.out.println("Enter the amount");
		Double amount = input.nextDouble();
		
		Integer adminId = admin.getId();
		
		System.out.println("Enter your account no");
		Long accountNo = input.nextLong();
		
		DonationRequest request = new DonationRequest();

		request.setRequestType(requestType);
		request.setDescription(descritption);
		request.setRequestAmount(amount);
		request.setAdminId(adminId);
		request.setAccountNo(accountNo);
		
		DonationService.donotionRequestService(request);
	}
	
}
