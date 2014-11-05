package com.jun.gao.creditcard.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.jun.gao.creditcard.R;
import com.jun.gao.creditcard.model.CreditCard;
import com.jun.gao.creditcard.util.BankLogo;

public class CreditCardView extends FrameLayout
{
	private ViewHolder mTag = null;
	private ClickListener mListener = null;

	public interface ClickListener
	{
		public void onButtonClicked(CreditCardView view, CreditCard card);
	}

	public void setListener(ClickListener listener)
	{
		mListener = listener;
	}

	public void init(Context context)
	{
		LayoutInflater inflater = LayoutInflater.from(context);
		View convertView = inflater.inflate(R.layout.adapter_creditcard, this);
		mTag = new ViewHolder();
		mTag.mImgIcon = (ImageView) convertView
				.findViewById(R.id.imageView_adapterCreditCard_bankIcon);
		mTag.mTvAlias = (TextView) convertView
				.findViewById(R.id.textView_adapterCreditCard_cardAlias);
		mTag.mTvDayBill = (TextView) convertView
				.findViewById(R.id.textView_adapterCreditCard_dayBill);
		mTag.mTvDayPayment = (TextView) convertView
				.findViewById(R.id.textView_adapterCreditCard_dayPayment);
		mTag.mTvBillStatus = (TextView) convertView
				.findViewById(R.id.textView_adapterCreditCard_billStatus);
		mTag.mBtnHuanKuanStatus = (ImageView) convertView
				.findViewById(R.id.imageView_adapterCreditCard_huanKuanStatus);
		mTag.mTvLastPaymentDate = (TextView) convertView
				.findViewById(R.id.textView_viewCreditCard_lastPayDay);
		convertView.setTag(mTag);

	}

	public CreditCardView(Context context)
	{
		super(context);

		init(context);
	}

	public CreditCardView(Context context, AttributeSet attrs)
	{
		super(context, attrs);

		init(context);
	}

	public void displayCreditCard(final CreditCard card)
	{
		mTag.mImgIcon.setImageResource(BankLogo.getBankLogoId(card
				.getBankName()));
		mTag.mTvAlias.setText(card.getLast4Num());
		mTag.mTvDayBill.setText(card.getStrBillDate());
		mTag.mTvDayPayment.setText(card.getStrPaymentDate());
		mTag.mTvBillStatus.setText(card.getBillStatus());
		mTag.mTvLastPaymentDate.setText(card.getLastPaymentDate());
		if (card.isPaied())
		{
			mTag.mBtnHuanKuanStatus.setImageResource(R.drawable.paid);
		}
		else
		{
			mTag.mBtnHuanKuanStatus.setImageResource(R.drawable.notpaid);
		}

		mTag.mBtnHuanKuanStatus.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if (null != mListener)
				{
					mListener.onButtonClicked(CreditCardView.this, card);
				}
			}
		});
	}

	private static class ViewHolder
	{
		private ImageView mImgIcon = null; // 银行logo
		private TextView mTvDayBill = null; // 账单日
		private TextView mTvDayPayment = null; // 还款日
		private TextView mTvAlias = null; // 卡别名
		private TextView mTvBillStatus = null; // 账单状态
		private ImageView mBtnHuanKuanStatus = null; // 还款状态
		private TextView mTvLastPaymentDate = null; // 最后还款日
	}

}
