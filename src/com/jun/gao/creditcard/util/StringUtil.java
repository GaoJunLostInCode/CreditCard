package com.jun.gao.creditcard.util;

public class StringUtil
{
	/**
	 * 
	 * @param a
	 * @param b
	 * @return ab
	 */
	public static String plus(String a, String b)
	{
		StringBuffer buffer = new StringBuffer(a.length() + b.length());
		buffer.append(a);
		buffer.append(b);
		return buffer.toString();
	}
}
