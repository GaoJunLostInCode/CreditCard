package com.jun.gao.creditcard.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jun.gao.creditcard.R;
import com.jun.gao.creditcard.model.CreditCard;
import com.jun.gao.creditcard.util.BankLogo;

public class CreditCardAdapter extends BaseAdapter
{
	private Context mContext = null;
	private List<CreditCard> mCreditCards = null;
	private ClickListener mListener = null;

	public interface ClickListener
	{
		public void onButtonClicked(CreditCard card);
	}

	public void setListener(ClickListener listener)
	{
		mListener = listener;
	}

	public CreditCardAdapter(Context context, List<CreditCard> cards)
	{
		mContext = context;
		mCreditCards = cards;
	}

	public List<CreditCard> getCards()
	{
		return mCreditCards;
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
		final CreditCard card = (CreditCard) getItem(position);
		if (null == convertView)
		{
			LayoutInflater inflater = LayoutInflater.from(mContext);
			convertView = inflater.inflate(R.layout.adapter_creditcard, null);
			ViewHolder holder = new ViewHolder();
			holder.mImgIcon = (ImageView) convertView
					.findViewById(R.id.imageView_adapterCreditCard_bankIcon);
			holder.mTvLast4Num = (TextView) convertView
					.findViewById(R.id.textView_adapterCreditCard_cardAlias);
			holder.mTvDayBill = (TextView) convertView
					.findViewById(R.id.textView_adapterCreditCard_dayBill);
			holder.mTvDayPayment = (TextView) convertView
					.findViewById(R.id.textView_adapterCreditCard_dayPayment);
			holder.mBtnHuanKuanStatus = (ImageView) convertView
					.findViewById(R.id.imageView_adapterCreditCard_huanKuanStatus);
			convertView.setTag(holder);
		}

		ViewHolder tag = (ViewHolder) convertView.getTag();
		tag.mImgIcon
				.setImageResource(BankLogo.getBankLogoId(card.getBankName()));
		tag.mTvLast4Num.setText(card.getLast4Num());
		tag.mTvDayBill.setText("账单日：" + card.getStrBillDate());
		tag.mTvDayPayment.setText("还款日：" + card.getStrPaymentDate());
		tag.mBtnHuanKuanStatus.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if (null != mListener)
				{
					mListener.onButtonClicked(card);
				}
			}
		});

		return convertView;
	}

	private static class ViewHolder
	{
		private ImageView mImgIcon = null; // 银行logo
		private TextView mTvDayBill = null; // 账单日
		private TextView mTvDayPayment = null; // 还款日
		private TextView mTvLast4Num = null; // 后四位
		private ImageView mBtnHuanKuanStatus = null; // 还款状态
	}
}
