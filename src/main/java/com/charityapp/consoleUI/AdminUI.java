package com.charityapp.consoleUI;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.charityapp.model.Admin;
import com.charityapp.model.DonationRequest;
import com.charityapp.service.AdminService;
import com.charityapp.service.DonationService;
import com.charityapp.service.DonorService;

public class AdminUI {
	
	/** Admin login **/
	public static Admin adminLoginUI(Scanner input)
	{
		
		Admin adminObj = new Admin();
		System.out.println("Admin Login");
		
		System.out.println("Enter your email");
		String adminEmail = input.next();
		
		System.out.println("Enter your password");
		String adminPassword = input.next();
		
		Admin admin = new Admin();
		
		admin.setEmail(adminEmail);
		admin.setPassword(adminPassword);
		
		/** Call login service **/
		adminObj = AdminService.adminLoginService(admin);
		
		return adminObj;
	}
	
	/** Get donation request 
	 * @throws  **/
	public static List<DonationRequest> listDonationRequest()
	{
		List<DonationRequest> list = null;
		try {
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/** Donation Request **/
	
	public static void donationRequestUI(Admin admin,Scanner ip)
	{
		
		System.out.println("=====================");
		System.out.println("|| 1.Create Request ||");
		System.out.println("=====================");
		
		System.out.println("Enter your choice");
		int option = ip.nextInt();
		
		switch(option)
		{
		
			case 1:
		
				System.out.println("Donation Request");
				
				System.out.println("Enter the request type");
				String requestType = ip.next();
				
				System.out.println("Enter the description");
				String descritption = ip.next();
				
				System.out.println("Enter the amount");
				Double amount = ip.nextDouble();
				
				Integer adminId = admin.getId();
				
				System.out.println("Enter your account no");
				Long accountNo = ip.nextLong();
				
				DonationRequest request = new DonationRequest();
		
				request.setRequestType(requestType);
				request.setDescription(descritption);
				request.setRequestAmount(amount);
				request.setAdminId(adminId);
				request.setAccountNo(accountNo);
				
				Boolean requestStatus = DonationService.donotionRequestService(request);
				
				if(requestStatus)
				{
					System.out.println("Request send successfully!");
					
					/** callback function **/
					donationRequestUI(admin, ip);
				}
		
				break;
				
			default:
				System.out.println("Choose correct option");	
	}
		
	}
	
}
