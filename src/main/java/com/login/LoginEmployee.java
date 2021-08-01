package com.login;

//After Login All functionalities for Employee

import java.util.List;
import java.util.Scanner;

import com.BankApp;
import com.RegisterCustomer;
import com.dao.BankingCustomerDAO;
import com.dao.BankingEmployeeDAO;
import com.model.BankingCustomer;
import com.model.BankingEmployee;
import com.model.ViewTransactionLogs;

public class LoginEmployee {

	int choice = 0;
	Scanner sc = new Scanner(System.in);
	BankingEmployeeDAO BankingEmployeeDAO = new BankingEmployeeDAO();
	boolean result;
	BankingEmployee BankingEmployee = new BankingEmployee();

	public int employeeIdPrompt() {
		int employeeId;
		System.out.print("Enter Employee ID :- ");
		employeeId = sc.nextInt();
		return employeeId;
	}

	public String employeePasswordPrompt() {
		String password;
		System.out.print("Enter your Password :- ");
		password = sc.next();
		return password;
	}

	public void loginEmployeeDisplay() {

		while (true) {
			int employeeId = employeeIdPrompt();
			String password = employeePasswordPrompt();

			String employeePassword = BankingEmployeeDAO.checkEmployeePassword(employeeId);
			if (password.equals(employeePassword)) {
				while (true) {

					System.out.println("\n\t Employee Access Page \n");

					System.out.println("\t 1. Delete Customer");
					System.out.println("\t 2. View All Transaction");
					System.out.println("\t 3. View All Customer Details");
					System.out.println("\t 4. Register a New Customer");
					System.out.println("\t 5. Back to Home Page");
					System.out.println("\t 0. Exit");

					System.out.print("Enter your choice : ");
					choice = sc.nextInt();

					switch (choice) {
//Delete Customer by id
					case 1:
						System.out.println("Enter the Customer ID for Delete: ");
						int customerId = sc.nextInt();
						boolean f = BankingCustomerDAO.delete(customerId);
						if (f) {
							System.out.println("Deleted....");
						} else {
							System.out.println("Something went wrong try Again...");
						}
						break;
//Display all Customer Money Transaction
					case 2:
						ViewTransactionLogs.viewTransactions();
						break;
//Display All Customer Table Details
					case 3:
						List<BankingCustomer> allCustomers = BankingEmployeeDAO.getAllCustomer();
						System.out.println(allCustomers);
						break;
//Register new Customer by Employee
					case 4:
						RegisterCustomer registerCustomer = new RegisterCustomer();
						registerCustomer.registerCustomerByEmployee();
						break;
//Back to HOME page
					case 5:
						BankApp bankapp = new BankApp();
						bankapp.startBankingApp();
						break;
//Exit
					case 0:
						System.out.println("\t Thanks for using our App");
						System.exit(0);
						break;
					}

				}
			} else {
				System.out.println("Invalid Username or Password.\nPlease enter correct Username and Password.\n\n");
			}
		}
	}

	public BankingEmployee acceptBankingEmployeeDetails() {
		System.out.print("Please enter Employee id : ");
		int EmployeeId = sc.nextInt();
		System.out.print("Please enter Employee name : ");
		String EmployeeName = sc.next();
		System.out.print("Please enter Password : ");
		String password = sc.next();

		BankingEmployee BankingEmployee = new BankingEmployee(EmployeeId, EmployeeName, password);
		return BankingEmployee;
	}

}
