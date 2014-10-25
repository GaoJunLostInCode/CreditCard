package com.jun.gao.creditcard.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.jun.gao.creditcard.R;
import com.jun.gao.creditcard.database.SQLiteOperate;
import com.jun.gao.creditcard.database.SQLiteOperateIml;
import com.jun.gao.creditcard.fragment.CreditCardAddFragment;
import com.jun.gao.creditcard.model.CreditCard;

public class CreditCardAddActivity extends CreditCardBaseActivity
{
	private CreditCardAddFragment mFragment = null;

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
		mFragment = (CreditCardAddFragment) getSupportFragmentManager()
				.findFragmentById(R.id.fragment_activity_creditCardAdd);

		return view;
	}

	@Override
	protected void onRightOptButtonClicked(View view)
	{
		CreditCard card = mFragment.createCreditCard();

		SQLiteOperate mSqLiteOperate = new SQLiteOperateIml(this);
		long id = mSqLiteOperate.addCreditCard(card);
		if (id > 0)
		{
			Toast.makeText(this, "添加成功!", Toast.LENGTH_SHORT).show();
			finish();
		}
		else
		{
			Toast.makeText(this, "添加失败!", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	protected String rightButtonText()
	{
		return "保存";
	}

}
