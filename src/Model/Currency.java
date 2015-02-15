/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import FunnyMoneyDatabase.UtilitiesInterface;
import static FunnyMoneyDatabase.FunnyDB.con;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
	public Currency(int id, String name, String symbol, int position) {
		this.id = id;
		this.position = position;
		this.name = name;
		this.symbol = symbol;
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
		return "Currency \"" + name + "\" (id = " + id + ") has symbol \'" + symbol + "\' and position is " + position + ".";
	}

	//---------------- IMPLEMENTED METHODS -------------------------------------------------------------------------------------
	@Override
	public void addToDatabase() {
		try {
			Statement stmt = con.createStatement();
			String sql = "INSERT INTO Currency (currency_name, symbol, position)\n"
					+ "VALUES ('" + this.name + "', '" + this.symbol + "', " + this.position + ")";
			stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			// get index of this operation
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);
			}
			// close connections
			rs.close();
			stmt.close();
		} catch (SQLException ex) {
			Logger.getLogger(Currency.class.getName()).log(Level.SEVERE, null, ex);
			System.out.println(ex.getMessage());
		}
	}

	@Override
	public void updateToDatabase(Currency currency) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void removeFreomDatabase() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	//---------------- STATIC METHODS -------------------------------------------------------------------------------------
	/**
	 * Returns object found by given ID.
	 *
	 * @param id Object ID.
	 * @return Object from database.
	 */
	public static Currency getCurrencyFromDatabaseById(int id) {
		Currency currency = null;
		try {
			// gets object's details from database
			Statement stmt = con.createStatement();
			String sql = "SELECT * \n"
					+ "FROM Currency \n"
					+ "WHERE currency_id = " + id;
			ResultSet rs = stmt.executeQuery(sql);
			// create object to return
			while (rs.next()) {
				currency = new Currency(rs.getInt("currency_id"), rs.getString("currency_name"), rs.getString("symbol"), rs.getInt("position"));
			}
			stmt.close();
		} catch (SQLException ex) {
			Logger.getLogger(Currency.class.getName()).log(Level.SEVERE, null, ex);
			System.out.println(ex.getMessage());
		}
		return currency;
	}

	/**
	 * Returns object found by given name.
	 *
	 * @param name
	 * @return Object from database.
	 */
	public static Currency getCurrencyFromDatabaseByName(String name) {
		Currency currency = null;
		try {
			// gets object's details from database
			Statement stmt = con.createStatement();
			String sql = "SELECT * \n"
					+ "FROM Currency \n"
					+ "WHERE currency_name = '" + name.toUpperCase() + "'";
			ResultSet rs = stmt.executeQuery(sql);
			// create object to return
			while (rs.next()) {
				currency = new Currency(rs.getInt("currency_id"), rs.getString("currency_name"), rs.getString("symbol"), rs.getInt("position"));
			}
			stmt.close();
		} catch (SQLException ex) {
			Logger.getLogger(Currency.class.getName()).log(Level.SEVERE, null, ex);
			System.out.println(ex.getMessage());
		}
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

	//TODO: Add comparator/comparable interface
}
