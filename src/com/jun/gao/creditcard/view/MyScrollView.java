package com.jun.gao.creditcard.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.jun.gao.creditcard.R;

public class MyScrollView extends ScrollView
{
	private ViewGroup mContainer = null;
	private ImageView mImgArrow = null;

	public MyScrollView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		LayoutInflater inflater = LayoutInflater.from(context);
		inflater.inflate(R.layout.view_myscroll, this);
		mContainer = (ViewGroup) findViewById(R.id.container);
		mImgArrow = (ImageView) findViewById(R.id.imageView_dragArrow);
	}

	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt)
	{
		super.onScrollChanged(l, t, oldl, oldt);

		Log.d("gao.takeaway", "t====" + t + "--cha====" + (t - oldt));
		if ((t <= 0) && (t - oldt <= 0))
		{
			mImgArrow.setVisibility(View.VISIBLE);

			AnimationSet animationSet = new AnimationSet(true);
			// 参数1～2：x轴的开始位置
			// 参数3～4：y轴的开始位置
			// 参数5～6：x轴的结束位置
			// 参数7～8：x轴的结束位置
			TranslateAnimation translateAnimation = new TranslateAnimation(
					Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF,
					0f, Animation.RELATIVE_TO_SELF, 0f,
					Animation.RELATIVE_TO_SELF, 0.5f);
			translateAnimation.setDuration(1000);
			RotateAnimation rotateAnimation = new RotateAnimation(0f, -180f,
					Animation.RELATIVE_TO_SELF, 0.5f,
					Animation.RELATIVE_TO_SELF, 0.5f);
			rotateAnimation.setDuration(1000);
			rotateAnimation.setFillAfter(true);
			animationSet.addAnimation(translateAnimation);
			animationSet.addAnimation(rotateAnimation);

			mImgArrow.startAnimation(animationSet);

		}
		else if (t > 0 && (t - oldt) > 0)
		{
			mImgArrow.setVisibility(View.GONE);
		}
	}

}
