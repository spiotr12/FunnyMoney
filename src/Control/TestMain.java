/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import FunnyMoneyDatabase.FunnyDB;
import FunnyMoneyDatabase.DefaultTables;
import FunnyMoneyDatabase.StaticUtilities;
import Model.Account;
import Model.Currency;

/**
 *
 * @author Piotrek
 */
public class TestMain {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		//creates database
		FunnyDB db = new FunnyDB("test_db", "APP", "root");
		if (false) {
			db.createTable(DefaultTables.CATEGORY_TABLE);
			db.createTable(DefaultTables.SUBCATEGORY_TABLE);
			db.createTable(DefaultTables.CURRENCY_TABLE);
			db.createTable(DefaultTables.ACCOUNT_TABLE);
			db.createTable(DefaultTables.PROJECT_TABLE);
			db.createTable(DefaultTables.PAYEE_TABLE);
			db.createTable(DefaultTables.OPERATION_TABLE);
		}
		
//		Currency pln = new Currency("MET", "zł", Currency.AFTER_VALUE);
//		System.out.println(pln.toString());
//		pln.addToDatabase();
//		System.out.println(pln.toString());
		
		Currency pln = Currency.getCurrencyFromDatabaseByName("met");
		System.out.println(pln.toString());
		
		Account bank = Account.getAccountFromDatabaseById(1);
		System.out.println(bank.toString());
		bank.updateToDatabase(new Account("Your bank", Account.CASH, pln, 0.30));
		System.out.println(bank.toString());
		
		String str = "asdadasd,asdasdas, df";		
		System.out.println(StaticUtilities.removeLastComa(str));
		System.out.println("---------------");
		

	}

}
