package com.charityapp.consoleUI;

import java.util.Scanner;

import com.charityapp.model.DonationRequest;
import com.charityapp.model.Transaction;
import com.charityapp.service.TransactionService;

public class TransactionUI {
	
	public static void transactionUI()
	{
		/** Donor Details **/
		
		System.out.println("Fund Transaction!");
		
		Transaction donor = new Transaction();
		Transaction admin = new Transaction();
		
		Scanner ip = new Scanner(System.in);
		
		System.out.println("Enter your account no");
		Long accountNumber = ip.nextLong();
		
		System.out.println("Amount");
		Double amount = ip.nextDouble();
		
		donor.setAccountNo(accountNumber);
		donor.setAmount(amount);
		donor.setTransactionType("debit");
		
		/** Admin Details **/
		
		System.out.println("Enter the request id");
		Integer requestId = ip.nextInt();
		
		System.out.println("Enter the admin account no");
		Long adminAccountNo = ip.nextLong();
		
		admin.setAccountNo(adminAccountNo);
		admin.setAmount(amount);
		admin.setTransactionType("credit");
		
		TransactionService.transaction(donor, admin);
		
		/** Update request amount **/

		DonationRequest request = new DonationRequest();

		Double balanceAmount = amount;
		request.setAmount(balanceAmount);
		request.setRequestId(requestId);

		TransactionService.updateRequestAmountService(request);

		ip.close();
	}

}
