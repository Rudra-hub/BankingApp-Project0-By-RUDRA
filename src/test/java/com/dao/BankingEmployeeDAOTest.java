package com.dao;

import static org.junit.Assert.*;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import com.model.BankingCustomer;

public class BankingEmployeeDAOTest {
	private BankingEmployeeDAO bankingEmployeerDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		bankingEmployeerDAO = new BankingEmployeeDAO();
	}

	@After
	public void tearDown() throws Exception {
		bankingEmployeerDAO = null;
	}

//Test for Add Customer Function	

	@Test
	public void testAddCustomer() {
		BankingCustomerDAO bankingCustomerDAO = new BankingCustomerDAO();

		int customerIdToTest = 90;

		BankingCustomer addedcustomer = new BankingCustomer(customerIdToTest, "Demo", "ab", 120);
		bankingCustomerDAO.addCustomer(addedcustomer);

		int retrievedCustomer = bankingCustomerDAO.viewCustomerId(90);

		assertEquals(customerIdToTest, retrievedCustomer);

		bankingCustomerDAO.deleteCustomer(customerIdToTest);
	}

//Test for Display Customer table Function	
	@Test
	public void testGetAllCustomer() {

		int customerIdToTest = -999;
		BankingCustomerDAO bankingCustomerdao = new BankingCustomerDAO();
		List<BankingCustomer> originalCustomers1 = bankingEmployeerDAO.getAllCustomer();
		bankingCustomerdao.addCustomer(new BankingCustomer(customerIdToTest, "Demo", "abcd", 60));
		List<BankingCustomer> originalCustomers2 = bankingEmployeerDAO.getAllCustomer();

		assertEquals(originalCustomers2.size(), originalCustomers1.size() + 1);

		bankingCustomerdao.deleteCustomer(customerIdToTest);
	}

}
