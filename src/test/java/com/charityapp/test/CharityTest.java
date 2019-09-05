package com.charityapp.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.charityapp.consoleUI.AdminUI;
import com.charityapp.consoleUI.DonorUI;
import com.charityapp.consoleUI.TransactionUI;
import com.charityapp.model.Admin;
import com.charityapp.model.DonationRequest;
import com.charityapp.model.Donor;

public class CharityTest {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		System.out.println("=======================================================");
		System.out.println("==============| Welcome To CharityApp |=================");
		System.out.println("=======================================================");
		System.out.println("=======================================================");
		System.out.println("|| 1.Donor Register || 2.Donor Login || 3.Admin Login ||");
		System.out.println("=======================================================");
		
		System.out.println("Enter your choice");
		
		int choice = input.nextInt(); 
		switch(choice)
		{
			case 1:
				DonorUI.donorRegisterUI(input);
				break;
			case 2:
				Donor donor = new Donor();
				donor = DonorUI.donorLoginUI(input);
//				List<DonationRequest> list = new ArrayList<DonationRequest>();
				if(donor.getIsDonorLoggedIn())
				{
//					list = 
					System.out.println("Welcom" +" "+donor.getName());
					System.out.println("==================================");
					System.out.println("|| 1.Donation Request ||");
					System.out.println("Choose your choice");
					int option = input.nextInt();
//					in.close();
					switch(option)
					{
						case 1:
							/**  List requests **/
							AdminUI.listDonationRequest();
							/** Here Transaction **/
							TransactionUI.transactionUI();
							break;
						default:
							System.out.println("Choose any one of the feature");
					}
				}else {
					System.out.println("Login failed!");
				}
				break;
			case 3:
				Admin admin = new Admin();
				admin = AdminUI.adminLoginUI(input);
				if(admin.getIsAdminLoggedIn() == true)
				{
					AdminUI.donationRequestUI(admin,input);
				}
				else {
					System.out.println("Login failed!");
				}
				break;
			default:
				System.out.println("Choose any one of the option!");
		}
		
		input.close();
	}

}
