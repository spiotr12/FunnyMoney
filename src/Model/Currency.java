/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import static Control.TestMain.FMLOGGER;
import FunnyMoneyDatabase.FunnyDB;
import FunnyMoneyDatabase.UtilitiesInterface;
import static FunnyMoneyDatabase.FunnyDB.con;
import static FunnyMoneyDatabase.StaticUtilities.removeLastComa;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Piotrek
 */
public class Currency implements UtilitiesInterface<Currency> {

	private int id, position;
	private String name, symbol;

	public static final int BEFORE_VALUE = -1;
	public static final int AFTER_VALUE = 1;

	/**
	 * Full field constructor. Use for DB queries.
	 *
	 * @param id
	 * @param name
	 * @param symbol
	 * @param position
	 */
	public Currency(String name, String symbol, int position, int id) {
		this.id = id;
		this.position = position;
		this.name = name;
		this.symbol = symbol;
//		FMLOGGER.log(Level.INFO, "{0} full constructor used", Currency.class.getName());
	}

	/**
	 * Constructor without ID. Use addToDatabase() method to add to database and get register ID.
	 *
	 * @param name
	 * @param position
	 * @param symbol
	 */
	public Currency(String name, String symbol, int position) {
		this.id = id;
		this.position = position;
		this.name = name;
		this.symbol = symbol;
//		FMLOGGER.log(Level.INFO, "{0} standard constructor used", Currency.class.getName());
	}

	public int getId() {
		return id;
	}

	public int getPosition() {
		return position;
	}

	public String getName() {
		return name;
	}

	public String getSymbol() {
		return symbol;
	}

	@Override
	public String toString() {
		return "Currency \"" + name + "\" (id = " + id + ") has symbol \'" + symbol + "\' and position is " + position;
	}

	//---------------- IMPLEMENTED METHODS -------------------------------------------------------------------------------------
	@Override
	public void addToDatabase() {
		try {
			Statement stmt = con.createStatement();
			String sql = "INSERT INTO Currency (currency_name, symbol, position)\n"
					+ "VALUES ('" + this.name.toUpperCase() + "', '" + this.symbol + "', " + this.position + ")";
			stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			// get index of this operation
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);
			}
			// close connections
			rs.close();
			stmt.close();
			FMLOGGER.log(Level.INFO, "{0} was added to database", this.toString());
		} catch (SQLException ex) {
			Logger.getLogger(Currency.class.getName()).log(Level.SEVERE, null, ex);

		}
	}

	@Override
	public void updateToDatabase(Currency updatedObject) {
		//TEST ME: Update Currency
		boolean change = false;
		try {
			Statement stmt = con.createStatement();
			String sql = "UPDATE Currency \n" + "SET ";
			// do test if there is a value, test if new value is different then old one
			// set name
			if (updatedObject.getName() != null && !this.name.equals(updatedObject.getName())) {
				sql += "currency_name = '" + updatedObject.getName() + "', ";
				this.name = updatedObject.getName();
				change = true;
			}
			// set symbol
			if (updatedObject.getSymbol() != null && !this.symbol.equals(updatedObject.getSymbol())) {
				sql += "symbol = '" + updatedObject.getSymbol() + "', ";
				this.symbol = updatedObject.getSymbol();
				change = true;
			}
			// set position
			if (this.position != updatedObject.getPosition()) {
				sql += "position = " + updatedObject.getPosition() + ", ";
				this.position = updatedObject.getPosition();
				change = true;
			}

			// finish and execute querry
			if (change) {
				sql = removeLastComa(sql);	// removes coma 
				sql += " \n" + "WHERE currency_id = " + this.id;	// finish sql query
				stmt.executeUpdate(sql);	// update row
				FMLOGGER.log(Level.INFO, "{0} with id = {1} was updatet to: {2}", new Object[]{this.getClass().getSimpleName(), this.id, updatedObject.toString()});
			} else {
				System.out.println("Objects are the same, no new changes");
			}
			// close connections
			stmt.close();
		} catch (SQLException ex) {
			Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void removeFromDatabase() {
		if (this.id > 0) {
			try {
				Statement stmt = con.createStatement();
				String sql = "DELETE FROM Currencu \n"
						+ "WHERE currencu_id = " + this.id;
				stmt.executeUpdate(sql);
				stmt.close();
				// sets id to 0, what is an equivalent to delete object from database, because it does not have an ID anymore. 
				this.id = 0;
			} catch (SQLException ex) {
				Logger.getLogger(Currency.class.getName()).log(Level.SEVERE, null, ex);
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
	public static Currency getCurrencyFromDatabaseById(int objectId) {
		Map hm = FunnyDB.getObjectDataById("Currency", objectId);
		// get values
		int id = (Integer) hm.get("currency_id");
		String name = (String) hm.get("currency_name");
		String type = (String) hm.get("symbol");
		int position = (Integer) hm.get("position");

		// create object
		Currency currency = new Currency(name, type, position, id);
		return currency;
	}

	/**
	 * Returns object found by given name.
	 *
	 * @param objectName
	 * @return Object from database.
	 */
	public static Currency getCurrencyFromDatabaseByName(String objectName) {
		Map hm = FunnyDB.getObjectDataByName("Currency", objectName.toUpperCase());
		// get values
		int id = (Integer) hm.get("currency_id");
		String name = (String) hm.get("currency_name");
		String type = (String) hm.get("symbol");
		int position = (Integer) hm.get("position");

		// create object
		Currency currency = new Currency(name, type, position, id);
		return currency;
	}

	//---------------- CUSTOM METHODS -------------------------------------------------------------------------------------
	/**
	 * Returns String with money value and currency symbol at correct position (symbol appears either before or after value).
	 *
	 * @param value
	 * @return
	 */
	public String getValueWithCurrency(double value) {
		String str = "";
		if (position > 0) {
			str += value + symbol;
		} else {
			str += symbol + value;
		}
		return str;
	}
}
