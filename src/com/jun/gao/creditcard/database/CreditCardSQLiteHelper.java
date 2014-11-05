package com.jun.gao.creditcard.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.jun.gao.creditcard.model.CreditCard.CreditCardColumn;
import com.jun.gao.creditcard.model.CreditCardPaymentRecord.PaymentRecordColumn;

public class CreditCardSQLiteHelper extends SQLiteOpenHelper
{
	private static final int DATABASE_VERSION = 1;
	public static final String DICTIONARY_TABLE_NAME = "creditcard";
	public static final String DICTIONARY_TABLE_NAME_PAYMENT_RECORD = "creditcard_payment_record"; // 还款记录表
	private static final String DICTIONARY_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS "
			+ DICTIONARY_TABLE_NAME
			+ " ("
			+ CreditCardColumn._ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ CreditCardColumn.CARD_BANK
			+ " VARCHAR(16), "
			+ CreditCardColumn.CARD_LAST4NUM
			+ " VARCHAR(4), "
			+ CreditCardColumn.DATE_BILL
			+ " INTEGER, "
			+ CreditCardColumn.IDENTITY_NUM
			+ " VARCHAR(24), "
			+ CreditCardColumn.PHONE_NUM
			+ " VARCHAR(16), "
			+ CreditCardColumn.DATE_PAYMENT
			+ " INTEGER, "
			+ CreditCardColumn.DATE_LASTPAIED
			+ " DATE, "
			+ CreditCardColumn.STATUS_PAID
			+ " INTEGER, "
			+ CreditCardColumn.CARD_NUM + " VARCHAR(24));";

	// KEY `dage_id` (`dage_id`),
	// CONSTRAINT `xiaodi_ibfk_1` FOREIGN KEY (`dage_id`) REFERENCES `dage`
	// (`id`) on delete cascade on update cascade

	private static final String DICTIONARY_TABLE_CREATE_PAYMENT = "CREATE TABLE IF NOT EXISTS "
			+ DICTIONARY_TABLE_NAME_PAYMENT_RECORD
			+ " ("
			+ PaymentRecordColumn._ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ PaymentRecordColumn.CARD_ID
			+ " INTEGER, "
			+ PaymentRecordColumn.YEAR_PAYMENT
			+ " INTEGER, "
			+ PaymentRecordColumn.MONTH_PAYMENT
			+ " INTEGER, "
			+ PaymentRecordColumn.DATE_PAID
			+ " DATE, FOREIGN KEY ("
			+ PaymentRecordColumn.CARD_ID
			+ ") REFERENCES "
			+ DICTIONARY_TABLE_NAME
			+ " ("
			+ CreditCardColumn._ID
			+ ") "
			+ "ON DELETE CASCADE ON UPDATE CASCADE);";

	public CreditCardSQLiteHelper(Context context, String name,
			CursorFactory factory, int version)
	{
		super(context, DICTIONARY_TABLE_NAME, factory, DATABASE_VERSION);
		// TODO Auto-generated constructor stub

	}

	@Override
	public void onCreate(SQLiteDatabase db)
	{
		// TODO Auto-generated method stub
		db.execSQL(DICTIONARY_TABLE_CREATE);
		db.execSQL(DICTIONARY_TABLE_CREATE_PAYMENT);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		// TODO Auto-generated method stub

	}

}
