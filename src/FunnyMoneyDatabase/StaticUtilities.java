/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunnyMoneyDatabase;

/**
 *
 * @author Piotrek
 */
public class StaticUtilities {

	/**
	 * Removes coma from sql query. Should be used for generated code for updateToDatabase() methods implemented with UtilitiesInterface.
	 *
	 * @param sql Partly generated sql query from updateToDatabase()
	 * @return String with no coma at the end.
	 */
	public static String removeLastComa(String sql) {
		boolean notDone = true;
		while (notDone) {
			if (sql.endsWith(",")) {
				sql = sql.substring(0, sql.length() - 1);
				notDone = false;
			} else {
				sql = sql.substring(0, sql.length() - 1);
			}
		}
		return sql;
	}
}
