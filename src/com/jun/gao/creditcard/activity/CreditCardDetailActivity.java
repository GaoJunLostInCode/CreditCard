package com.jun.gao.creditcard.activity;

import java.io.Serializable;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.jun.gao.creditcard.R;
import com.jun.gao.creditcard.fragment.CreditCardDetailFragment;
import com.jun.gao.creditcard.model.CreditCard;

public class CreditCardDetailActivity extends CreditCardBaseActivity
{
	public static final String INTENT_BUNDLE_NAME = "CreditCardDetail";
	public static final String INTENT_KEY_CARD = "CreditCard";
	private CreditCard mCreditCard = null;
	private CreditCardDetailFragment mFragment = null;

	@Override
	protected String title()
	{
		return mCreditCard != null ? mCreditCard.getmLast4Num() : "";
	}

	@Override
	protected void onCreate(Bundle arg0)
	{
		Bundle bundle = getIntent().getBundleExtra(INTENT_BUNDLE_NAME);

		Serializable data = bundle.getSerializable(INTENT_KEY_CARD);
		if (data != null && data instanceof CreditCard)
		{
			mCreditCard = (CreditCard) data;
		}

		super.onCreate(arg0);

		mFragment.showCreditCard(mCreditCard);
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

	@Override
	protected void onRightOptButtonClicked(View view)
	{
		// TODO Auto-generated method stub

	}

	@Override
	protected String rightButtonText()
	{
		return null;
	}
}
