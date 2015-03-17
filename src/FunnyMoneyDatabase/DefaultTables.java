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
public class DefaultTables {

	public static final String CATEGORY_TABLE = "CREATE TABLE Category ( \n"
			+ "category_id			INT			NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), \n"
			+ "category_name		VARCHAR(50)	NOT NULL, \n"
			+ "category_type		INT			NOT NULL, \n"
			+ "CONSTRAINT pk_Category PRIMARY KEY (category_id), \n"
			+ "CONSTRAINT uc_category_name UNIQUE (category_name), \n"
			+ "CONSTRAINT ck_category_type CHECK (category_type IN (-1, 1, 0))" //-1 - expences; 1 - income; 0 - transfer;
			+ ")";

	public static final String SUBCATEGORY_TABLE = "CREATE TABLE Subcategory ( \n"
			+ "subcategory_id	INT			NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), \n"
			+ "category_id		INT			NOT NULL, \n"
			+ "subcategory_name	VARCHAR(50)	NOT NULL, \n"
			+ "CONSTRAINT pk_Subcategory PRIMARY KEY (subcategory_id), \n"
			+ "CONSTRAINT uc_subcategory_name UNIQUE (subcategory_name), \n"
			+ "CONSTRAINT fk_Subcategory_Category FOREIGN KEY (category_id) REFERENCES Category (category_id)"
			+ ")";

	public static final String CURRENCY_TABLE = "CREATE TABLE Currency ( \n"
			+ "currency_id		INT			NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), \n"
			+ "currency_name	CHAR(3)		NOT NULL, \n"
			+ "symbol			VARCHAR(3)	NOT NULL, \n"
			+ "position			INT			NOT NULL, \n"
			+ "CONSTRAINT pk_Currency PRIMARY KEY (currency_id), \n"
			+ "CONSTRAINT uc_currency_name UNIQUE (currency_name),"
			+ "CONSTRAINT ck_position CHECK (position IN (-1, 1))"
			+ ")";

	public static final String ACCOUNT_TABLE = "CREATE TABLE Account ( \n"
			+ "account_id		INT			NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), \n"
			+ "account_name		VARCHAR(50)	NOT NULL, \n"
			+ "account_type		CHAR(4)		NOT NULL, \n"
			+ "currency_id		INT			NOT NULL, \n"
			+ "start_amount		DOUBLE		NOT NULL, \n"
			+ "balance			DOUBLE		NOT NULL, \n"
			+ "CONSTRAINT pk_Account PRIMARY KEY (account_id), \n"
			+ "CONSTRAINT uc_account_name UNIQUE (account_name), \n"
			+ "CONSTRAINT fk_Account_Currency FOREIGN KEY (currency_id) REFERENCES Currency (currency_id), \n"
			+ "CONSTRAINT ck_account_type CHECK (account_type IN ('BANK','CASH','CARD'))"
			+ ")";

	public static final String PROJECT_TABLE = "CREATE TABLE Project ( \n"
			+ "project_id	INT		 NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), \n"
			+ "project_name	VARCHAR(50) NOT NULL, \n"
			+ "description	LONG VARCHAR, \n"
			+ "CONSTRAINT pk_Project PRIMARY KEY (project_id), \n"
			+ "CONSTRAINT uc_project_name UNIQUE (project_name)"
			+ ")";

	public static final String PAYEE_TABLE = "CREATE TABLE Payee ( \n"
			+ "payee_id		INT			NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), \n"
			+ "payee_name	VARCHAR(50) NOT NULL, \n"
			+ "description	LONG VARCHAR, \n"
			+ "editable		BOOLEAN		NOT NULL, \n"
			+ "CONSTRAINT pk_Payee PRIMARY KEY (payee_id), \n"
			+ "CONSTRAINT uc_payee_name UNIQUE (payee_name)"
			+ ")";

	public static final String OPERATION_TABLE = "CREATE TABLE Operation ( \n"
			+ "operation_id				INT			NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), \n"
			+ "subcategory_id			INT			NOT NULL, \n"
			+ "operation_type			INT			NOT NULL, \n"
			+ "transfer					BOOLEAN, \n"
			+ "account_id				INT, \n"
			+ "payee_id					INT, \n"
			+ "operation_reference_id	INT, \n"
			+ "date						DATE		NOT NULL, \n"
			+ "amount					DOUBLE		NOT NULL, \n"
			+ "currency_id				INT			NOT NULL, \n"
			+ "exchange_rate			DOUBLE, \n"
			+ "fee						DOUBLE		NOT NULL, \n" //should be in amount currency
			+ "total_amount				DOUBLE		NOT NULL, \n" //
			+ "project_id				INT, \n"
			+ "note						VARCHAR(99), \n"
			+ "CONSTRAINT pk_Operation PRIMARY KEY (operation_id), \n"
			+ "CONSTRAINT fk_Operation_Subcategory FOREIGN KEY (subcategory_id) REFERENCES Subcategory (subcategory_id) ON DELETE CASCADE, \n"
			+ "CONSTRAINT fk_Operation_Payee FOREIGN KEY (payee_id) REFERENCES Payee (payee_id), \n"
			+ "CONSTRAINT fk_Operation_Account FOREIGN KEY (account_id) REFERENCES Account (account_id), \n"
			+ "CONSTRAINT fk_Operation_Operation FOREIGN KEY (operation_reference_id) REFERENCES Operation (operation_id) ON DELETE CASCADE, \n"
			+ "CONSTRAINT fk_Operation_Currency FOREIGN KEY (currency_id) REFERENCES Currency (currency_id), \n"
			+ "CONSTRAINT fk_Operation_Project FOREIGN KEY (project_id) REFERENCES Project (project_id) ON DELETE SET NULL, \n"
			+ "CONSTRAINT ck_Operation_type CHECK (operation_type IN (-1, 1))" //-1 - expences; 1 - income;
			+ ")";

	public static final String USED_IN_TABLE = "CREATE TABLE Used_in ( \n"
			+ "subcategory_id		INT		NOT NULL, \n"
			+ "payee_id		INT		NOT NULL, \n"
			+ "CONSTRAINT pk_Used_in PRIMARY KEY (subcategory_id, payee_id), \n"
			+ "CONSTRAINT fk_Used_in_Subcategory FOREIGN KEY (subcategory_id) REFERENCES Subcategory (subcategory_id) ON DELETE CASCADE, \n"
			+ "CONSTRAINT fk_Used_id_Payee FOREIGN KEY (payee_id) REFERENCES Payee (payee_id) ON DELETE CASCADE"
			+ ")";
}
