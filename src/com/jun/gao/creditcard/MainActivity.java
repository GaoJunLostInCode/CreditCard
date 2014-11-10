package com.jun.gao.creditcard;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jun.gao.creditcard.activity.CreditCardAddActivity;
import com.jun.gao.creditcard.database.CreditCardSQLiteOperate;
import com.jun.gao.creditcard.database.CreditCardSQLiteOperateIml;
import com.jun.gao.creditcard.model.CreditCard;
import com.jun.gao.creditcard.service.PaymentRemindService;
import com.jun.gao.creditcard.view.CreditCardView;
import com.jun.gao.creditcard.view.TitleBar;

public class MainActivity extends FragmentActivity implements
		CreditCardView.ClickListener
{
	private List<CreditCard> mCreditCards = null; // data source
	// private CreditCardListFragment mFragmentList = null; // CreditCard list
	private TitleBar mTitleBar = null;	//titalBar
	private TextView mTvDate = null; // 显示日期
	private Dialog mDialogDel = null;
	private LinearLayout mLlContainer = null;

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

	@Override
	protected void onResume()
	{
		super.onResume();
		refresh();
	}

	@Override
	protected void onCreate(Bundle arg0)
	{
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		
		Intent intent = new Intent("com.jun.gao.creditcard.service.PaymentRemindService");
//		bindService(intent, null, Service.BIND_AUTO_CREATE);
		startService(intent);
		
		setContentView(R.layout.activity_main);
		mLlContainer = (LinearLayout) findViewById(R.id.container);

		mTitleBar = (TitleBar) findViewById(R.id.titleBar_activityCardList_title);
		mTitleBar.setTitle("我的卡片");
		mTitleBar.setButtonTextAndListener("添加", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(MainActivity.this,
						CreditCardAddActivity.class);
				startActivity(intent);
			}
		});
		mTvDate = (TextView) findViewById(R.id.textView_activityCardList_date);
		Date dateNow = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		mTvDate.setText(format.format(dateNow));
	}

	private void refresh()
	{
		mLlContainer.removeAllViews();
		CreditCardSQLiteOperate mSqLiteOperate = new CreditCardSQLiteOperateIml(
				getApplicationContext());
		mCreditCards = mSqLiteOperate.listAllCreditCards();

		for (int i = 0, len = mCreditCards.size(); i < len; i++)
		{
			final CreditCard creditCard = mCreditCards.get(i);
			CreditCardView cardView = new CreditCardView(this);
			cardView.setListener(this);
			mLlContainer.addView(cardView);
			cardView.displayCreditCard(creditCard);
			cardView.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					startCardDetailActivity(creditCard);
				}
			});
			cardView.setOnLongClickListener(new OnLongClickListener()
			{
				@Override
				public boolean onLongClick(View v)
				{
					onLongItemClicked(creditCard);
					return false;
				}
			});
		}
	}
	
	private void startCardDetailActivity(CreditCard card)
	{
		Intent intent = new Intent(this, CreditCardAddActivity.class);
		intent.putExtra(CreditCardAddActivity.INTENT_KEY_CREDITCARD, card);
		startActivity(intent);
	}

	public void onLongItemClicked(final CreditCard card)
	{
		if (null == mDialogDel)
		{
			Builder builder = new Builder(MainActivity.this);
			builder.setNegativeButton("确定",
					new DialogInterface.OnClickListener()
					{
						@Override
						public void onClick(DialogInterface dialog, int which)
						{
							CreditCardSQLiteOperate mSqLiteOperate = new CreditCardSQLiteOperateIml(
									getApplicationContext());
							mSqLiteOperate.deleteCreditCard(card);
							refresh();
						}
					});
			builder.setPositiveButton("取消",
					new DialogInterface.OnClickListener()
					{
						@Override
						public void onClick(DialogInterface dialog, int which)
						{
							mDialogDel.dismiss();
						}
					});
			builder.setTitle("删除");
			builder.setMessage("确定删除吗?");

			mDialogDel = builder.create();
		}

		mDialogDel.show();
	}

	@Override
	public void onButtonClicked(CreditCardView view, CreditCard card)
	{
		card.setIsPaied(!card.isPaied());
		CreditCardSQLiteOperate mSqLiteOperate = new CreditCardSQLiteOperateIml(
				getApplicationContext());
		mSqLiteOperate.updateCrditCard(card);
		view.displayCreditCard(card);
	}

}
