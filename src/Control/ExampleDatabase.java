/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import static Control.TestMain.FMLOGGER;
import static Control.TestMain.db;
import FunnyMoneyDatabase.DefaultTables;
import static FunnyMoneyDatabase.FunnyDB.doesValueExist;
import static FunnyMoneyDatabase.FunnyDB.doesTableExists;
import Model.Account;
import Model.Category;
import static Model.Category.EXPENSE;
import static Model.Category.INCOME;
import Model.Currency;
import Model.Payee;
import Model.Project;
import Model.Subcategory;
import java.util.logging.Level;

/**
 *
 * @author Piotrek
 */
public class ExampleDatabase {

	public static void createExampleDatabase() {
		// create tables
		if (!doesTableExists("Category")) {
			db.createTable(DefaultTables.CATEGORY_TABLE);
		}
		if (!doesTableExists("Subcategory")) {
			db.createTable(DefaultTables.SUBCATEGORY_TABLE);
		}
		if (!doesTableExists("Currency")) {
			db.createTable(DefaultTables.CURRENCY_TABLE);
		}
		if (!doesTableExists("Account")) {
			db.createTable(DefaultTables.ACCOUNT_TABLE);
		}
		if (!doesTableExists("Project")) {
			db.createTable(DefaultTables.PROJECT_TABLE);
		}
		if (!doesTableExists("Payee")) {
			db.createTable(DefaultTables.PAYEE_TABLE);
		}
		if (!doesTableExists("Operation")) {
			db.createTable(DefaultTables.OPERATION_TABLE);
		}

		// populate tables
		// - currencys
		Currency[] currencys = new Currency[4];
		currencys[0] = new Currency("PLN", "zł", Currency.AFTER_VALUE);
		currencys[1] = new Currency("GBP", "£", Currency.BEFORE_VALUE);
		currencys[2] = new Currency("USD", "$", Currency.BEFORE_VALUE);
		currencys[3] = new Currency("EUR", "€", Currency.BEFORE_VALUE);
		for (Currency c : currencys) {
			if (!doesValueExist(Currency.class.getSimpleName(), c.getName())) {
				c.addToDatabase();
			} else {
				FMLOGGER.log(Level.WARNING, "{0} \"{1}\" already exists/name already used", new Object[]{c.getClass().getSimpleName(), c.getName()});
			}
		}

		// - account
		Account[] accounts = new Account[4];
		accounts[0] = new Account("TSB bank", Account.BANK, currencys[1], 740.30);
		accounts[1] = new Account("Wallet", Account.CASH, currencys[3], 14.71);
		accounts[2] = new Account("Alior", Account.CARD, currencys[0], 1303.89);
		accounts[3] = new Account("WBK", Account.BANK, currencys[0], 0.01);
		for (Account a : accounts) {
			if (!doesValueExist(Account.class.getSimpleName(), a.getName())) {
				a.addToDatabase();
			} else {
				FMLOGGER.log(Level.WARNING, "{0} \"{1}\" already exists/name already used", new Object[]{a.getClass().getSimpleName(), a.getName()});
			}
		}

		// - category
		Category[] categories = new Category[3];
		categories[0] = new Category("Jedzenie", EXPENSE);
		categories[1] = new Category("Wynagrodzenie", INCOME);
		categories[2] = new Category("Kosmetyki", EXPENSE);
		for (Category c : categories) {
			if (!doesValueExist(Category.class.getSimpleName(), c.getName())) {
				c.addToDatabase();
			} else {
				FMLOGGER.log(Level.WARNING, "{0} \"{1}\" already exists/name already used", new Object[]{c.getClass().getSimpleName(), c.getName()});
			}
		}

		// - subcategory
		Subcategory[] subcategories = new Subcategory[5];
		subcategories[0] = new Subcategory("Artykuly spozywcze", 1);
		subcategories[1] = new Subcategory("Slodycze", 1);
		subcategories[2] = new Subcategory("Sainsbury", 2);
		subcategories[3] = new Subcategory("Szampon", 3);
		subcategories[4] = new Subcategory("Perfumy", 3);
		for (Subcategory s : subcategories) {
			if (!doesValueExist(Category.class.getSimpleName(), s.getName())) {
				s.addToDatabase();
			} else {
				FMLOGGER.log(Level.WARNING, "{0} \"{1}\" already exists/name already used", new Object[]{s.getClass().getSimpleName(), s.getName()});
			}
		}

		// - payee
		Payee[] payees = new Payee[4];
		payees[0] = new Payee("Asda", "Grocery shop", true);
		payees[1] = new Payee("Lidl", null, true);
		payees[2] = new Payee("PC World", "Computer shop", true);
		payees[3] = new Payee("Oriental Takeaway", "Takeaway", true);
		for (Payee p : payees) {
			if (!doesValueExist(Payee.class.getSimpleName(), p.getName())) {
				p.addToDatabase();
			} else {
				FMLOGGER.log(Level.WARNING, "{0} \"{1}\" already exists/name already used", new Object[]{p.getClass().getSimpleName(), p.getName()});
			}
		}

		// - project
		Project[] projects = new Project[3];
		projects[0] = new Project("Edynburg 11.2014", "Wycieczka do Edynburga z Mirą");
		projects[1] = new Project("Rachunki mieszkaniowe", null);
		projects[2] = new Project("Loty do Polski", null);
		for (Project p : projects) {
			if (!doesValueExist(Payee.class.getSimpleName(), p.getName())) {
				p.addToDatabase();
			} else {
				FMLOGGER.log(Level.WARNING, "{0} \"{1}\" already exists/name already used", new Object[]{p.getClass().getSimpleName(), p.getName()});
			}
		}

		// - operation
	}
}
