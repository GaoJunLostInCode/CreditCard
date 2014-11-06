package com.jun.gao.creditcard.util;

import java.util.HashMap;

import android.app.Application;
import android.content.res.Resources;

import com.jun.gao.creditcard.CreditCardApp;
import com.jun.gao.creditcard.R;

public class BankUtil
{
	private static String[] BANKS = null;
	private static HashMap<String, Integer> BANK_INDEX_MAP = null;
	private static final HashMap<String, Integer> BANK_LOGOS_MAP = new HashMap<String, Integer>()
	{
		{

			put("招商", Integer.valueOf(R.drawable.bank_zhao_shang));
			put("中信", Integer.valueOf(R.drawable.bank_zhong_xin));
			put("浦发", Integer.valueOf(R.drawable.bank_pu_fa));
		}
	};

	public static int getBankLogoId(String bankName)
	{
		return BANK_LOGOS_MAP.get(bankName);
	}

	public static int getBankIndex(String bankName)
	{
		if (null == BANKS)
		{
			BANKS = CreditCardApp.getInstance().getResources()
					.getStringArray(R.array.spinner_banks);
		}

		if (null == BANK_INDEX_MAP)
		{
			BANK_INDEX_MAP = new HashMap<String, Integer>()
			{
				{
					for (int i = 0; i < BANKS.length; i++)
					{
						put(BANKS[i], Integer.valueOf(i));
					}
				}
			};
		}

		return BANK_INDEX_MAP.get(bankName).intValue();
	}

	public static String[] getBankNames()
	{
		if (null == BANKS)
		{
			BANKS = CreditCardApp.getInstance().getResources()
					.getStringArray(R.array.spinner_banks);
		}
		
		return BANKS;
	}
	
}
