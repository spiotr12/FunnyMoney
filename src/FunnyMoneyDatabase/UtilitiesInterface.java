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
public interface UtilitiesInterface<T> {

	/**
	 * Adds object to database. Adds generated ID value to object
	 *
	 */
	public void addToDatabase();

	/**
	 * Updates certain object with new information to database. Checks which object fields are different (except id) and construct an update query. Updates both object and
	 * database. Method updates different tables, rows and object depends on the class.
	 *
	 * @param object
	 */
	public void updateToDatabase(T e);

	/**
	 * Removes object from database;
	 */
	public void removeFreomDatabase();

}
