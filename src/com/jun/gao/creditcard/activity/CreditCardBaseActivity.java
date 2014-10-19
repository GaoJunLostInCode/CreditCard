package com.jun.gao.creditcard.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jun.gao.creditcard.R;

public abstract class CreditCardBaseActivity extends FragmentActivity
{
	protected abstract String title();

	protected abstract View onCreateContentView();

	@Override
	protected void onCreate(Bundle arg0)
	{
		super.onCreate(arg0);

		LayoutInflater inflater = LayoutInflater.from(this);
		LinearLayout llView = (LinearLayout) inflater.inflate(R.layout.activity_creditcard_base, null);
		setContentView(llView);
		
		TextView tvTitle = (TextView) findViewById(R.id.textView_activityCreditCardBase_title);
		tvTitle.setText(title());
		
		
		llView.addView(onCreateContentView());
        
	}
}
