package com.jun.gao.creditcard.fragment;

import java.util.List;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.jun.gao.creditcard.R;
import com.jun.gao.creditcard.adapter.CreditCardAdapter;
import com.jun.gao.creditcard.model.CreditCard;

/**
 * A placeholder fragment containing a simple view.
 */
public class CreditCardListFragment extends Fragment
{
	private ListView mListView = null;

	public interface ItemClickListener
	{
		public void onItemClicked(CreditCard card);

		public void onLongItemClicked(CreditCard card);
	}

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
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View rootView = inflater.inflate(R.layout.fragment_creditcard_list,
				container, false);
		mListView = (ListView) rootView
				.findViewById(R.id.listView_fragment_main);

		mListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id)
			{
				CreditCard card = mAdapter.getCards().get(position);
				if (null != mListener)
				{
					mListener.onItemClicked(card);
				}
			}
		});
		mListView
				.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
				{
					@Override
					public boolean onItemLongClick(AdapterView<?> parent,
							View view, int position, long id)
					{
						CreditCard card = mAdapter.getCards().get(position);
						if (null != mListener)
						{
							mListener.onLongItemClicked(card);
						}

						return true;
					}
				});

		rootView.setBackgroundColor(Color.TRANSPARENT);
		return rootView;
	}

	public void refreshList(List<CreditCard> cards)
	{
		if (null == mAdapter)
		{
			mAdapter = new CreditCardAdapter(getActivity()
					.getApplicationContext(), cards);
			mListView.setAdapter(mAdapter);
		}
		else
		{
			mAdapter.setCreditCards(cards);
		}

		mAdapter.notifyDataSetChanged();
	}
}
