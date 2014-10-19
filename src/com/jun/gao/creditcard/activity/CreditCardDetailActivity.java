package com.jun.gao.creditcard.activity;

import android.view.LayoutInflater;
import android.view.View;

import com.jun.gao.creditcard.R;
import com.jun.gao.creditcard.fragment.CreditCardDetailFragment;
import com.jun.gao.creditcard.model.CreditCard;

public class CreditCardDetailActivity extends CreditCardBaseActivity
{
	private CreditCard mCreditCard = null;
	private CreditCardDetailFragment mFragment = null;

	@Override
	protected String title()
	{
		return mCreditCard != null ? mCreditCard.getmLast4Num() : "";
	}

	@Override
	protected View onCreateContentView()
	{
		LayoutInflater inflater = LayoutInflater.from(this);
		View view = inflater.inflate(R.layout.activity_creditcard_detail, null);

		mFragment = (CreditCardDetailFragment) getSupportFragmentManager()
				.findFragmentById(R.id.fragment_activity_creditCardDetail);

		return view;
	}
}
