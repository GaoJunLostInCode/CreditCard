package com.jun.gao.creditcard.util;

import java.util.HashMap;

import com.jun.gao.creditcard.R;

public class BankLogo
{
	private static final HashMap<String, Integer> BANK_LOGOS = new HashMap<String, Integer>()
	{
		{
			put("招商", Integer.valueOf(R.drawable.bank_zhao_shang));
			put("中信", Integer.valueOf(R.drawable.bank_zhong_xin));
			put("浦发", Integer.valueOf(R.drawable.bank_pu_fa));
		}
	};

	public static int getBankLogoId(String bankName)
	{
		return BANK_LOGOS.get(bankName);
	}
}
