package com.login;

//After Login All functionalities for Customer

import java.util.Date;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.BankApp;
import com.dao.BankingCustomerDAO;
import com.model.BankingCustomer;

public class LoginCustomer {

	int choice = 0;
	Scanner sc = new Scanner(System.in);
	BankingCustomerDAO BankingCustomerDAO = new BankingCustomerDAO();
	boolean result;
	BankingCustomer BankingCustomer = new BankingCustomer();
	private static Logger transactionLog = Logger.getLogger("debugLogger");

	public int customerIdPrompt() {
		int customerId;
		System.out.print("Enter Customer ID :- ");
		customerId = sc.nextInt();
		return customerId;
	}

	public String customerPasswordPrompt() {
		String password;
		System.out.print("Enter your Password :- ");
		password = sc.next();
		return password;
	}

	public void loginCustomerDisplay() {

		while (true) {
			int customerId = customerIdPrompt();
			String password = customerPasswordPrompt();
			String customerPassword = BankingCustomerDAO.checkCustomerPassword(customerId);
			if (password.equals(customerPassword)) {
				while (true) {
					System.out.println("\t Welcome Customer Login\n");

					System.out.print("1. Balance Check: ");
					System.out.print("\t 2. Amount Transfer: ");
					System.out.print("\t 3. Amount Deposit: ");
					System.out.print("\t 4. Amount Withdraw: ");
					System.out.print("\t 5. Back to Home Page");
					System.out.println("\t 9. Exit");

					System.out.print("\n Please Enter the Service You Want: ");
					choice = sc.nextInt();

					switch (choice) {
//Bank Balance Check
					case 1:
						int customerBalance = BankingCustomerDAO.viewCustomerBalance(customerId);
						System.out.print("Your Account Balance is: " + customerBalance);
						System.out.println("\n\n");
						break;
//Money Transfer
					case 2:
						System.out.println("\t Enter the id to whom you want to transfer money:- ");
						int receiver = sc.nextInt();
						System.out.print("\t Please Enter the Amount:- ");
						int amount = sc.nextInt();
						int customerBal = BankingCustomerDAO.viewCustomerBalance(customerId);
						int checkCustomerId = BankingCustomerDAO.viewCustomerId(receiver);
						if (customerBal >= amount && amount > 0 && checkCustomerId == receiver) {
							BankingCustomerDAO.transferBalance(customerId, receiver, amount);
						} else {
							transactionLog.trace(customerId + " transfer of INR " + amount + " to " + receiver + " on "
									+ new Date() + " was failed");

							if (checkCustomerId != receiver) {
								System.out.println("Invalid Id");
							} else if (amount <= 0) {
								System.out.println("Invalid Amount!");
							} else
								System.out.println("Insufficient Balance");
						}
						break;
//Money Deposit
					case 3:
						System.out.print("Enter amount to deposit:- ");
						int depositAmount = sc.nextInt();
						if (depositAmount > 0) {

							boolean checkDeposit = BankingCustomerDAO.depositAmount(customerId, depositAmount);
							if (checkDeposit == true) {
								System.out.println("INR " + depositAmount + " Deposited Successfully");
								transactionLog.trace(customerId + " deposit of INR " + depositAmount + " on "
										+ new Date() + " was successful");

							} else {
								System.out.println("Some Error occurred please try again");
								transactionLog.trace(customerId + " deposit of INR " + depositAmount + " on "
										+ new Date() + " was failed");

							}
						} else {
							System.out.println("Enter valid Amount to deposit:-");
							transactionLog.trace(customerId + " deposit of INR " + depositAmount + " on " + new Date()
									+ " was failed");

						}
						break;
//Money Withdraw
					case 4:
						System.out.print("Enter the amount to Withdraw:- ");
						int withdrawAmount = sc.nextInt();
						int customerBal1 = BankingCustomerDAO.viewCustomerBalance(customerId);

						if (withdrawAmount > 0 && withdrawAmount <= customerBal1) {
							boolean checkWithdraw = BankingCustomerDAO.withdrawAmount(customerId, withdrawAmount);
							if (checkWithdraw == true) {
								System.out.println("INR " + withdrawAmount + " Withdrawn Successfully");
								transactionLog.trace(customerId + " withdraw of INR " + withdrawAmount + " on "
										+ new Date() + " was successful");
							} else {
								System.out.println("Some Error occurred please try again");
								transactionLog.trace(customerId + " withdraw of INR " + withdrawAmount + " on "
										+ new Date() + " was failed");

							}

						} else {
							System.out.println("Please enter valid Amount to Deposit:-");
							transactionLog.trace(customerId + " withdraw of INR " + withdrawAmount + " on " + new Date()
									+ " was failed");

						}
						break;
//Back to HOME page
					case 5:
						BankApp bankapp = new BankApp();
						bankapp.startBankingApp();
						break;
//Exit
					case 0:
						System.out.println("Thanks for using our Services");
						System.exit(0);
						break;
					}
				}
			} else {
				System.out.println("Invalid Username or Password.\nPlease Enter correct Username and Password.\n\n");

			}
		}
	}

	public static void main(String[] args) {
		LoginCustomer lc = new LoginCustomer();
		lc.loginCustomerDisplay();
	}
}
