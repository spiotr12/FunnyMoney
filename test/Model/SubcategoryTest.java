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
public class SubcategoryTest {
	
	public SubcategoryTest() {
	}

	/**
	 * Test of getId method, of class Subcategory.
	 */
	@Test
	public void testGetId() {
		System.out.println("getId");
		Subcategory instance = null;
		int expResult = 0;
		int result = instance.getId();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getCatId method, of class Subcategory.
	 */
	@Test
	public void testGetCatId() {
		System.out.println("getCatId");
		Subcategory instance = null;
		int expResult = 0;
		int result = instance.getCatId();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getName method, of class Subcategory.
	 */
	@Test
	public void testGetName() {
		System.out.println("getName");
		Subcategory instance = null;
		String expResult = "";
		String result = instance.getName();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of toString method, of class Subcategory.
	 */
	@Test
	public void testToString() {
		System.out.println("toString");
		Subcategory instance = null;
		String expResult = "";
		String result = instance.toString();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of addToDatabase method, of class Subcategory.
	 */
	@Test
	public void testAddToDatabase() {
		System.out.println("addToDatabase");
		Subcategory instance = null;
		instance.addToDatabase();
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of updateToDatabase method, of class Subcategory.
	 */
	@Test
	public void testUpdateToDatabase() {
		System.out.println("updateToDatabase");
		Subcategory updatedObject = null;
		Subcategory instance = null;
		instance.updateToDatabase(updatedObject);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of removeFromDatabase method, of class Subcategory.
	 */
	@Test
	public void testRemoveFromDatabase() {
		System.out.println("removeFromDatabase");
		Subcategory instance = null;
		instance.removeFromDatabase();
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getSubcategoryFromDatabaseById method, of class Subcategory.
	 */
	@Test
	public void testGetSubcategoryFromDatabaseById() {
		System.out.println("getSubcategoryFromDatabaseById");
		int objectId = 0;
		Subcategory expResult = null;
		Subcategory result = Subcategory.getSubcategoryFromDatabaseById(objectId);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getSubcategoryFromDatabaseByName method, of class Subcategory.
	 */
	@Test
	public void testGetSubcategoryFromDatabaseByName() {
		System.out.println("getSubcategoryFromDatabaseByName");
		String objectName = "";
		Subcategory expResult = null;
		Subcategory result = Subcategory.getSubcategoryFromDatabaseByName(objectName);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}
	
}
