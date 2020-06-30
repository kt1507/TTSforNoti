package com.example.ttsfornoti;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.Locale;

public class TTSService extends Service {

    TextToSpeech tts;
    String Check_Appname[] = new String[100];
    int Check_Appname_Length;

    public TTSService(){

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status!= TextToSpeech.ERROR){
                    tts.setLanguage(Locale.KOREAN);
                }
            }
        });

        Log.i("TTSService","onCreate()");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        Log.i("TTSService", "onStartCommand()");

        if(intent == null){
            return Service.START_STICKY;
        } else{
            Check_Appname_Length = intent.getIntExtra("Check_Appname_length",Check_Appname_Length);
            if(intent.getStringArrayExtra("Check_Appname") != null){
                String Intent_Appname[] = intent.getStringArrayExtra("Check_Appname");
                System.arraycopy(Intent_Appname,0,Check_Appname,0,100);
            }
            String App_name = intent.getExtras().getString("App_name", null);
            String TTS_Data = intent.getExtras().getString("TTS_Data", null);

            Log.i("TTSService","Check_Appname_Length : " + Check_Appname_Length);
            Log.i("TTSService","TTS_Data : " + TTS_Data);

            for(int i = 0; i < Check_Appname_Length; i++){
                if(Check_Appname[i] != null && Check_Appname[i].equals(App_name)){
                    tts.speak(TTS_Data, TextToSpeech.QUEUE_ADD, null);
                }
            }

        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();

        Log.i("TTSService","onDestory()");
    }
}