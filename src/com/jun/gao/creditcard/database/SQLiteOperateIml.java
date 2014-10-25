package com.jun.gao.creditcard.database;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.jun.gao.creditcard.model.CreditCard;
import com.jun.gao.creditcard.model.CreditCard.CreditCardColumn;

public class SQLiteOperateIml implements SQLiteOperate
{
	private CreditCardSQLiteHelper mDb;

	public SQLiteOperateIml(Context context)
	{
		mDb = new CreditCardSQLiteHelper(context, null, null, 1);
	}

	@Override
	public long addCreditCard(CreditCard card)
	{
		ContentValues values = new ContentValues();
		values.put(CreditCardColumn.CARD_NUM, card.getmCardNum());
		values.put(CreditCardColumn.CARD_BANK, card.getmBankName());
		values.put(CreditCardColumn.DATE_BILL, card.getStrBillDate());
		values.put(CreditCardColumn.DATE_LASTPAIED, card.getStrLastDate());
		values.put(CreditCardColumn.DATE_PAYMENT, card.getStrPaymentDate());
		values.put(CreditCardColumn.CARD_LAST4NUM, card.getmLast4Num());

		SQLiteDatabase sqLiteDatabase = mDb.getWritableDatabase();
		long creditCardId = sqLiteDatabase.insert("creditcard", null, values);
		System.out.println("addCreditCard id=====" + creditCardId);
		sqLiteDatabase.close();

		return creditCardId;
	}

	@Override
	public int deleteCreditCard(CreditCard card)
	{
		SQLiteDatabase sqLiteDatabase = mDb.getReadableDatabase();
		int count = sqLiteDatabase.delete(CreditCardSQLiteHelper.DICTIONARY_TABLE_NAME,
				CreditCardColumn.CARD_NUM + "=?", new String[]
				{ card.getmCardNum() });
		sqLiteDatabase.close();
		return count;
	}

	@Override
	public int updateCrditCard(CreditCard card)
	{
		return 0;
	}

	@Override
	public List<CreditCard> listAllCreditCards()
	{
		List<CreditCard> listCards = null;
		SQLiteDatabase sqlDatabase = mDb.getReadableDatabase();

		Cursor cursor = sqlDatabase.query(
				CreditCardSQLiteHelper.DICTIONARY_TABLE_NAME, null, null, null,
				null, null, null);
		if (null != cursor)
		{
			listCards = new ArrayList<CreditCard>();
			SimpleDateFormat format = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			try
			{
				while (cursor.moveToNext())
				{
					CreditCard card = new CreditCard();

					int index = cursor
							.getColumnIndex(CreditCardColumn.CARD_BANK);
					card.setmBankName(cursor.getString(index));

					index = cursor
							.getColumnIndex(CreditCardColumn.CARD_LAST4NUM);
					card.setmLast4Num(cursor.getString(index));

					index = cursor.getColumnIndex(CreditCardColumn.CARD_NUM);
					card.setmCardNum(cursor.getString(index));

					index = cursor.getColumnIndex(CreditCardColumn.DATE_BILL);
					int day = cursor.getInt(index);
					card.setBillDate(day);

					index = cursor
							.getColumnIndex(CreditCardColumn.DATE_LASTPAIED);
					Date date = format.parse(cursor.getString(index));
					card.setDateLastPaied(date);

					index = cursor
							.getColumnIndex(CreditCardColumn.DATE_PAYMENT);
					day = cursor.getInt(index);
					card.setPaymentDate(day);

					index = cursor
							.getColumnIndex(CreditCardColumn.IDENTITY_NUM);
					card.setmIdentityNum(cursor.getString(index));

					index = cursor.getColumnIndex(CreditCardColumn.PHONE_NUM);
					card.setmPhoneNum(cursor.getString(index));

					listCards.add(card);
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			finally
			{
				cursor.close();
				cursor = null;
			}
		}

		sqlDatabase.close();

		return listCards;
	}

}
