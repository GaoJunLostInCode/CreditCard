package com.jun.gao.creditcard.database;

import java.util.List;

import com.jun.gao.creditcard.model.CreditCard;

public interface SQLiteOperate
{
	public long addCreditCard(CreditCard card);
	public int deleteCreditCard(CreditCard card);
	public int updateCrditCard(CreditCard card);
	public List<CreditCard> listAllCreditCards();
}
