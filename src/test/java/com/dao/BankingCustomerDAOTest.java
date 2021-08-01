package com.dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import com.model.BankingCustomer;

public class BankingCustomerDAOTest {
	private BankingCustomerDAO bankingCustomerDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		bankingCustomerDAO = new BankingCustomerDAO();
	}

	@After
	public void tearDown() throws Exception {
		bankingCustomerDAO = null;
	}

//Test for Add Customer Function

	@Test
	public void testAddCustomer() {
		int customerIdToTest = 90;

		BankingCustomer addedcustomer = new BankingCustomer(customerIdToTest, "DemoCustomer", "abc", 120);
		bankingCustomerDAO.addCustomer(addedcustomer);

		int retrievedCustomer = bankingCustomerDAO.viewCustomerId(90);

		assertEquals(customerIdToTest, retrievedCustomer);

		// deleting the product after testing
		bankingCustomerDAO.deleteCustomer(customerIdToTest);
	}

//Test for Deposit amount Function

	@Test
	public void testDepositAmount() {
		int customerIdToTest = 90;
		int amountToTest = 100;

		BankingCustomer addedcustomer = new BankingCustomer(customerIdToTest, "Demo", "ab", 120);
		bankingCustomerDAO.addCustomer(addedcustomer);
		bankingCustomerDAO.depositAmount(customerIdToTest, amountToTest);
		int retrievedCustomer = bankingCustomerDAO.viewCustomerBalance(90);

		assertEquals(amountToTest + 120, retrievedCustomer);

		// deleting the product after testing
		bankingCustomerDAO.deleteCustomer(customerIdToTest);
	}

//Test for Withdraw amount Function

	@Test
	public void testWithdrawAmount() {
		int customerIdToTest = 90;
		int amountToTest = 100;

		BankingCustomer addedcustomer = new BankingCustomer(customerIdToTest, "Demo", "ab", 120);
		bankingCustomerDAO.addCustomer(addedcustomer);
		bankingCustomerDAO.withdrawAmount(customerIdToTest, amountToTest);
		int retrievedCustomer = bankingCustomerDAO.viewCustomerBalance(90);

		assertEquals(120 - amountToTest, retrievedCustomer);

		// deleting the product after testing
		bankingCustomerDAO.deleteCustomer(customerIdToTest);
	}

}
