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
public class Category implements UtilitiesInterface<Category> {

	private int id, type;
	private String name;

	/**
	 * Full field constructor. Use for DB queries.
	 *
	 * @param name
	 * @param type
	 * @param id
	 */
	public Category(String name, int type, int id) {
		this.id = id;
		this.type = type;
		this.name = name;
	}

	/**
	 * Constructor without ID. Use addToDatabase() method to add to database and get register ID.
	 *
	 * @param name
	 * @param type
	 */
	public Category(String name, int type) {
		this.type = type;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public int getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Category \"" + name + "\" (id = " + id + ") has type: " + type;
	}

	//---------------- IMPLEMENTED METHODS -------------------------------------------------------------------------------------
	@Override
	public void addToDatabase() {
		try {
			Statement stmt = con.createStatement();
			String sql = "INSERT INTO Category (category_name, category_type)\n"
					+ "VALUES ('" + this.name + "', " + this.type + ")";
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
			Logger.getLogger(Category.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void updateToDatabase(Category updatedObject) {
//TEST ME: Update category
		boolean change = false;
		try {
			Statement stmt = con.createStatement();
			String sql = "UPDATE Category \n" + "SET ";
			// do test if there is a value, test if new value is different then old one
			// set name
			if (updatedObject.getName() != null && !this.name.equals(updatedObject.getName())) {
				sql += "category_name = '" + updatedObject.getName() + "', ";
				this.name = updatedObject.getName();
				change = true;
			}
			// set type
			if (this.type != updatedObject.getType()) {
				sql += "description = '" + updatedObject.getType() + "', ";
				this.type = updatedObject.getType();
				change = true;
			}

			// finish and execute querry
			if (change) {
				sql = removeLastComa(sql);	// removes coma 
				sql += " \n" + "WHERE category_id = " + this.id;	// finish sql query
				stmt.executeUpdate(sql);	// update row
				FMLOGGER.log(Level.INFO, "{0} with id = {1} was updatet to: {2}", new Object[]{this.getClass().getSimpleName(), this.id, updatedObject.toString()});
			} else {
				System.out.println("Objects are the same, no new changes");
			}
			// close connections
			stmt.close();
		} catch (SQLException ex) {
			Logger.getLogger(Category.class.getName()).log(Level.SEVERE, null, ex);
		}	}

	@Override
	public void removeFromDatabase() {
		if (this.id > 0) {
			try {
				Statement stmt = con.createStatement();
				String sql = "DELETE FROM Category \n"
						+ "WHERE category_id = " + this.id;
				stmt.executeUpdate(sql);
				stmt.close();
				// sets id to 0, what is an equivalent to delete object from database, because it does not have an ID anymore. 
				this.id = 0;
			} catch (SQLException ex) {
				Logger.getLogger(Category.class.getName()).log(Level.SEVERE, null, ex);
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
	public static Category getCategoryFromDatabaseById(int objectId) {
		Map hm = FunnyDB.getObjectDataById("Category", objectId);
		// get values
		int id = (Integer) hm.get("category_id");
		String name = (String) hm.get("category_name");
		int type = (Integer) hm.get("category_type");
		// create object
		Category category = new Category(name, type, id);
		return category;
	}

	/**
	 * Returns object found by given name.
	 *
	 * @param objectName
	 * @return Object from database.
	 */
	public static Category getCategoryFromDatabaseByName(String objectName) {
		Map hm = FunnyDB.getObjectDataByName("Category", objectName);
		// get values
		int id = (Integer) hm.get("category_id");
		String name = (String) hm.get("category_name");
		int type = (Integer) hm.get("category_type");
		// create object
		Category category = new Category(name, type, id);
		return category;
	}
	//---------------- CUSTOM METHODS -------------------------------------------------------------------------------------

}
