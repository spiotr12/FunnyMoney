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
 * @author Piotrek
 */
public class CurrencyTest {
	
	public CurrencyTest() {
	}

	/**
	 * Test of getId method, of class Currency.
	 */
	@Test
	public void testGetId() {
		System.out.println("getId");
		Currency instance = null;
		int expResult = 0;
		int result = instance.getId();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getPosition method, of class Currency.
	 */
	@Test
	public void testGetPosition() {
		System.out.println("getPosition");
		Currency instance = null;
		int expResult = 0;
		int result = instance.getPosition();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getName method, of class Currency.
	 */
	@Test
	public void testGetName() {
		System.out.println("getName");
		Currency instance = null;
		String expResult = "";
		String result = instance.getName();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getSymbol method, of class Currency.
	 */
	@Test
	public void testGetSymbol() {
		System.out.println("getSymbol");
		Currency instance = null;
		String expResult = "";
		String result = instance.getSymbol();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of toString method, of class Currency.
	 */
	@Test
	public void testToString() {
		System.out.println("toString");
		Currency instance = null;
		String expResult = "";
		String result = instance.toString();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of addToDatabase method, of class Currency.
	 */
	@Test
	public void testAddToDatabase() {
		System.out.println("addToDatabase");
		Currency instance = null;
		instance.addToDatabase();
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of updateToDatabase method, of class Currency.
	 */
	@Test
	public void testUpdateToDatabase() {
		System.out.println("updateToDatabase");
		Currency currency = null;
		Currency instance = null;
		instance.updateToDatabase(currency);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of removeFromDatabase method, of class Currency.
	 */
	@Test
	public void testRemoveFreomDatabase() {
		System.out.println("removeFreomDatabase");
		Currency instance = null;
		instance.removeFromDatabase();
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getCurrencyFromDatabaseById method, of class Currency.
	 */
	@Test
	public void testGetCurrencyFromDatabaseById() {
		System.out.println("getCurrencyFromDatabaseById");
		int id = 0;
		Currency expResult = null;
		Currency result = Currency.getCurrencyFromDatabaseById(id);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getCurrencyFromDatabaseByName method, of class Currency.
	 */
	@Test
	public void testGetCurrencyFromDatabaseByName() {
		System.out.println("getCurrencyFromDatabaseByName");
		String name = "";
		Currency expResult = null;
		Currency result = Currency.getCurrencyFromDatabaseByName(name);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getValueWithCurrency method, of class Currency.
	 */
	@Test
	public void testGetValueWithCurrency() {
		System.out.println("getValueWithCurrency");
		double value = 0.0;
		Currency instance = null;
		String expResult = "";
		String result = instance.getValueWithCurrency(value);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}
	
}
