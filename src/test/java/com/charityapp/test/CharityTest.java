package com.charityapp.test;

import java.util.Scanner;

import com.charityapp.consoleUI.AdminUI;
import com.charityapp.consoleUI.DonorUI;

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
				DonorUI.donorRegisterUI();
				break;
			case 2:
				DonorUI.donorLoginUI();
				break;
			case 3:
				AdminUI.adminLoginUI();
				break;
			default:
				System.out.println("Choose any one of the option!");
		}

	}

}
