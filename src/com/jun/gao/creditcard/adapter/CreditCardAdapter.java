package com.jun.gao.creditcard.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jun.gao.creditcard.R;
import com.jun.gao.creditcard.model.CreditCard;

public class CreditCardAdapter extends BaseAdapter
{
	private Context mContext = null;
	private List<CreditCard> mCreditCards = null;

	public CreditCardAdapter(Context context, List<CreditCard> cards)
	{
		mContext = context;
		mCreditCards = cards;
	}
	
	public void setCreditCards(List<CreditCard> cards)
	{
		mCreditCards = cards;
	}

	@Override
	public int getCount()
	{
		int count = 0;
		if (mCreditCards != null)
		{
			count = mCreditCards.size();
		}
		return count;
	}

	@Override
	public Object getItem(int position)
	{
		return mCreditCards.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		CreditCard card = (CreditCard) getItem(position);
		if (null == convertView)
		{
			LayoutInflater inflater = LayoutInflater.from(mContext);
			convertView = inflater.inflate(R.layout.adapter_creditcard, null);
			ViewHolder holder = new ViewHolder();
			holder.mTvBank = (TextView) convertView
					.findViewById(R.id.textView_adapterCreditCard_bank);
			holder.mTvCardNum = (TextView) convertView
					.findViewById(R.id.textView_adapterCreditCard_num);
			convertView.setTag(holder);
		}
		
		ViewHolder tag = (ViewHolder) convertView.getTag();
		tag.mTvBank.setText(card.getmBankName());
		tag.mTvCardNum.setText(card.getmCardNum());

		return convertView;
	}

	private static class ViewHolder
	{
		private TextView mTvBank = null;
		private TextView mTvCardNum = null;
	}
}
