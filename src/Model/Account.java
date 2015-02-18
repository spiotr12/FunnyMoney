/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import FunnyMoneyDatabase.FunnyDB;
import FunnyMoneyDatabase.UtilitiesInterface;
import static FunnyMoneyDatabase.FunnyDB.con;
import static FunnyMoneyDatabase.StaticUtilities.removeLastComa;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Piotrek
 */
public class Account implements UtilitiesInterface<Account> {

	private int id;
	private Currency currency;
	private String name, type;
	private double startAmount, balance;

	public static String BANK = "BANK";
	public static String CASH = "CASH";
	public static String CARD = "CARD";

	/**
	 * Full field constructor. Use for database queries results.
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
		this.name = name;
		this.type = type;
		this.currency = currency;
		this.startAmount = startAmount;
		this.balance = balance;
	}

	/**
	 * Constructor without ID and balance. Use for new objects and updates. Use addToDatabase() method to add to database and get register ID. Balance is set using countBalance()
	 * method; For type use one of the static fields: BANK - for bank type, CASH - for cash type, CARD - for card type.
	 *
	 * @param name
	 * @param type
	 * @param currency
	 * @param startAmount
	 */
	public Account(String name, String type, Currency currency, double startAmount) {
		this.name = name;
		this.type = type;
		this.currency = currency;
		this.startAmount = startAmount;
		this.balance = countBalance();
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

	public void setBalance(double balance) {
		this.balance = balance;
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
					+ "VALUES ('" + this.name + "', '" + this.type + "', " + this.currency.getId() + ", " + this.startAmount + ", " + this.balance + ")";
			stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			// get index of this operation
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);
			}
			// close connections
			stmt.close();
			rs.close();
		} catch (SQLException ex) {
			Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			//TODO: Add payee with accout name
		}
	}

	@Override
	public void updateToDatabase(Account newAccount) {
		boolean change = false;
		boolean changeName = false;
		try {
			Statement stmt = con.createStatement();
			String sql = "UPDATE Account \n" + "SET ";
			// set name
			if (!this.name.equals(newAccount.getName())) {
				sql += "account_name = '" + newAccount.getName() + "', ";
				this.name = newAccount.getName();
				change = true;
				changeName = true;
			}
			// set type
			if (!this.type.equals(newAccount.getType())) {
				sql += "account_type = '" + newAccount.getType() + "', ";
				this.type = newAccount.getType();
				change = true;
			}
			// set currency
			int currencyId = newAccount.getCurrency().getId();
			if (this.currency.getId() != currencyId) {
				sql += "currency_id = " + currencyId + ", ";
				this.currency = Currency.getCurrencyFromDatabaseById(currencyId);
				change = true;
			}
			// set startAmount and set balance
			if (this.startAmount != newAccount.getStartAmount()) {
				sql += "start_amount = " + newAccount.getStartAmount() + ", ";
				this.startAmount = newAccount.getStartAmount();
				this.balance = this.countBalance();
				change = true;
			}

			// finish and execute querry
			if (change) {
				sql = removeLastComa(sql);	// removes coma 
				sql += " \n" + "WHERE account_id = " + this.id;	// finish sql query
				stmt.executeUpdate(sql);	// update row
				//System.out.println(sql);
			} else {
				System.out.println("Objects are the same, no new changes");
			}
			// close connections
			stmt.close();
		} catch (SQLException ex) {
			Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			if (changeName) {
				//TODO: Updates payee with account name
			}
		}
	}

	@Override
	public void removeFreomDatabase() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	//---------------- STATIC METHODS -------------------------------------------------------------------------------------
	/**
	 * Returns object found by given ID.
	 *
	 * @param objectId Object ID.
	 * @return Object from database.
	 */
	public static Account getAccountFromDatabaseById(int objectId) {
		Map hm = FunnyDB.getObjectDataById("Account", objectId);
		// get values
		int id = (Integer) hm.get("account_id");
		String name = (String) hm.get("account_name");
		String type = (String) hm.get("account_type");
		Currency currency = Currency.getCurrencyFromDatabaseById((Integer) hm.get("currency_id"));
		double startAmount = (Double) hm.get("start_amount");
		double balance = (Double) hm.get("balance");
		// create object
		Account account = new Account(id, name, type, currency, startAmount, balance);
		return account;
	}

	/**
	 * Returns object found by given name.
	 *
	 * @param objectName
	 * @return Object from database.
	 */
	public static Account getAccountFromDatabaseByName(String objectName) {
		Map hm = FunnyDB.getObjectDataByName("Account", objectName);
		// get values
		int id = (Integer) hm.get("account_id");
		String name = (String) hm.get("account_name");
		String type = (String) hm.get("account_type");
		Currency currency = Currency.getCurrencyFromDatabaseById((Integer) hm.get("currency_id"));
		double startAmount = (Double) hm.get("start_amount");
		double balance = (Double) hm.get("balance");
		// create object
		Account account = new Account(id, name, type, currency, startAmount, balance);
		return account;
	}

	//---------------- CUSTOM METHODS -------------------------------------------------------------------------------------
	/**
	 * Counts the balance for this account and updates it to database. Starts form startAmount and adds totalAmount of each operation multiplied by type. Returns final balance.
	 *
	 * @return This account current balance.
	 */
	public double countBalance() {
		//TEST: Test countBalance()
		double totalBalance = this.startAmount;
		try {
			Statement stmt = con.createStatement();
			String sql = "SELECT total_amount, operation_type \n"
					+ "FROM Operation \n"
					+ "WHERE account_id = " + this.id + " \n";
			// sum up all expenses for this account
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				totalBalance += (rs.getInt("operation_type") * rs.getDouble("total_amount"));
			}
			// close connections
			rs.close();
			stmt.close();
		} catch (SQLException ex) {
			Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
			System.out.println(ex.getMessage());
		} finally {
			// updates balance to database
			try {
				Statement stmt = con.createStatement();
				String sql = "UPDATE Account \n"
						+ "SET balance = " + totalBalance + " \n"
						+ "WHERE account_id = " + this.id;
				stmt.executeUpdate(sql);
				// close connections
				stmt.close();
			} catch (SQLException ex) {
				Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return totalBalance;
	}

}
