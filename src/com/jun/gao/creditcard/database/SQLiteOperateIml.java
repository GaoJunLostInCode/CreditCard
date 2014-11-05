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
		ContentValues values = createValues(card);

		SQLiteDatabase sqLiteDatabase = mDb.getWritableDatabase();
		long creditCardId = sqLiteDatabase.insert(
				CreditCardSQLiteHelper.DICTIONARY_TABLE_NAME, null, values);
		System.out.println("addCreditCard id=====" + creditCardId);
		sqLiteDatabase.close();

		return creditCardId;
	}

	private ContentValues createValues(CreditCard card)
	{
		ContentValues values = new ContentValues();
		values.put(CreditCardColumn.CARD_NUM, card.getmCardNum());
		values.put(CreditCardColumn.CARD_BANK, card.getmBankName());
		values.put(CreditCardColumn.DATE_BILL, card.getStrBillDate());
		values.put(CreditCardColumn.DATE_LASTPAIED, card.getStrLastDate());
		values.put(CreditCardColumn.DATE_PAYMENT, card.getStrPaymentDate());
		values.put(CreditCardColumn.CARD_LAST4NUM, card.getmLast4Num());
		values.put(CreditCardColumn.STATUS_PAID, card.isPaied());

		return values;
	}

	@Override
	public int deleteCreditCard(CreditCard card)
	{
		SQLiteDatabase sqLiteDatabase = mDb.getReadableDatabase();
		int count = sqLiteDatabase.delete(
				CreditCardSQLiteHelper.DICTIONARY_TABLE_NAME,
				CreditCardColumn._ID + "=?", new String[]
				{ String.valueOf(card.getId()) });
		sqLiteDatabase.close();
		return count;
	}

	@Override
	public int updateCrditCard(CreditCard card)
	{
		SQLiteDatabase sqlDatabase = mDb.getReadableDatabase();

		int rows = sqlDatabase.updateWithOnConflict(
				CreditCardSQLiteHelper.DICTIONARY_TABLE_NAME,
				createValues(card), CreditCardColumn._ID + "=?", new String[]
				{ String.valueOf(card.getId()) }, 0);

		sqlDatabase.close();

		return rows;
	}

	@Override
	public List<CreditCard> listAllCreditCards()
	{
		List<CreditCard> listCards = null;
		SQLiteDatabase sqlDatabase = mDb.getReadableDatabase();

		Cursor cursor = sqlDatabase.query(
				CreditCardSQLiteHelper.DICTIONARY_TABLE_NAME, null, null, null,
				null, null, CreditCardColumn.DATE_BILL + " ASC");
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

					index = cursor.getColumnIndex(CreditCardColumn._ID);
					card.setId(cursor.getInt(index));

					index = cursor
							.getColumnIndex(CreditCardColumn.CARD_LAST4NUM);
					card.setmLast4Num(cursor.getString(index));

					index = cursor.getColumnIndex(CreditCardColumn.CARD_NUM);
					card.setmCardNum(cursor.getString(index));

					index = cursor.getColumnIndex(CreditCardColumn.DATE_BILL);
					int day = cursor.getInt(index);
					card.setBillDay(day);

					index = cursor
							.getColumnIndex(CreditCardColumn.DATE_LASTPAIED);
					Date date = format.parse(cursor.getString(index));
					card.setDateLastPaied(date);

					index = cursor
							.getColumnIndex(CreditCardColumn.DATE_PAYMENT);
					day = cursor.getInt(index);
					card.setPaymentDay(day);

					index = cursor
							.getColumnIndex(CreditCardColumn.IDENTITY_NUM);
					card.setmIdentityNum(cursor.getString(index));

					index = cursor.getColumnIndex(CreditCardColumn.PHONE_NUM);
					card.setmPhoneNum(cursor.getString(index));

					index = cursor.getColumnIndex(CreditCardColumn.STATUS_PAID);
					if (1 == cursor.getInt(index))
					{
						card.setIsPaied(true);
					}
					else
					{
						card.setIsPaied(false);
					}

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
