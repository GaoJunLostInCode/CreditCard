package com.jun.gao.creditcard.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.R.integer;
import android.provider.BaseColumns;
import android.text.format.Time;
import android.util.Log;
import android.util.MonthDisplayHelper;

/**
 * 信用卡
 * 
 * @author gaojun
 * 
 */
public final class CreditCard implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int mId = -1;
	private String mBankName; // 银行名
	private String mCardName; // 卡名称
	private String mIdentityNum; // 持卡人身份证
	private String mPhoneNum; // 绑定的手机号
	private int mBillDay; // 账单日
	private int mPaymentDay; // 还款日
	private String mLast4Num = "0000"; // 后4位
	private Date mDateLastPaied = new Date(); // 最后还款的日期
	private boolean mIsPaied = false; // 是否已还
	private Time mRemindTime = null;	//还款提醒时间
	private int mRemindDay = 0;		//还款提醒日 

	public String getBillStatus()
	{
		String status = isChuZhang() ? "已出账" : "未出帐";
		return status;
	}

	public String getLastPaymentDate()
	{
		StringBuffer ret = new StringBuffer();
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int today = calendar.get(Calendar.DAY_OF_MONTH);
		int daysThisMonth = calendar.getActualMaximum(Calendar.DATE);
		if (mPaymentDay > mBillDay)
		{
			if (today <= mBillDay)
			{
				month += 1;
			}
			else
			{
				month += 2;
			}
		}
		else
		{
			if (today <= mBillDay)
			{
				month += 2;
			}
			else
			{
				month += 3;
			}
		}

		if (month > 12)
		{
			year++;
			month = month - 12;
		}

		int day = mPaymentDay;
		if (mPaymentDay > daysThisMonth)
		{
			day = daysThisMonth;
		}
		ret.append(year).append("-").append(month).append("-")
				.append(day);
		return ret.toString();
	}

	public int getId()
	{
		return mId;
	}

	public void setId(int id)
	{
		this.mId = id;
	}

	// 距出账日剩余天数
	public int daysToBillDay()
	{
		int daysToBillDay = 0;
		Calendar calendar = Calendar.getInstance();
		int today = calendar.get(Calendar.DATE);
		if (today <= mBillDay)
		{
			daysToBillDay = mBillDay - today;
		}
		else
		{
			int daysLeftThisMonth = calendar.getActualMaximum(Calendar.DATE)
					- today;
			daysToBillDay = daysLeftThisMonth + mBillDay;
		}

		return daysToBillDay;
	}

	public boolean isChuZhang()
	{
		boolean chuZhang = false;
		int today = Calendar.getInstance().get(Calendar.DATE);
		if (mPaymentDay > mBillDay)
		{
			if (today > mBillDay && today < mPaymentDay)
			{
				chuZhang = true;
			}
		}
		else
		{
			if (today < mPaymentDay || today > mBillDay)
			{
				chuZhang = true;
			}
		}

		return chuZhang;
	}

	public String getBillDate()
	{
		String date = null;
		int month = Calendar.getInstance().get(Calendar.MONTH);
		date = month - 1 + "月" + (mBillDay + 1) + "号" + " - " + month + "月"
				+ mBillDay + "号";

		return date;
	}

	public boolean isPaied()
	{
		return mIsPaied;
	}

	public void setIsPaied(boolean isPaied)
	{
		mIsPaied = isPaied;
	}

	public String getStrBillDate()
	{
		return String.valueOf(mBillDay);
	}

	public void setmLast4Num(String mLast4Num)
	{
		this.mLast4Num = mLast4Num;
	}

	public String getStrPaymentDate()
	{
		return String.valueOf(mPaymentDay);
	}

	public String getStrLastDate()
	{
		return transDateToString(mDateLastPaied);
	}

	private String transDateToString(Date date)
	{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		return format.format(date);
	}

	public String getmBankName()
	{
		return mBankName;
	}

	public void setmBankName(String mBankName)
	{
		this.mBankName = mBankName;
	}

	public String getmCardNum()
	{
		return mCardName;
	}

	public void setmCardNum(String mCardNum)
	{
		this.mCardName = mCardNum;
	}

	public String getBankName()
	{
		return mBankName;
	}

	public void setBankName(String bankName)
	{
		mBankName = bankName;
	}

	public String getCardName()
	{
		return mCardName;
	}

	public void setCardName(String cardNum)
	{
		mCardName = cardNum;
	}

	public String getIdentityNum()
	{
		return mIdentityNum;
	}

	public void setIdentityNum(String identityNum)
	{
		mIdentityNum = identityNum;
	}

	public String getPhoneNum()
	{
		return mPhoneNum;
	}

	public void setPhoneNum(String phoneNum)
	{
		mPhoneNum = phoneNum;
	}

	public int getBillDay()
	{
		return mBillDay;
	}

	public void setBillDay(int billDate)
	{
		mBillDay = billDate;
	}

	public int getPaymentDay()
	{
		return mPaymentDay;
	}

	public void setPaymentDay(int paymentDate)
	{
		mPaymentDay = paymentDate;
	}

	public String getLast4Num()
	{
		if (mCardName.length() < 4)
		{
			return mCardName;
		}

		return mCardName.substring(mCardName.length() - 4);
	}

	public Date getDateLastPaied()
	{
		return mDateLastPaied;
	}

	public void setDateLastPaied(Date dateLastPaied)
	{
		mDateLastPaied = dateLastPaied;
	}

	public String getmLast4Num()
	{
		return mLast4Num;
	}

	public Date getmDateLastPaied()
	{
		return mDateLastPaied;
	}

	public void setmDateLastPaied(Date mDateLastPaied)
	{
		this.mDateLastPaied = mDateLastPaied;
	}

	public String getmIdentityNum()
	{
		return mIdentityNum;
	}

	public void setmIdentityNum(String mIdentityNum)
	{
		this.mIdentityNum = mIdentityNum;
	}

	public String getmPhoneNum()
	{
		return mPhoneNum;
	}

	public void setmPhoneNum(String mPhoneNum)
	{
		this.mPhoneNum = mPhoneNum;
	}

	public static abstract class CreditCardColumn implements BaseColumns
	{
		public static final String CARD_NUM = "card_num"; // 卡号
		public static final String CARD_BANK = "card_bank"; // 银行
		public static final String CARD_LAST4NUM = "last_fournum"; // 后4位
		public static final String IDENTITY_NUM = "identity_num"; // 持卡人身份证
		public static final String PHONE_NUM = "phone_num"; // 持卡人身份证
		public static final String DATE_BILL = "bill_date"; // 账单日
		public static final String DATE_PAYMENT = "payment_date"; // 还款日
		public static final String DATE_LASTPAIED = "lastpaid_date";// 最后还款的日期
		public static final String STATUS_PAID = "status_paid";// 还款状态
	}

}
