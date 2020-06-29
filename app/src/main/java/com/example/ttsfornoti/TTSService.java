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
            String TTS_Data = intent.getExtras().getString("TTS_Data");

            Log.i("TTSService","TTS_Data : " + TTS_Data);

            tts.speak(TTS_Data, TextToSpeech.QUEUE_ADD, null);
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();

        Log.i("TTSService","onDestory()");
    }
}