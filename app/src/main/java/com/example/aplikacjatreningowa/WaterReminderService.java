package com.example.aplikacjatreningowa;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.SystemClock;

public class WaterReminderService extends IntentService {

    public WaterReminderService() {
        super("WaterReminderService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Intent alarmIntent = new Intent(this, WaterReminderReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, PendingIntent.FLAG_CANCEL_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        long triggerAtMillis = SystemClock.elapsedRealtime() + AlarmManager.INTERVAL_HOUR;
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        if (alarmManager != null) {
            alarmManager.setInexactRepeating(
                    AlarmManager.ELAPSED_REALTIME_WAKEUP,
                    triggerAtMillis,
                    AlarmManager.INTERVAL_HOUR,
                    pendingIntent
            );
        }
    }
}

