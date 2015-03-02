/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import FunnyMoneyDatabase.FunnyDB;
import Model.Currency;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author Piotrek
 */
public class TestMain {

	public static final Logger FMLOGGER = Logger.getLogger(TestMain.class.getName());
	public static final FunnyDB db = new FunnyDB("test_db", "APP", "root");

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		// QUESTIONS AND TASKS
		// TODO: Improve logger
		// TODO: Add comparator/comparable interface

		// add and sets file log
		Handler handler;
		try {
			handler = new FileHandler("funnyMoneyLog.log", true);
			FMLOGGER.addHandler(handler);
			handler.setFormatter(new SimpleFormatter());
		} catch (IOException ex) {
			Logger.getLogger(TestMain.class.getName()).log(Level.SEVERE, null, ex);
		} catch (SecurityException ex) {
			Logger.getLogger(TestMain.class.getName()).log(Level.SEVERE, null, ex);
		}

		// creates database
		ExampleDatabase.createExampleDatabase();

		Currency gbp = Currency.getCurrencyFromDatabaseByName("gbp");
		System.out.println(gbp.toString());

//		Currency pln1 = new Currency("MET", "zł", Currency.AFTER_VALUE);
//		System.out.println(pln1.toString());
//		pln1.addToDatabase();
//		System.out.println(pln1.toString());
//		Currency pln = Currency.getCurrencyFromDatabaseByName("met");
//		System.out.println(pln.toString());
//		
//		Account add = new Account("Alior", Account.BANK, pln, 20.34);
//		add.addToDatabase();
//		Currency gbp = new Currency("GBP", "zł", Currency.BEFORE_VALUE);
//		System.out.println(gbp.toString());
//		gbp.addToDatabase();
//		System.out.println(gbp.toString());
//		
//		Currency asd = Currency.getCurrencyFromDatabaseById(1);
//		System.out.println(asd.toString());
//
//		Account bank = Account.getAccountFromDatabaseById(3);
//		System.out.println(bank.toString());
//		bank.updateToDatabase(new Account("You bank", Account.CASH, pln, 0.30));
//		System.out.println(bank.toString());
	}

}
