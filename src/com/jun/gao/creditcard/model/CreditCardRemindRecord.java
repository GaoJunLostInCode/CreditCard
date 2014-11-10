package com.jun.gao.creditcard.model;

import com.jun.gao.creditcard.view.MyScrollView;

/**
 * 信用卡提醒记录
 * 
 * @author gaojun
 * 
 */
public class CreditCardRemindRecord
{
	public int mCardId; // 信用卡ID
	public int mYear; // 年
	public int mMonth; // 月
	public int mDay; // 日

	public boolean isReminded(int year, int month)
	{
		if (year == mYear && month == mMonth)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static CreditCardRemindRecord createWithYearAndMonth(int year, int month)
	{
		CreditCardRemindRecord record = new CreditCardRemindRecord();
		
		record.mYear = year;
		record.mMonth = month;
		return record;
	}
}
