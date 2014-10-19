package com.jun.gao.creditcard.activity;

import com.jun.gao.creditcard.R;

import android.view.LayoutInflater;
import android.view.View;

public class CreditCardAddActivity extends CreditCardBaseActivity
{

	@Override
	protected String title()
	{
		return "添加";
	}

	@Override
	protected View onCreateContentView()
	{
		LayoutInflater inflater = LayoutInflater.from(this);
		View view = inflater.inflate(R.layout.activity_creditcard_add, null);
		
		return view;
	}

}
