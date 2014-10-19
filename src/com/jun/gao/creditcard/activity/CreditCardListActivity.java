package com.jun.gao.creditcard.activity;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.jun.gao.creditcard.R;
import com.jun.gao.creditcard.database.SQLiteOperate;
import com.jun.gao.creditcard.database.SQLiteOperateIml;
import com.jun.gao.creditcard.fragment.CreditCardListFragment;
import com.jun.gao.creditcard.fragment.CreditCardListFragment.ItemClickListener;
import com.jun.gao.creditcard.model.CreditCard;

public class CreditCardListActivity extends CreditCardBaseActivity
{
	private CreditCardListFragment mFragmentList = null;

	// private CreditCardDetailFragment mFragmentDetail = null;

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings)
		{
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	static int count = 0;

	public void addCreditCard(View view)
	{
		Intent intent = new Intent(CreditCardListActivity.this,
				CreditCardAddActivity.class);
		startActivity(intent);
	}

	@Override
	protected String title()
	{
		return "信用卡";
	}

	@Override
	protected View onCreateContentView()
	{
		LayoutInflater inflater = LayoutInflater.from(this);
		View view = inflater.inflate(R.layout.activity_creditcard_list, null);

		FragmentManager fragmentManager = getSupportFragmentManager();
		mFragmentList = (CreditCardListFragment) fragmentManager
				.findFragmentById(R.id.fragment_activity_creditCardList);
		mFragmentList.setItemClickListener(new ItemClickListener()
		{
			@Override
			public void onItemClicked(CreditCard card)
			{
				// mFragmentDetail.showCreditCard(card);
			}
		});

		// mFragmentDetail = (CreditCardDetailFragment) fragmentManager
		// .findFragmentById(R.id.fragment_mainActivity_detail);

		return view;
	}

}
