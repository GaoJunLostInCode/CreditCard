package com.jun.gao.creditcard.fragment;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.jun.gao.creditcard.R;
import com.jun.gao.creditcard.adapter.CreditCardAdapter;
import com.jun.gao.creditcard.database.SQLiteOperate;
import com.jun.gao.creditcard.database.SQLiteOperateIml;
import com.jun.gao.creditcard.model.CreditCard;

public class CreditCardListScrollFragment extends Fragment
{
	private FrameLayout mFlContainer = null;
	private List<CreditCard> mCreditCards = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.fragment_creditcard_list_scroll,
				container, false);


		return view;
	}
}
