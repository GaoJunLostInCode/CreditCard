package com.jun.gao.creditcard.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.jun.gao.creditcard.model.CreditCard.CreditCardColumn;

public class CreditCardSQLiteHelper extends SQLiteOpenHelper
{
	private static final int DATABASE_VERSION = 1;
	public static final String DICTIONARY_TABLE_NAME = "creditcard";
	private static final String DICTIONARY_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS "
			+ DICTIONARY_TABLE_NAME + " (" + CreditCardColumn._ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ CreditCardColumn.CARD_BANK + " VARCHAR(16), "
			+ CreditCardColumn.CARD_LAST4NUM + " VARCHAR(4), "
			+ CreditCardColumn.DATE_BILL + " DATE, "
			+ CreditCardColumn.IDENTITY_NUM + " VARCHAR(24), "
			+ CreditCardColumn.PHONE_NUM + " VARCHAR(16), "
			+ CreditCardColumn.DATE_PAYMENT + " DATE, "
			+ CreditCardColumn.DATE_LASTPAIED + " DATE, "
			+ CreditCardColumn.CARD_NUM + " VARCHAR(24));";

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
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		// TODO Auto-generated method stub

	}

}
