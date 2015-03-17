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
public class CategoryTest {
	
	public CategoryTest() {
	}

	/**
	 * Test of getId method, of class Category.
	 */
	@Test
	public void testGetId() {
		System.out.println("getId");
		Category instance = null;
		int expResult = 0;
		int result = instance.getId();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getType method, of class Category.
	 */
	@Test
	public void testGetType() {
		System.out.println("getType");
		Category instance = null;
		int expResult = 0;
		int result = instance.getType();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getName method, of class Category.
	 */
	@Test
	public void testGetName() {
		System.out.println("getName");
		Category instance = null;
		String expResult = "";
		String result = instance.getName();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of toString method, of class Category.
	 */
	@Test
	public void testToString() {
		System.out.println("toString");
		Category instance = null;
		String expResult = "";
		String result = instance.toString();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of addToDatabase method, of class Category.
	 */
	@Test
	public void testAddToDatabase() {
		System.out.println("addToDatabase");
		Category instance = null;
		instance.addToDatabase();
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of updateToDatabase method, of class Category.
	 */
	@Test
	public void testUpdateToDatabase() {
		System.out.println("updateToDatabase");
		Category updatedObject = null;
		Category instance = null;
		instance.updateToDatabase(updatedObject);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of removeFromDatabase method, of class Category.
	 */
	@Test
	public void testRemoveFromDatabase() {
		System.out.println("removeFromDatabase");
		Category instance = null;
		instance.removeFromDatabase();
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getCategoryFromDatabaseById method, of class Category.
	 */
	@Test
	public void testGetCategoryFromDatabaseById() {
		System.out.println("getCategoryFromDatabaseById");
		int objectId = 0;
		Category expResult = null;
		Category result = Category.getCategoryFromDatabaseById(objectId);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getCategoryFromDatabaseByName method, of class Category.
	 */
	@Test
	public void testGetCategoryFromDatabaseByName() {
		System.out.println("getCategoryFromDatabaseByName");
		String objectName = "";
		Category expResult = null;
		Category result = Category.getCategoryFromDatabaseByName(objectName);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}
	
}
