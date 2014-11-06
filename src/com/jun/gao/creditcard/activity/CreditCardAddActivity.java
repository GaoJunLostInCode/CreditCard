package com.jun.gao.creditcard.activity;

import java.io.Serializable;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Toast;

import com.jun.gao.creditcard.R;
import com.jun.gao.creditcard.database.CreditCardSQLiteOperate;
import com.jun.gao.creditcard.database.CreditCardSQLiteOperateIml;
import com.jun.gao.creditcard.fragment.CreditCardAddFragment;
import com.jun.gao.creditcard.model.CreditCard;
import com.jun.gao.creditcard.view.TitleBar;

public class CreditCardAddActivity extends FragmentActivity
{
	public static final String INTENT_KEY_CREDITCARD = "CREDIT_CARD";
	private CreditCard mCard = null;
	private TitleBar mTitleBar = null;
	private CreditCardAddFragment mFragment = null;

	private CreditCardSQLiteOperate sqlOperator = null;
	private String mTitle = "添加新卡";

	@Override
	protected void onCreate(Bundle arg0)
	{
		// TODO Auto-generated method stub
		super.onCreate(arg0);

		Serializable card = getIntent().getSerializableExtra(
				INTENT_KEY_CREDITCARD);
		if (card != null && card instanceof CreditCard)
		{
			mCard = (CreditCard) card;
		}

		sqlOperator = new CreditCardSQLiteOperateIml(this);

		setContentView(R.layout.activity_creditcard_add);
		mTitleBar = (TitleBar) findViewById(R.id.titleBar_activityCreditCardAdd_title);
		if (null != mCard)
		{
			mTitle = mCard.getCardName();
		}
		mTitleBar.setTitle(mTitle);
		mTitleBar.setButtonTextAndListener("完成", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if (null == mCard)
				{
					mCard = mFragment.creatCreditCard();
					sqlOperator.addCreditCard(mCard);
				}
				else
				{
					mFragment.editCreditCard(mCard);
					sqlOperator.updateCrditCard(mCard);
				}

				Toast.makeText(CreditCardAddActivity.this, "保存成功！",
						Toast.LENGTH_SHORT).show();
				finish();
			}
		});
		mFragment = (CreditCardAddFragment) getSupportFragmentManager()
				.findFragmentById(R.id.fragment_activity_creditCardAdd);

	}

	@Override
	protected void onResume()
	{
		super.onResume();

		if (null != mCard)
		{
			mFragment.showCreditCard(mCard);
		}
	}
}
