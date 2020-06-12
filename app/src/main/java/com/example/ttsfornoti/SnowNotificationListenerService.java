package com.example.ttsfornoti;

import android.app.Notification;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

public class SnowNotificationListenerService extends NotificationListenerService {

    private String TTS_Data;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("NotificationListener", "[snowdeer] onCreate()");

    }

    @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            Log.i("NotificationListener", "[snowdeer] onStartCommand()");
            return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("NotificationListener", "[snowdeer] onDestroy()");

    }

    //노티 정보 받아오기
    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        Log.i("NotificationListener", "[snowdeer] onNotificationPosted() - " + sbn.toString());
        Log.i("NotificationListener", "[snowdeer] PackageName:" + sbn.getPackageName());
        Log.i("NotificationListener", "[snowdeer] PostTime:" + sbn.getPostTime());

        Notification notificatin = sbn.getNotification();
        Bundle extras = notificatin.extras;
        String title = extras.getString(Notification.EXTRA_TITLE);
        //int smallIconRes = extras.getInt(Notification.EXTRA_SMALL_ICON);
        //Bitmap largeIcon = ((Bitmap) extras.getParcelable(Notification.EXTRA_LARGE_ICON));
        CharSequence text = extras.getCharSequence(Notification.EXTRA_TEXT);
        CharSequence subText = extras.getCharSequence(Notification.EXTRA_SUB_TEXT);

        Log.i("NotificationListener", "[snowdeer] Title:" + title);
        Log.i("NotificationListener", "[snowdeer] Text:" + text);
        Log.i("NotificationListener", "[snowdeer] Sub Text:" + subText);

        //패키지명으로 라벨명 가져오기
        String packageName = sbn.getPackageName();
        PackageManager packageManager = getPackageManager();
        ApplicationInfo appinfo = null;
        try {
            appinfo = packageManager.getApplicationInfo(packageName, PackageManager.GET_META_DATA);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        String Applabel = packageManager.getApplicationLabel(appinfo).toString();

        Log.i("NotificationListener", "[snowdeer] AppLabel:" + Applabel);

        //데이터 넘기기
        TTS_Data = Applabel;

        if(text != null) {
            TTS_Data = TTS_Data + " " + text;
        }
        else if(subText != null) {
            TTS_Data = TTS_Data + " " + subText;
        }
        Log.i("NotificationListener", "[snowdeer] TTS_Data:" + TTS_Data);
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        Log.i("NotificationListener", "[snowdeer] onNotificationRemoved() - " + sbn.toString());
    }


}
