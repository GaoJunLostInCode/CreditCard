package com.jun.gao.creditcard.util;

import android.R.integer;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.jun.gao.creditcard.model.CreditCardRemindRecord;

public class SharedPreferanceUtil
{
	private static final String NAME_SHARED_PREFERENCES = "CreditCard";
	private static final String KEY_SHARED_TAIL_DAY = "_DAY";
	private static final String KEY_SHARED_TAIL_MONTH = "_MONTH";
	private static final String KEY_SHARED_TAIL_YEAR = "_YEAR";

	// private static

	public static void writeLastRecord(Context context,
			CreditCardRemindRecord record)
	{
		SharedPreferences sp = context.getSharedPreferences(
				NAME_SHARED_PREFERENCES, Context.MODE_PRIVATE);
		Editor editor = sp.edit();

		editor.putInt(getKey(record.mCardId, KEY_SHARED_TAIL_DAY), record.mDay);
		editor.putInt(getKey(record.mCardId, KEY_SHARED_TAIL_MONTH),
				record.mMonth);
		editor.putInt(getKey(record.mCardId, KEY_SHARED_TAIL_YEAR),
				record.mYear);
		editor.commit();
	}

	public static CreditCardRemindRecord readLastRecord(Context context,
			int cardId)
	{
		CreditCardRemindRecord record = new CreditCardRemindRecord();
		SharedPreferences sp = context.getSharedPreferences(
				NAME_SHARED_PREFERENCES, Context.MODE_PRIVATE);
		record.mDay = sp.getInt(getKey(cardId, KEY_SHARED_TAIL_DAY), 0);
		record.mMonth = sp.getInt(getKey(cardId, KEY_SHARED_TAIL_MONTH), 0);
		record.mYear = sp.getInt(getKey(cardId, KEY_SHARED_TAIL_YEAR), 0);

		return record;
	}

	private static String getKey(int cardID, String keyTail)
	{
		return StringUtil.plus(String.valueOf(cardID), keyTail);
	}
}
