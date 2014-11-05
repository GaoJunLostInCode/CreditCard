package com.jun.gao.creditcard.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Toast;

import com.jun.gao.creditcard.R;
import com.jun.gao.creditcard.database.SQLiteOperate;
import com.jun.gao.creditcard.database.SQLiteOperateIml;
import com.jun.gao.creditcard.fragment.CreditCardAddFragment;
import com.jun.gao.creditcard.model.CreditCard;
import com.jun.gao.creditcard.view.TitleBar;

public class CreditCardAddActivity extends FragmentActivity
{
	private TitleBar mTitleBar = null;
	private CreditCardAddFragment mFragment = null;

	private SQLiteOperate sqlOperator = null;

	@Override
	protected void onCreate(Bundle arg0)
	{
		// TODO Auto-generated method stub
		super.onCreate(arg0);

		sqlOperator = new SQLiteOperateIml(this);

		setContentView(R.layout.activity_creditcard_add);
		mTitleBar = (TitleBar) findViewById(R.id.titleBar_activityCreditCardAdd_title);
		mTitleBar.setTitle("添加新卡");
		mTitleBar.setButtonTextAndListener("完成", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				CreditCard card = mFragment.createCreditCard();
				sqlOperator.addCreditCard(card);
				Toast.makeText(CreditCardAddActivity.this, "保存成功！",
						Toast.LENGTH_SHORT).show();
				finish();
			}
		});
		mFragment = (CreditCardAddFragment) getSupportFragmentManager()
				.findFragmentById(R.id.fragment_activity_creditCardAdd);

	}

}
