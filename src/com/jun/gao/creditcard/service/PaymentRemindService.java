package com.jun.gao.creditcard.service;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import com.jun.gao.creditcard.MainActivity;
import com.jun.gao.creditcard.model.CreditCard;
import com.jun.gao.creditcard.model.CreditCardRemindRecord;
import com.jun.gao.creditcard.util.LogUtil;
import com.jun.gao.creditcard.util.SharedPreferanceUtil;

public class PaymentRemindService extends Service
{
	private static final long TIME_HOUR = 60 * 60;

	private Calendar calendar = Calendar.getInstance();
	private Timer mTimer = new Timer();

	@Override
	public IBinder onBind(Intent intent)
	{
		LogUtil.log("onBind");
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId)
	{
		mTimer.schedule(new TimerTask()
		{
			@Override
			public void run()
			{
				CreditCardRemindRecord record = SharedPreferanceUtil
						.readLastRecord(PaymentRemindService.this, 0);
				int year = calendar.get(Calendar.YEAR);
				int month = calendar.get(Calendar.MONTH) + 1;
				if (!record.isReminded(year, month))
				{
					showNotification(null);
					record = CreditCardRemindRecord.createWithYearAndMonth(
							year, month);
					record.mCardId = 0;

					SharedPreferanceUtil.writeLastRecord(
							PaymentRemindService.this, record);
				}
			}
		}, 1000, TIME_HOUR);

		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy()
	{
		mTimer.cancel();
		mTimer = null;

		super.onDestroy();
	}

	@SuppressWarnings("deprecation")
	private void showNotification(CreditCard card)
	{
		CharSequence text = "xxx 距最后还款日就剩 x 天了";

		Notification notification = new Notification(
				android.R.drawable.arrow_up_float, text,
				System.currentTimeMillis());
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
				new Intent(this, MainActivity.class), 0);

		notification.setLatestEventInfo(this, "还款提醒", text, contentIntent);
		notification.flags = Notification.FLAG_AUTO_CANCEL
				| Notification.FLAG_ONLY_ALERT_ONCE;
		notification.defaults = Notification.DEFAULT_SOUND;
		NotificationManager notiManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		notiManager.notify(0, notification);
	}
}
