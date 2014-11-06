package com.jun.gao.creditcard;

import android.app.Application;

public class CreditCardApp extends Application
{
	private static CreditCardApp mInstance = null;

	@Override
	public void onCreate()
	{
		super.onCreate();

		mInstance = this;
	}

	public static CreditCardApp getInstance()
	{
		return mInstance;
	}
}
