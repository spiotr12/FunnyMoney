/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunnyMoneyDatabase;

/**
 *
 * @author Piotrek
 */
public interface UtilitiesInterface {

	/**
	 * Adds object to database. Adds generated ID value to object
	 *
	 */
	public void addToDatabase();

	/**
	 * Updates certain object with new information to database.
	 *
	 * @param object
	 */
	public void updateToDatabase(Object object);

	/**
	 * Removes object from database;
	 */
	public void removeFreomDatabase();

}
