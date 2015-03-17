/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author spiotr12 <KnowIdea Team>
 */
public class AccountTest {
	
	public AccountTest() {
	}

	/**
	 * Test of getId method, of class Account.
	 */
	@Test
	public void testGetId() {
		System.out.println("getId");
		Account instance = null;
		int expResult = 0;
		int result = instance.getId();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getCurrency method, of class Account.
	 */
	@Test
	public void testGetCurrency() {
		System.out.println("getCurrency");
		Account instance = null;
		Currency expResult = null;
		Currency result = instance.getCurrency();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getName method, of class Account.
	 */
	@Test
	public void testGetName() {
		System.out.println("getName");
		Account instance = null;
		String expResult = "";
		String result = instance.getName();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getType method, of class Account.
	 */
	@Test
	public void testGetType() {
		System.out.println("getType");
		Account instance = null;
		String expResult = "";
		String result = instance.getType();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getStartAmount method, of class Account.
	 */
	@Test
	public void testGetStartAmount() {
		System.out.println("getStartAmount");
		Account instance = null;
		double expResult = 0.0;
		double result = instance.getStartAmount();
		assertEquals(expResult, result, 0.0);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getBalance method, of class Account.
	 */
	@Test
	public void testGetBalance() {
		System.out.println("getBalance");
		Account instance = null;
		double expResult = 0.0;
		double result = instance.getBalance();
		assertEquals(expResult, result, 0.0);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of setBalance method, of class Account.
	 */
	@Test
	public void testSetBalance() {
		System.out.println("setBalance");
		double balance = 0.0;
		Account instance = null;
		instance.setBalance(balance);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of toString method, of class Account.
	 */
	@Test
	public void testToString() {
		System.out.println("toString");
		Account instance = null;
		String expResult = "";
		String result = instance.toString();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of addToDatabase method, of class Account.
	 */
	@Test
	public void testAddToDatabase() {
		System.out.println("addToDatabase");
		Account instance = null;
		instance.addToDatabase();
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of updateToDatabase method, of class Account.
	 */
	@Test
	public void testUpdateToDatabase() {
		System.out.println("updateToDatabase");
		Account updatedObject = null;
		Account instance = null;
		instance.updateToDatabase(updatedObject);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of removeFromDatabase method, of class Account.
	 */
	@Test
	public void testRemoveFromDatabase() {
		System.out.println("removeFromDatabase");
		Account instance = null;
		instance.removeFromDatabase();
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getAccountFromDatabaseById method, of class Account.
	 */
	@Test
	public void testGetAccountFromDatabaseById() {
		System.out.println("getAccountFromDatabaseById");
		int objectId = 0;
		Account expResult = null;
		Account result = Account.getAccountFromDatabaseById(objectId);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getAccountFromDatabaseByName method, of class Account.
	 */
	@Test
	public void testGetAccountFromDatabaseByName() {
		System.out.println("getAccountFromDatabaseByName");
		String objectName = "";
		Account expResult = null;
		Account result = Account.getAccountFromDatabaseByName(objectName);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of countBalance method, of class Account.
	 */
	@Test
	public void testCountBalance() {
		System.out.println("countBalance");
		Account instance = null;
		double expResult = 0.0;
		double result = instance.countBalance();
		assertEquals(expResult, result, 0.0);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}
	
}
