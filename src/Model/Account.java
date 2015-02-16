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
			rs.close();
			stmt.close();
		} catch (SQLException ex) {
			Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			//TODO: Add payee with accout name
		}
	}

	@Override
	public void updateToDatabase(Account account) {
		//TEST: generating SQL string and this method.
		boolean change = false;
		boolean changeName = false;
		try {
			Statement stmt = con.createStatement();
			String sql = "UPDATE Account \n" + "SET ";
			// set name
			if (!this.name.equals(account.getName())) {
				sql += "account_name = '" + account.getName() + "', ";
				this.name = account.getName();
				change = true;
				changeName = true;
			}
			// set type
			if (!this.type.equals(account.getType())) {
				sql += "account_type = '" + account.getType() + "', ";
				this.type = account.getType();
				change = true;
			}
			// set currency
			int currencyId = account.getCurrency().getId();
			if (this.currency.getId() != currencyId) {
				sql += "currency_id = " + currencyId + ", ";
				this.currency = Currency.getCurrencyFromDatabaseById(currencyId);
				change = true;
			}
			// set startAmount and set balance
			if (this.startAmount != account.getStartAmount()) {
				sql += "start_amount = " + account.getStartAmount() + ", ";
				this.startAmount = account.getStartAmount();
				this.balance = this.countBalance();
				change = true;
			}

			// finish and execute querry
			if (change) {
				sql = removeLastComa(sql);	// removes coma 
				sql += " \n" + "WHERE account_id = " + this.id;	// finish sql query
				stmt.executeUpdate(sql);	// update row
				//System.out.println(sql);
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
	 * @param id Object ID.
	 * @return Object from database.
	 */
	public static Account getAccountFromDatabaseById(int id) {
		Account account = null;
		try {
			ResultSet rs = FunnyDB.getObjectResultSetById("Account", id);
			// create object to return
			while (rs.next()) {
				account = new Account(rs.getInt("account_id"), rs.getString("account_name"), rs.getString("account_type"), Currency.getCurrencyFromDatabaseById(rs.getInt("currency_id")), rs.getDouble("start_amount"), rs.getDouble("balance"));
			}
			rs.close();
		} catch (SQLException ex) {
			Logger.getLogger(Currency.class.getName()).log(Level.SEVERE, null, ex);
			System.out.println(ex.getMessage());
		}
		return account;
	}

	/**
	 * Returns object found by given name.
	 *
	 * @param name
	 * @return Object from database.
	 */
	public static Account getAccountFromDatabaseByName(String name) {
		Account account = null;
		try {
			ResultSet rs = FunnyDB.getObjectResultSetByName("Account", name);
			// create object to return
			while (rs.next()) {
				account = new Account(rs.getInt("account_id"), rs.getString("account_name"), rs.getString("account_type"), Currency.getCurrencyFromDatabaseById(rs.getInt("currency_id")), rs.getDouble("start_amount"), rs.getDouble("balance"));
			}
			rs.close();
		} catch (SQLException ex) {
			Logger.getLogger(Currency.class.getName()).log(Level.SEVERE, null, ex);
			System.out.println(ex.getMessage());
		}
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
