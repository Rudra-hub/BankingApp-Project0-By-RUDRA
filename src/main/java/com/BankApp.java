package com;

import java.util.Scanner;

import com.login.LoginCustomer;
import com.login.LoginEmployee;

public class BankApp {

	int choice = 0;
	char type;
	Scanner sc = new Scanner(System.in);

	public void startBankingApp() {

		while (true) {
			System.out.println("\t\t\t\t WELCOME TO MY BANKING-APP\n\n");

			System.out.println("\t APP-FEATURES\n\n");
			System.out.println("\t 1. Login");
			System.out.println("\t 2. Register");
			System.out.println("\t 3. Know about Our Services");
			System.out.println("\t 4. Developer Details");
			System.out.println("\t 0. Exit");

			System.out.print("Enter your choice :- ");
			choice = sc.nextInt();

			switch (choice) {
//Login Access
			case 1:
				while (true) {
					System.out.println("\t Login Page\n\n");

					System.out.print("Please Enter Your type of Account (C/E) : ");
					type = sc.next().charAt(0);

					if (type == 'C' || type == 'c') {

						LoginCustomer loginCustomer = new LoginCustomer();
						loginCustomer.loginCustomerDisplay();
						break;
					} else if (type == 'E' || type == 'e') {
						LoginEmployee loginEmployee = new LoginEmployee();
						loginEmployee.loginEmployeeDisplay();
					} else {
						System.out.println("Please enter valid input!!!");
					}
				}
				break;
			case 2:
//Registration
				while (true) {
					System.out.println("\t Registration Page\n\n");

					System.out.print("Please Enter Your type of Account (C/E) : ");
					type = sc.next().charAt(0);
					ApplyAccounts applyaccount = new ApplyAccounts();
					if (type == 'C' || type == 'c') {
						applyaccount.applyCustomer();

					} else if (type == 'E' || type == 'e') {
						applyaccount.applyEmployee();

					} else {
						System.out.println("INVALIDE INPUT TYPE");
					}
				}
//Bank services details
			case 3:
				BankAppServices b = new BankAppServices();
				b.bank();
				break;
//Developer Details
			case 4:

				DeveloperDetails d = new DeveloperDetails();
				d.devop();
				break;
//Exit
			case 0:
				System.out.println("\t Thanks for using Our App");
				System.exit(0);
				break;
			}
		}
	}
}
