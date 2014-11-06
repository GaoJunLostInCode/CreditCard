package com.jun.gao.creditcard.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.jun.gao.creditcard.R;
import com.jun.gao.creditcard.model.CreditCard;
import com.jun.gao.creditcard.util.BankUtil;

public class CreditCardAddFragment extends Fragment
{
	private EditText mEtCardName = null;

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

		mEtCardName = (EditText) view
				.findViewById(R.id.editText_fragmentAdd_cardName);
		mSpinnerBank = (Spinner) view
				.findViewById(R.id.spinner_fragmentAdd_bank);
		mSpinnerDayBill = (Spinner) view
				.findViewById(R.id.spinner_adapterFragmentAdd_dayBill);
		mSpinnerDayPayment = (Spinner) view
				.findViewById(R.id.spinner_adapterFragmentAdd_dayPayment);

		String[] banks = BankUtil.getBankNames();
		mAdapterSpinnerBanks = new ArrayAdapter<String>(getActivity(),
				R.layout.item_spinner, R.id.textView_itemSpinner_itemValue,
				banks);
		mSpinnerBank.setAdapter(mAdapterSpinnerBanks);

		String[] days = getResources().getStringArray(R.array.spinner_days);
		mAdapterSpinnerDays = new ArrayAdapter<String>(getActivity(),
				R.layout.item_spinner, R.id.textView_itemSpinner_itemValue,
				days);
		mSpinnerDayBill.setAdapter(mAdapterSpinnerDays);
		mSpinnerDayPayment.setAdapter(mAdapterSpinnerDays);

		return view;
	}

	public void showCreditCard(CreditCard card)
	{
		mEtCardName.setText(card.getCardName());
		mSpinnerBank.setSelection(BankUtil.getBankIndex(card.getBankName()));
		mSpinnerDayBill.setSelection(card.getBillDay() - 1);
		mSpinnerDayPayment.setSelection(card.getPaymentDay() - 1);
	}

	public CreditCard creatCreditCard()
	{
		return setCredtiCardValues(null);
	}

	public void editCreditCard(CreditCard card)
	{
		setCredtiCardValues(card);
	}

	private CreditCard setCredtiCardValues(CreditCard card)
	{
		if (mEtCardName.getText() == null
				|| mEtCardName.getText().toString().equals(""))
		{
			Toast.makeText(getActivity(), "请输入卡名称！", Toast.LENGTH_SHORT).show();
			return null;
		}

		if (null == card)
		{
			card = new CreditCard();
		}

		card.setmCardNum(mEtCardName.getText().toString());

		String bank = mAdapterSpinnerBanks.getItem(mSpinnerBank
				.getSelectedItemPosition());
		card.setBankName(bank);

		String day = mAdapterSpinnerDays.getItem(mSpinnerDayBill
				.getSelectedItemPosition());
		card.setBillDay(Integer.valueOf(day));

		day = mAdapterSpinnerDays.getItem(mSpinnerDayPayment
				.getSelectedItemPosition());
		card.setPaymentDay(Integer.valueOf(day));

		return card;
	}
}
