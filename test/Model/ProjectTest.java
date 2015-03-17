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
public class ProjectTest {
	
	public ProjectTest() {
	}

	/**
	 * Test of getId method, of class Project.
	 */
	@Test
	public void testGetId() {
		System.out.println("getId");
		Project instance = null;
		int expResult = 0;
		int result = instance.getId();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getName method, of class Project.
	 */
	@Test
	public void testGetName() {
		System.out.println("getName");
		Project instance = null;
		String expResult = "";
		String result = instance.getName();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getDescription method, of class Project.
	 */
	@Test
	public void testGetDescription() {
		System.out.println("getDescription");
		Project instance = null;
		String expResult = "";
		String result = instance.getDescription();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of toString method, of class Project.
	 */
	@Test
	public void testToString() {
		System.out.println("toString");
		Project instance = null;
		String expResult = "";
		String result = instance.toString();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of addToDatabase method, of class Project.
	 */
	@Test
	public void testAddToDatabase() {
		System.out.println("addToDatabase");
		Project instance = null;
		instance.addToDatabase();
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of updateToDatabase method, of class Project.
	 */
	@Test
	public void testUpdateToDatabase() {
		System.out.println("updateToDatabase");
		Project updatedObject = null;
		Project instance = null;
		instance.updateToDatabase(updatedObject);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of removeFromDatabase method, of class Project.
	 */
	@Test
	public void testRemoveFromDatabase() {
		System.out.println("removeFromDatabase");
		Project instance = null;
		instance.removeFromDatabase();
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getProjectFromDatabaseById method, of class Project.
	 */
	@Test
	public void testGetProjectFromDatabaseById() {
		System.out.println("getProjectFromDatabaseById");
		int objectId = 0;
		Project expResult = null;
		Project result = Project.getProjectFromDatabaseById(objectId);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getProjectFromDatabaseByName method, of class Project.
	 */
	@Test
	public void testGetProjectFromDatabaseByName() {
		System.out.println("getProjectFromDatabaseByName");
		String objectName = "";
		Project expResult = null;
		Project result = Project.getProjectFromDatabaseByName(objectName);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}
	
}
