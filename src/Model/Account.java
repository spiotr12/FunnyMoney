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
	public Account(String name, String type, Currency currency, double startAmount, double balance, int id) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.currency = currency;
		this.startAmount = startAmount;
		this.balance = balance;
	}

	/**
	 * Constructor without ID and balance. Used for new objects and updates. Use addToDatabase() method to add to database and get register ID. Balance is set using countBalance()
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
				+ ", while current balance = " + currency.getValueWithCurrency(balance);
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
			FMLOGGER.log(Level.INFO, "{0} was added to database", this.toString());
			// Add payee with accout name
			Payee accountPayee = new Payee(this.name, "Payee refer to one of your account", false);
			accountPayee.addToDatabase();
		} catch (SQLException ex) {
			Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void updateToDatabase(Account updatedObject) {
		boolean change = false;
		boolean changeName = false;
		String oldName = this.name;
		try {
			Statement stmt = con.createStatement();
			String sql = "UPDATE Account \n" + "SET ";
			// probram tests if there is a value, tests if new value is different then old one
			// set name
			if (updatedObject.getName() != null && !this.name.equals(updatedObject.getName())) {
				sql += "account_name = '" + updatedObject.getName() + "', ";
				this.name = updatedObject.getName();
				change = true;
				changeName = true;
			}
			// set type
			if (updatedObject.getType() != null && !this.type.equals(updatedObject.getType())) {
				sql += "account_type = '" + updatedObject.getType() + "', ";
				this.type = updatedObject.getType();
				change = true;
			}
			// set currency
			int currencyId = updatedObject.getCurrency().getId();
			if (currencyId != 0 && this.currency.getId() != currencyId) {
				sql += "currency_id = " + currencyId + ", ";
				this.currency = Currency.getCurrencyFromDatabaseById(currencyId);
				change = true;
			}
			// set startAmount and set balance
			if (this.startAmount != updatedObject.getStartAmount()) {
				sql += "start_amount = " + updatedObject.getStartAmount() + ", ";
				this.startAmount = updatedObject.getStartAmount();
				this.balance = this.countBalance();
				change = true;
			}

			// finish and execute querry
			if (change) {
				sql = removeLastComa(sql);	// removes coma 
				sql += " \n" + "WHERE account_id = " + this.id;	// finish sql query
				stmt.executeUpdate(sql);	// update row
				FMLOGGER.log(Level.INFO, "{0} with id = {1} was updatet to: {2}", new Object[]{this.getClass().getSimpleName(), this.id, updatedObject.toString()});
			} else {
				System.out.println("Objects are the same, no new changes");
			}
			// close connections
			stmt.close();
			// Updates payee with account name!!!
			if(updatedObject.getName() != null && !oldName.equals(updatedObject.getName())){
				Payee accountPayee = Payee.getPayeeFromDatabaseByName(oldName);
				Payee newAccountPayee = new Payee(updatedObject.getName(), null, false);
				accountPayee.updateToDatabase(newAccountPayee);
			}
			
		} catch (SQLException ex) {
			Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void removeFromDatabase() {
		if (this.id > 0) {
			try {
				Statement stmt = con.createStatement();
				String sql = "DELETE FROM Account \n"
						+ "WHERE account_id = " + this.id;
				stmt.executeUpdate(sql);
				stmt.close();
				// sets id to 0, what is an equivalent to delete object from database, because it does not have an ID anymore. 
				this.id = 0;
				// Remove payee with account name
				Payee accountPayee = Payee.getPayeeFromDatabaseByName(this.name);
				accountPayee.removeFromDatabase();
			} catch (SQLException ex) {
				Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
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
		Account account = new Account(name, type, currency, startAmount, balance, id);
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
		Account account = new Account(name, type, currency, startAmount, balance, id);
		return account;
	}

	//---------------- CUSTOM METHODS -------------------------------------------------------------------------------------
	/**
	 * Counts the balance for this account and updates it to database. Starts form startAmount and adds totalAmount of each operation multiplied by type. Returns final balance.
	 *
	 * @return This account current balance.
	 */
	public double countBalance() {	//TEST ME: Test countBalance()
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
