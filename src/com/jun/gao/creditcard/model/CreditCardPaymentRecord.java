package com.jun.gao.creditcard.model;

import java.util.Date;

import android.provider.BaseColumns;

/**
 * 还款记录
 * 
 * @author gaojun
 * 
 */
public class CreditCardPaymentRecord
{
	private int mCardID = 0; // 信用卡ID
	private Date mDatePaid = null; // 还款日期
	private int mMonthPayment = 0; // 应还款月份
	private int mYearPayment = 0; // 应还款年份

	public static class PaymentRecordColumn implements BaseColumns
	{
		public static final String CARD_ID = "card_id"; // 信用卡ID
		public static final String DATE_PAID = "date_paid"; // 还款时间
		public static final String MONTH_PAYMENT = "month_payment"; // 应还款月份
		public static final String YEAR_PAYMENT = "year_payment"; // 应还款月份
	}
}
