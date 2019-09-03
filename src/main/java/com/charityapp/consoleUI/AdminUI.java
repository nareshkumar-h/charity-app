package com.charityapp.consoleUI;

import java.util.Scanner;

import com.charityapp.model.Admin;
import com.charityapp.service.AdminService;
import com.charityapp.service.DonorService;

public class AdminUI {
	
	public static void adminLoginUI()
	{
		System.out.println("Admin Login");
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter your email");
		String adminEmail = input.next();
		
		System.out.println("Enter your password");
		String adminPassword = input.next();
		
		Admin admin = new Admin();
		
		admin.setEmail(adminEmail);
		admin.setPassword(adminPassword);
		
		AdminService.adminLoginService(admin);
		
		input.close();
	}
	
}
