package com.jun.gao.creditcard.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jun.gao.creditcard.R;

public class TitleBar extends LinearLayout
{
	private TextView mTvTitle = null;
	private TextView mBtnRight = null;

	public TitleBar(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		LayoutInflater inflater = LayoutInflater.from(context);
		inflater.inflate(R.layout.view_title_bar, this);
		mTvTitle = (TextView) findViewById(R.id.textView_viewTitleBar_title);
		mBtnRight = (TextView) findViewById(R.id.textView_viewTitleBar_rightOpt);
	}

	public void setTitle(String title)
	{
		mTvTitle.setText(title);
	}

	public void setButtonTextAndListener(String text,
			View.OnClickListener listener)
	{
		mBtnRight.setText(text);
		mBtnRight.setOnClickListener(listener);
	}

	public void setButtonHidden(boolean hidden)
	{
		if (hidden)
		{
			mBtnRight.setVisibility(View.GONE);
		}
		else
		{
			mBtnRight.setVisibility(View.VISIBLE);
		}
	}
}
