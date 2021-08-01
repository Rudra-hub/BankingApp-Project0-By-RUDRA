package com.dao;

import java.sql.Connection;
import com.db.DBConnection;
import com.model.BankingCustomer;
import com.model.BankingEmployee;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BankingEmployeeDAO {

	Connection connection = DBConnection.getDBconnection();
	private final String ADD_CUSTOMER_QUERY = "insert into BankingApp.customer values(?,?,?,?);";
	private final String FIND_ALL_CUSTOMER_QUERY = "select * from BankingApp.customer;";
	private final String CHECK_EMPLOYEE_PASSWORD_QUERY = "select password from BankingApp.employee where employeeid = ?;";
	private final String ADD_EMPLOYEETEMPORARYDATA_QUERY = "insert into bankingapp.employee values(?,?,?);";

//Execution of Insert New Employee Data Query

	public boolean addEmployee(BankingEmployee employee) {
		int res = 0;
		try {
			PreparedStatement statement = connection.prepareStatement(ADD_EMPLOYEETEMPORARYDATA_QUERY);
			statement.setInt(1, employee.getemployeeId());
			statement.setString(2, employee.getemployeeName());
			statement.setString(3, employee.getPassword());

			res = statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (res == 0)
			return false;
		else
			return true;
	}

//Execution of Insert New Customer Data  accessed by Employee Query	

	public boolean addCustomer(BankingCustomer customer) {

		int res = 0;
		try {
			PreparedStatement statement = connection.prepareStatement(ADD_CUSTOMER_QUERY);
			statement.setInt(1, customer.getCustomerId());
			statement.setString(2, customer.getCustomerName());
			statement.setString(3, customer.getPassword());
			statement.setInt(4, customer.getCustomerBalance());

			res = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (res == 0)
			return false;
		else
			return true;
	}

//Execution of Display all data of Customer Table Query

	public List<BankingCustomer> getAllCustomer() {

		List<BankingCustomer> customers = new ArrayList<BankingCustomer>();

		try {
			Statement statement = connection.createStatement();
			ResultSet res = statement.executeQuery(FIND_ALL_CUSTOMER_QUERY);

			while (res.next()) {
				BankingCustomer customer = new BankingCustomer();
				customer.setCustomerId(res.getInt(1));
				customer.setPassword(res.getString(3));
				customer.setCustomerBalance(res.getInt(4));
				customer.setCustomerName(res.getString(2));
				customers.add(customer);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return customers;
	}

// Query used in login for Registered Employee

	public String checkEmployeePassword(int employeeId) {
		List<BankingEmployee> employees = new ArrayList<BankingEmployee>();
		String out = null;
		ResultSet res;
		try {
			PreparedStatement statement = connection.prepareStatement(CHECK_EMPLOYEE_PASSWORD_QUERY);
			statement.setInt(1, employeeId);
			res = statement.executeQuery();

			while (res.next()) {
				BankingEmployee employee = new BankingEmployee();
				employee.setPassword(res.getString(1));
				employees.add(employee);
				out = employee.getPassword();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return out;
	}

}
