/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunnyMoneyDatabase;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Piotrek
 */
public class FunnyDB {

	private String driver = "org.apache.derby.jdbc.EmbeddedDriver";
	private String url;
	private String user;
	private String password;
	public static Connection con;

	/**
	 * Creator for DataBaseControl and creates Connection. It sets name (or path) to database.
	 *
	 * @param dbName Name or path to database
	 * @param user User name for connection
	 * @param password User's password
	 */
	public FunnyDB(String dbName, String user, String password) {
		this.url = "jdbc:derby:" + dbName + ";create=true";
		this.user = user;
		this.password = password;

		createConnection();
	}

	/**
	 * Creates connection with database. Default user and password id "admin".
	 */
	private void createConnection() {
		try {
			Class.forName(driver);
			this.con = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(FunnyDB.class.getName()).log(Level.SEVERE, null, ex);
			System.out.println(ex.getMessage());
		} catch (SQLException ex) {
			Logger.getLogger(FunnyDB.class.getName()).log(Level.SEVERE, null, ex);
			System.out.println(ex.getMessage());
		}
	}

	/**
	 * Create table in the database. First create connection using createConnection().
	 *
	 * @param sql SQL String command. Use pre-define Strings.
	 *
	 * @see DataBaseControl#createConnection()
	 */
	public void createTable(String sql) {
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException ex) {
			Logger.getLogger(FunnyDB.class.getName()).log(Level.SEVERE, null, ex);
			System.out.println(ex.getMessage());
		}
	}
	
	/**
	 * Checks if the table already exists, or table does not exists..
	 *
	 * @param tableName Table name to check for existence.
	 * @return
	 */
	private static boolean doesTableExists(String tableName) {
		ArrayList<String> tables = new ArrayList<>();
		boolean exists = false;
		try {
			// get all tables names
			DatabaseMetaData meta = con.getMetaData();
			ResultSet rs = meta.getTables(null, null, null, new String[]{"TABLE"});
			while (rs.next()) {
				tables.add(rs.getString(3));
			}
			rs.close();
		} catch (SQLException ex) {
			Logger.getLogger(FunnyDB.class.getName()).log(Level.SEVERE, null, ex);
			System.out.println(ex.getMessage());
		} finally {
			// check
			for (String str : tables) {
				if (str.equals(tableName.toUpperCase())) {
					exists = true;
				}
			}
		}
		return exists;
	}

	/**
	 * Checks if the value already exists, or table does not exists. in given table. If yes, returns 1; if no, returns 0. If table does not exist, returns -1. This method is created to ensures that
	 * table auto increment does not
	 *
	 * @param tableName Name of the table to search through.
	 * @param objectName Name of the object to find.
	 * @return If value found, returns 1; if no, returns 0. If table does not exist, returns -1.
	 */
	public static int doesValueExist(String tableName, String objectName) {
		if (doesTableExists(tableName)) {
			boolean exists = false;
			String columnName = tableName.toLowerCase() + "_name";
			try {
				Statement stmt = con.createStatement();
				String sql = "SELECT " + columnName + "\n"
						+ "FROM " + tableName + "\n"
						+ "WHERE " + columnName + " = '" + objectName + "'";
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					if (rs.getString(columnName).equals(objectName)) {
						exists = true;
					}
				}
				rs.close();
			} catch (SQLException ex) {
				Logger.getLogger(FunnyDB.class.getName()).log(Level.SEVERE, null, ex);
				System.out.println(ex.getMessage());
			} finally {
				if (exists) {
					return 1;
				} else {
					return 0;
				}
			}
		} else {
			System.out.println("Table " + tableName + " does not exists");
			return -1;
		}
	}
}
