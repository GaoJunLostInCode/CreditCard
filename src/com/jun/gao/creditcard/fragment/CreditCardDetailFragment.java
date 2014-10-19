package com.jun.gao.creditcard.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jun.gao.creditcard.R;
import com.jun.gao.creditcard.model.CreditCard;

public class CreditCardDetailFragment extends Fragment
{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View rootView = inflater.inflate(R.layout.fragment_creditcard_detail,
				null);

		return rootView;
	}

	public void showCreditCard(final CreditCard card)
	{
		
	}
}
