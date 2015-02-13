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
public class Account implements UtilitiesInterface {

	private int id;
	private Currency currency;
	private String name, type;
	private double startAmount, balance;

	/**
	 * Full field constructor. Use for DB queries.
	 *
	 * @param id
	 * @param name
	 * @param type
	 * @param currency
	 * @param startAmount
	 * @param balance
	 */
	public Account(int id, String name, String type, Currency currency, double startAmount, double balance) {
		this.id = id;
		this.currency = currency;
		this.name = name;
		this.type = type;
		this.startAmount = startAmount;
		this.balance = balance;
	}

	/**
	 * Constructor without ID. Use addToDatabase() method to add to database and get register ID.
	 *
	 * @param name
	 * @param type
	 * @param currency
	 * @param startAmount
	 * @param balance
	 */
	public Account(String name, String type, Currency currency, double startAmount, double balance) {
		this.currency = currency;
		this.name = name;
		this.type = type;
		this.startAmount = startAmount;
		this.balance = balance;
	}

	public int getId() {
		return id;
	}

	public Currency getCurrency() {
		return currency;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public double getStartAmount() {
		return startAmount;
	}

	public double getBalance() {
		return balance;
	}

	@Override
	public String toString() {
		return "Account \"" + name + "\" (id = " + id + ") is in " + currency.getName() + ", type is " + type + ", start amount was: " + currency.getValueWithCurrency(startAmount)
				+ ", while current balance = " + currency.getValueWithCurrency(balance) + ".";
	}

	//---------------- IMPLEMENTED METHODS -------------------------------------------------------------------------------------
	@Override
	public void addToDatabase() {
		try {
			Statement stmt = con.createStatement();
			String sql = "INSERT INTO Account (account_name, account_type, currency_id, start_amount, balance)\n"
					+ "VALUES ('" + this.name + "','" + this.type + "'," + this.currency.getId() + "," + this.startAmount + "," + this.balance + ")";
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
			Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void updateToDatabase(Object object) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void removeFreomDatabase() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	//---------------- STATIC METHODS -------------------------------------------------------------------------------------
	/**
	 * Returns object found by given name.
	 *
	 * @param valueName
	 * @return Object from database.
	 */
	public static Account getCurrencyByName(String valueName) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	//---------------- CUSTOM METHODS -------------------------------------------------------------------------------------
}
