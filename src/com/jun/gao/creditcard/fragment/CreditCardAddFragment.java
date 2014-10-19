package com.jun.gao.creditcard.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.jun.gao.creditcard.R;
import com.jun.gao.creditcard.database.SQLiteOperate;
import com.jun.gao.creditcard.database.SQLiteOperateIml;
import com.jun.gao.creditcard.model.CreditCard;

public class CreditCardAddFragment extends Fragment
{
	private EditText mEtBank = null;
	private EditText mEtCardNum = null;
	private DatePicker mEtDayHuanKuan = null;
	private DatePicker mEtDayZhangDan = null;
	private Button mBtnSave = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.fragment_creditcard_add, null);

		mEtBank = (EditText) view.findViewById(R.id.editText_fragmentAdd_bank);
		mEtCardNum = (EditText) view
				.findViewById(R.id.editText_fragmentAdd_cardNum);
		mEtDayHuanKuan = (DatePicker) view
				.findViewById(R.id.datePicker_fragmentAdd_dayPayment);
		mEtDayZhangDan = (DatePicker) view
				.findViewById(R.id.datePicker_fragmentAdd_dayBill);

		mBtnSave = (Button) view.findViewById(R.id.button_fragmentAdd_save);
		mBtnSave.setOnClickListener(listener);

		return view;
	}

	View.OnClickListener listener = new View.OnClickListener()
	{

		@Override
		public void onClick(View v)
		{
			CreditCard card = new CreditCard();
			card.setmBankName(mEtBank.getText().toString());
			card.setmCardNum(mEtCardNum.getText().toString());
			// card.setmBillDate(mEtDayZhangDan.getText().toString());
			// card.setmPaymentDate(mEtDayHuanKuan.getText().toString());

			SQLiteOperate mSqLiteOperate = new SQLiteOperateIml(
					CreditCardAddFragment.this.getActivity());
			long id = mSqLiteOperate.addCreditCard(card);
			if (id > 0)
			{
				Toast.makeText(getActivity(), "添加成功!", Toast.LENGTH_SHORT)
						.show();
				getActivity().finish();
			}
			else
			{
				Toast.makeText(getActivity(), "添加失败!", Toast.LENGTH_SHORT)
						.show();
			}
		}
	};
}
