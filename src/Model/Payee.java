/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import static Control.TestMain.FMLOGGER;
import FunnyMoneyDatabase.FunnyDB;
import static FunnyMoneyDatabase.FunnyDB.con;
import static FunnyMoneyDatabase.StaticUtilities.removeLastComa;
import FunnyMoneyDatabase.UtilitiesInterface;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author spiotr12 <KnowIdea Team>
 */
public class Payee implements UtilitiesInterface<Payee> {

	private int id;
	private String name, description;
	private boolean editable;

	/**
	 * Full field constructor. Use for DB queries.
	 *
	 * @param name
	 * @param description
	 * @param editable Set to fale only by application, user's payees are set to true.
	 * @param id
	 */
	public Payee(String name, String description, boolean editable, int id) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.editable = editable;
	}

	/**
	 * Constructor without ID. Use addToDatabase() method to add to database and get register ID.
	 *
	 * @param name
	 * @param description
	 * @param editable Set to fale only by application, user's payees are set to true.
	 */
	public Payee(String name, String description, boolean editable) {
		this.name = name;
		this.description = description;
		this.editable = editable;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		return "Payee \"" + name + "\" (id = " + id + "): " + description;
	}

	//---------------- IMPLEMENTED METHODS -------------------------------------------------------------------------------------
	@Override
	public void addToDatabase() {
		try {
			Statement stmt = con.createStatement();
			String sql = "INSERT INTO Payee (payee_name, description, editable)\n"
					+ "VALUES ('" + this.name + "', '" + this.description + "', " + this.editable + ")";
			stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			// get index of this operation
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);
			}
			// close connections
			stmt.close();
			rs.close();
			FMLOGGER.log(Level.INFO, "{0} was added to database", this.toString());
		} catch (SQLException ex) {
			Logger.getLogger(Payee.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void updateToDatabase(Payee updatedObject) {
		//TEST ME: Update 
		boolean change = false;
		try {
			Statement stmt = con.createStatement();
			String sql = "UPDATE Payee \n" + "SET ";
			// do test if there is a value, test if new value is different then old one
			// set name
			if (updatedObject.getName() != null && !this.name.equals(updatedObject.getName())) {
				sql += "payee_name = '" + updatedObject.getName() + "', ";
				this.name = updatedObject.getName();
				change = true;
			}
			// set symbol
			if (updatedObject.getDescription() != null && !this.description.equals(updatedObject.getDescription())) {
				sql += "description = '" + updatedObject.getDescription() + "', ";
				this.description = updatedObject.getDescription();
				change = true;
			}

			// finish and execute querry
			if (change) {
				sql = removeLastComa(sql);	// removes coma 
				sql += " \n" + "WHERE payee_id = " + this.id;	// finish sql query
				stmt.executeUpdate(sql);	// update row
				FMLOGGER.log(Level.INFO, "{0} with id = {1} was updatet to: {2}", new Object[]{this.getClass().getSimpleName(), this.id, updatedObject.toString()});
			} else {
				System.out.println("Objects are the same, no new changes");
			}
			// close connections
			stmt.close();
		} catch (SQLException ex) {
			Logger.getLogger(Payee.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void removeFromDatabase() {
		if (this.id > 0) {
			try {
				Statement stmt = con.createStatement();
				String sql = "DELETE FROM Payee \n"
						+ "WHERE payee_id = " + this.id;
				stmt.executeUpdate(sql);
				stmt.close();
				// sets id to 0, what is an equivalent to delete object from database, because it does not have an ID anymore. 
				this.id = 0;

			} catch (SQLException ex) {
				Logger.getLogger(Payee.class
						.getName()).log(Level.SEVERE, null, ex);
			}
		} else {
			FMLOGGER.log(Level.WARNING, "ID for {0} \"{1}\" is not specified", new Object[]{this.getClass().getSimpleName(), this.getName()});
		}
	}

	//---------------- STATIC METHODS -------------------------------------------------------------------------------------
	/**
	 * Returns object found by given ID.
	 *
	 * @param objectId Object ID.
	 * @return Object from database.
	 */
	public static Payee getPayeeFromDatabaseById(int objectId) {
		Map hm = FunnyDB.getObjectDataById("Payee", objectId);
		// get values
		int id = (Integer) hm.get("payee_id");
		String name = (String) hm.get("payee_name");
		String description = (String) hm.get("description");
		boolean editable = (boolean) hm.get("editable");
		// create object
		Payee payee = new Payee(name, description, editable, id);
		return payee;
	}

	/**
	 * Returns object found by given name.
	 *
	 * @param objectName
	 * @return Object from database.
	 */
	public static Payee getPayeeFromDatabaseByName(String objectName) {
		Map hm = FunnyDB.getObjectDataByName("Payee", objectName);
		// get values
		int id = (Integer) hm.get("payee_id");
		String name = (String) hm.get("payee_name");
		String description = (String) hm.get("description");
		boolean editable = (boolean) hm.get("editable");
		// create object
		Payee payee = new Payee(name, description, editable, id);
		return payee;
	}

	//---------------- CUSTOM METHODS -------------------------------------------------------------------------------------
}
