package com.jun.gao.creditcard.fragment;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.jun.gao.creditcard.R;
import com.jun.gao.creditcard.adapter.CreditCardAdapter;
import com.jun.gao.creditcard.database.SQLiteOperate;
import com.jun.gao.creditcard.database.SQLiteOperateIml;
import com.jun.gao.creditcard.model.CreditCard;

/**
 * A placeholder fragment containing a simple view.
 */
public class CreditCardListFragment extends Fragment
{
	public interface ItemClickListener
	{
		public void onItemClicked(CreditCard card);
	}

	private List<CreditCard> mCreditCards = null;
	private CreditCardAdapter mAdapter = null;
	private ItemClickListener mListener = null;

	public CreditCardListFragment()
	{
	}

	public void setItemClickListener(ItemClickListener listener)
	{
		mListener = listener;
	}
	
	@Override
	public void onResume()
	{
		super.onResume();
		
		refreshList();
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		mCreditCards = getAllCards();
		mAdapter = new CreditCardAdapter(getActivity().getApplicationContext(),
				mCreditCards);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View rootView = inflater.inflate(R.layout.fragment_creditcard_list,
				container, false);
		ListView listView = (ListView) rootView
				.findViewById(R.id.listView_fragment_main);
		listView.setAdapter(mAdapter);
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id)
			{
				CreditCard card = mCreditCards.get(position);
				if(null != mListener)
				{
					mListener.onItemClicked(card);
				}
			}
		});

		return rootView;
	}

	public void refreshList()
	{
		mCreditCards = getAllCards();
		mAdapter.setCreditCards(mCreditCards);
		mAdapter.notifyDataSetChanged();
	}

	private List<CreditCard> getAllCards()
	{
		SQLiteOperate mSqLiteOperate = new SQLiteOperateIml(getActivity()
				.getApplicationContext());
		return mSqLiteOperate.listAllCreditCards();
	}
}
