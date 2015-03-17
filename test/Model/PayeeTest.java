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
public class PayeeTest {
	
	public PayeeTest() {
	}

	/**
	 * Test of getId method, of class Payee.
	 */
	@Test
	public void testGetId() {
		System.out.println("getId");
		Payee instance = null;
		int expResult = 0;
		int result = instance.getId();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getName method, of class Payee.
	 */
	@Test
	public void testGetName() {
		System.out.println("getName");
		Payee instance = null;
		String expResult = "";
		String result = instance.getName();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getDescription method, of class Payee.
	 */
	@Test
	public void testGetDescription() {
		System.out.println("getDescription");
		Payee instance = null;
		String expResult = "";
		String result = instance.getDescription();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of toString method, of class Payee.
	 */
	@Test
	public void testToString() {
		System.out.println("toString");
		Payee instance = null;
		String expResult = "";
		String result = instance.toString();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of addToDatabase method, of class Payee.
	 */
	@Test
	public void testAddToDatabase() {
		System.out.println("addToDatabase");
		Payee instance = null;
		instance.addToDatabase();
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of updateToDatabase method, of class Payee.
	 */
	@Test
	public void testUpdateToDatabase() {
		System.out.println("updateToDatabase");
		Payee updatedObject = null;
		Payee instance = null;
		instance.updateToDatabase(updatedObject);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of removeFromDatabase method, of class Payee.
	 */
	@Test
	public void testRemoveFromDatabase() {
		System.out.println("removeFromDatabase");
		Payee instance = null;
		instance.removeFromDatabase();
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getPayeeFromDatabaseById method, of class Payee.
	 */
	@Test
	public void testGetPayeeFromDatabaseById() {
		System.out.println("getPayeeFromDatabaseById");
		int objectId = 0;
		Payee expResult = null;
		Payee result = Payee.getPayeeFromDatabaseById(objectId);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getPayeeFromDatabaseByName method, of class Payee.
	 */
	@Test
	public void testGetPayeeFromDatabaseByName() {
		System.out.println("getPayeeFromDatabaseByName");
		String objectName = "";
		Payee expResult = null;
		Payee result = Payee.getPayeeFromDatabaseByName(objectName);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}
	
}
