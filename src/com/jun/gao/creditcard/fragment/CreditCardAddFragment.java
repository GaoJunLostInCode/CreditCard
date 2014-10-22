package com.jun.gao.creditcard.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.jun.gao.creditcard.R;
import com.jun.gao.creditcard.database.SQLiteOperate;
import com.jun.gao.creditcard.database.SQLiteOperateIml;
import com.jun.gao.creditcard.model.CreditCard;

public class CreditCardAddFragment extends Fragment
{
	private EditText mEtBank = null;
	private EditText mEtCardNum = null;
	private Button mBtnSave = null;

	private Spinner mSpinnerDayBill = null;
	private Spinner mSpinnerDayPayment = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.fragment_creditcard_add, null);

		mEtBank = (EditText) view.findViewById(R.id.editText_fragmentAdd_bank);
		mEtCardNum = (EditText) view
				.findViewById(R.id.editText_fragmentAdd_cardNum);
		mSpinnerDayBill = (Spinner) view
				.findViewById(R.id.spinner_adapterFragmentAdd_dayBill);
		mSpinnerDayPayment = (Spinner) view
				.findViewById(R.id.spinner_adapterFragmentAdd_dayPayment);
		String[] days = getResources().getStringArray(R.array.spinner_days);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_spinner_dropdown_item, days);
		mSpinnerDayBill.setAdapter(adapter);
		mSpinnerDayPayment.setAdapter(adapter);

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
