package com.jun.gao.creditcard.activity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jun.gao.creditcard.R;
import com.jun.gao.creditcard.database.SQLiteOperate;
import com.jun.gao.creditcard.database.SQLiteOperateIml;
import com.jun.gao.creditcard.fragment.CreditCardListFragment;
import com.jun.gao.creditcard.fragment.CreditCardListFragment.ItemClickListener;
import com.jun.gao.creditcard.model.CreditCard;
import com.jun.gao.creditcard.view.TitleBar;

public class CreditCardListActivity extends FragmentActivity
{
	private List<CreditCard> mCreditCards = null; // data source
	private CreditCardListFragment mFragmentList = null; // CreditCard list
	private TitleBar mTitleBar = null;
	private TextView mTvDate = null;  // 显示日期
	private Dialog mDialogDel = null;

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

		setContentView(R.layout.activity_creditcard_list);

		mTitleBar = (TitleBar) findViewById(R.id.titleBar_activityCardList_title);
		mTitleBar.setTitle("信用卡");
		mTitleBar.setButtonTextAndListener("添加", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(CreditCardListActivity.this,
						CreditCardAddActivity.class);
				startActivity(intent);
			}
		});
		mTvDate = (TextView) findViewById(R.id.textView_activityCardList_date);
		Date dateNow = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		mTvDate.setText(format.format(dateNow));

		FragmentManager fragmentManager = getSupportFragmentManager();
		mFragmentList = (CreditCardListFragment) fragmentManager
				.findFragmentById(R.id.fragment_activity_creditCardList);
		mFragmentList.setItemClickListener(new ItemClickListener()
		{
			@Override
			public void onItemClicked(CreditCard card)
			{
				// Intent intent = new Intent(CreditCardListActivity.this,
				// CreditCardDetailActivity.class);
				// Bundle bundle = new Bundle();
				// bundle.putSerializable(
				// CreditCardDetailActivity.INTENT_KEY_CARD, card);
				// intent.putExtra(CreditCardDetailActivity.INTENT_BUNDLE_NAME,
				// bundle);
				// startActivity(intent);

				Toast.makeText(CreditCardListActivity.this, "ItemClicked..",
						Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onLongItemClicked(final CreditCard card)
			{
				if (null == mDialogDel)
				{
					Builder builder = new Builder(CreditCardListActivity.this);
					builder.setNegativeButton("确定",
							new DialogInterface.OnClickListener()
							{
								@Override
								public void onClick(DialogInterface dialog,
										int which)
								{
									SQLiteOperate mSqLiteOperate = new SQLiteOperateIml(
											getApplicationContext());
									mSqLiteOperate.deleteCreditCard(card);
									refresh();
								}
							});
					builder.setPositiveButton("取消",
							new DialogInterface.OnClickListener()
							{
								@Override
								public void onClick(DialogInterface dialog,
										int which)
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
		});
	}

	private void refresh()
	{
		SQLiteOperate mSqLiteOperate = new SQLiteOperateIml(
				getApplicationContext());
		mCreditCards = mSqLiteOperate.listAllCreditCards();

		mFragmentList.refreshList(mCreditCards);
	}
}
