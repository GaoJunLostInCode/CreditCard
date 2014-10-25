package com.jun.gao.creditcard.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.jun.gao.creditcard.R;
import com.jun.gao.creditcard.model.CreditCard;

public class CreditCardAddFragment extends Fragment
{
	private EditText mEtCardNum = null;

	private Spinner mSpinnerBank = null;
	private Spinner mSpinnerDayBill = null;
	private Spinner mSpinnerDayPayment = null;
	private ArrayAdapter<String> mAdapterSpinnerDays = null;
	private ArrayAdapter<String> mAdapterSpinnerBanks = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.fragment_creditcard_add, null);

		mEtCardNum = (EditText) view
				.findViewById(R.id.editText_fragmentAdd_cardNum);
		mSpinnerBank = (Spinner) view
				.findViewById(R.id.spinner_fragmentAdd_bank);
		mSpinnerDayBill = (Spinner) view
				.findViewById(R.id.spinner_adapterFragmentAdd_dayBill);
		mSpinnerDayPayment = (Spinner) view
				.findViewById(R.id.spinner_adapterFragmentAdd_dayPayment);

		String[] banks = getResources().getStringArray(R.array.spinner_banks);
		mAdapterSpinnerBanks = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_spinner_dropdown_item, banks);
		mSpinnerBank.setAdapter(mAdapterSpinnerBanks);

		String[] days = getResources().getStringArray(R.array.spinner_days);
		mAdapterSpinnerDays = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_spinner_dropdown_item, days);
		mSpinnerDayBill.setAdapter(mAdapterSpinnerDays);
		mSpinnerDayPayment.setAdapter(mAdapterSpinnerDays);

		return view;
	}

	public CreditCard createCreditCard()
	{
		CreditCard card = new CreditCard();
		card.setmCardNum(mEtCardNum.getText().toString());

		String bank = mAdapterSpinnerBanks.getItem(mSpinnerBank
				.getSelectedItemPosition());
		card.setBankName(bank);

		String day = mAdapterSpinnerDays.getItem(mSpinnerDayBill
				.getSelectedItemPosition());
		card.setBillDate(Integer.valueOf(day));

		day = mAdapterSpinnerDays.getItem(mSpinnerDayPayment
				.getSelectedItemPosition());
		card.setPaymentDate(Integer.valueOf(day));

		return card;
	}
}
