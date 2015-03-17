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
public class Project implements UtilitiesInterface<Project> {

	private int id;
	private String name, description;

	/**
	 * Full field constructor. Use for DB queries.
	 *
	 * @param name
	 * @param id
	 */
	public Project(String name, String description, int id) {
		this.id = id;
		this.description = description;
		this.name = name;
	}

	/**
	 * Constructor without ID. Use addToDatabase() method to add to database and get register ID.
	 *
	 * @param name
	 */
	public Project(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public String getDescription(){
		return description;
	}

	@Override
	public String toString() {
		return "Project \"" + name + "\" (id = " + id + "): " + description;
	}

	//---------------- IMPLEMENTED METHODS -------------------------------------------------------------------------------------
	@Override
	public void addToDatabase() {
		try {
			Statement stmt = con.createStatement();
			String sql = "INSERT INTO Project (project_name, description)\n"
					+ "VALUES ('" + this.name + "', '" + this.description + "')";
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
			Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void updateToDatabase(Project updatedObject) {
		//TEST ME: Update project
		boolean change = false;
		try {
			Statement stmt = con.createStatement();
			String sql = "UPDATE Project \n" + "SET ";
			// do test if there is a value, test if new value is different then old one
			// set name
			if (updatedObject.getName() != null && !this.name.equals(updatedObject.getName())) {
				sql += "project_name = '" + updatedObject.getName() + "', ";
				this.name = updatedObject.getName();
				change = true;
			}
			// set description
			if (updatedObject.getDescription()!= null && !this.name.equals(updatedObject.getDescription())) {
				sql += "description = '" + updatedObject.getDescription() + "', ";
				this.description = updatedObject.getDescription();
				change = true;
			}

			// finish and execute querry
			if (change) {
				sql = removeLastComa(sql);	// removes coma 
				sql += " \n" + "WHERE project_id = " + this.id;	// finish sql query
				stmt.executeUpdate(sql);	// update row
				FMLOGGER.log(Level.INFO, "{0} with id = {1} was updatet to: {2}", new Object[]{this.getClass().getSimpleName(), this.id, updatedObject.toString()});
			} else {
				System.out.println("Objects are the same, no new changes");
			}
			// close connections
			stmt.close();
		} catch (SQLException ex) {
			Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void removeFromDatabase() {
		if (this.id > 0) {
			try {
				Statement stmt = con.createStatement();
				String sql = "DELETE FROM Project \n"
						+ "WHERE project_id = " + this.id;
				stmt.executeUpdate(sql);
				stmt.close();
				// sets id to 0, what is an equivalent to delete object from database, because it does not have an ID anymore. 
				this.id = 0;
			} catch (SQLException ex) {
				Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
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
	public static Project getProjectFromDatabaseById(int objectId) {
		Map hm = FunnyDB.getObjectDataById("Project", objectId);
		// get values
		int id = (Integer) hm.get("project_id");
		String name = (String) hm.get("project_name");
		String description = (String) hm.get("description");
		// create object
		Project project = new Project(name, description, id);
		return project;
	}

	/**
	 * Returns object found by given name.
	 *
	 * @param objectName
	 * @return Object from database.
	 */
	public static Project getProjectFromDatabaseByName(String objectName) {
		Map hm = FunnyDB.getObjectDataByName("Project", objectName);
		// get values
		int id = (Integer) hm.get("project_id");
		String name = (String) hm.get("project_name");
		String description = (String) hm.get("description");
		// create object
		Project project = new Project(name, description, id);
		return project;
	}
	//---------------- CUSTOM METHODS -------------------------------------------------------------------------------------

}
