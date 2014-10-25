package com.jun.gao.creditcard.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jun.gao.creditcard.R;

public abstract class CreditCardBaseActivity extends FragmentActivity
{
	protected abstract String title();

	protected abstract String rightButtonText();

	protected abstract View onCreateContentView();

	protected abstract void onRightOptButtonClicked(View view);

	@Override
	protected void onCreate(Bundle arg0)
	{
		super.onCreate(arg0);

		LayoutInflater inflater = LayoutInflater.from(this);
		LinearLayout llView = (LinearLayout) inflater.inflate(
				R.layout.activity_creditcard_base, null);
		setContentView(llView);

		TextView tvTitle = (TextView) findViewById(R.id.textView_activityCreditCardBase_title);
		tvTitle.setText(title());

		Button button = (Button) findViewById(R.id.button_activityBase_option);
		String text = rightButtonText();
		if (null == text)
		{
			button.setVisibility(View.GONE);
		}
		else
		{
			button.setText(rightButtonText());
			button.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					onRightOptButtonClicked(v);
				}
			});
		}

		llView.addView(onCreateContentView());
	}
}
