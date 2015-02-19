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
	 * @param update
	 */
	public void updateToDatabase(T update);

	/**
	 * Removes object from database; IMPORTANT: method search for the item by ID. Therefore object must be either added to database or imported from database before it is actually
	 * removed. If ID is equal to 0, it will return error message. At the end it sets id to 0.
	 */
	public void removeFromDatabase();

}
