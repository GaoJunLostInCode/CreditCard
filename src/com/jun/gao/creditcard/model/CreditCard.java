package com.jun.gao.creditcard.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.provider.BaseColumns;

public final class CreditCard implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String mBankName;	//银行名
	private String mCardNum;	//卡号
	private String mIdentityNum;	//持卡人身份证
	private String mPhoneNum;	//绑定的手机号
	private int mBillDate;		//账单日
	private int mPaymentDate;	//还款日
	private String mLast4Num="0000";	//后4位
	private Date mDateLastPaied = new Date();	//最后还款的日期
	
	public String getStrBillDate()
	{
		return String.valueOf(mBillDate);
	}
	
	public void setmLast4Num(String mLast4Num)
	{
		this.mLast4Num = mLast4Num;
	}

	public String getStrPaymentDate()
	{
		return String.valueOf(mPaymentDate);
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
		return mCardNum;
	}
	public void setmCardNum(String mCardNum)
	{
		this.mCardNum = mCardNum;
	}
	
	public String getBankName()
	{
		return mBankName;
	}

	public void setBankName(String bankName)
	{
		mBankName = bankName;
	}

	public String getCardNum()
	{
		return mCardNum;
	}

	public void setCardNum(String cardNum)
	{
		mCardNum = cardNum;
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

	public int getBillDate()
	{
		return mBillDate;
	}

	public void setBillDate(int billDate)
	{
		mBillDate = billDate;
	}

	public int getPaymentDate()
	{
		return mPaymentDate;
	}

	public void setPaymentDate(int paymentDate)
	{
		mPaymentDate = paymentDate;
	}

	public String getLast4Num()
	{
		if (mCardNum.length() < 4)
		{
			return mCardNum;
		}
		
		return mCardNum.substring(mCardNum.length()-4);
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
		public static final String CARD_NUM = "card_num";		//卡号
		public static final String CARD_BANK = "card_bank";		//银行
		public static final String CARD_LAST4NUM = "last_fournum";	//后4位
		public static final String IDENTITY_NUM = "identity_num";	//持卡人身份证
		public static final String PHONE_NUM = "phone_num";	//持卡人身份证
		public static final String DATE_BILL = "bill_date";	//账单日
		public static final String DATE_PAYMENT = "payment_date";	//还款日
		public static final String DATE_LASTPAIED = "lastpaid_date";//最后还款的日期
	}
	
}
