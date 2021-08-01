package com;

//Registration Access By Employee for "Customer Registration of new account"

import java.util.Scanner;
import com.dao.BankingCustomerDAO;
import com.model.BankingCustomer;

public class RegisterCustomer {
	boolean result;
	BankingCustomerDAO bankingCustomerDAO = new BankingCustomerDAO();
	Scanner sc = new Scanner(System.in);
	BankingCustomer bankingcustomer = new BankingCustomer();

	public void registerCustomerByEmployee() {
		bankingcustomer = registerCustomerDetails();
		if (bankingcustomer.getCustomerBalance() >= 0) {
			result = bankingCustomerDAO.addCustomer(bankingcustomer);
			if (result) {
				System.out.println("Account with customer name : " + bankingcustomer.getCustomerName()
						+ " and Customer Id : " + bankingcustomer.getCustomerId() + " has been saved successfully.");
			} else {
				System.out.println(
						"Account with customer name : " + bankingcustomer.getCustomerName() + " and Customer Id : "
								+ bankingcustomer.getCustomerId() + " has not been saved successfully.");
			}
		} else {
			System.out.println("Invalid Amount! Amount cannot be less than Zero");
		}
	}

//Customer registration asking for these fields

	public BankingCustomer registerCustomerDetails() {
		System.out.println("Enter the CustomerId:- ");
		int id = sc.nextInt();
		sc.nextLine();
		System.out.println("Enter the Customer Name:- ");
		String name = sc.nextLine();
		System.out.println("Enter the Password:- ");
		String password = sc.nextLine();
		System.out.println("Enter the Starting Balance:- ");
		int balance = sc.nextInt();

		BankingCustomer bankingcustomer = new BankingCustomer(id, name, password, balance);
		return bankingcustomer;

	}

}
