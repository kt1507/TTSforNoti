package com.example.ttsfornoti;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Locale;

public class TextToSpeechActivity extends AppCompatActivity{

    // TTS
    public TextToSpeech tts;

    //public static Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       //tts = new TextToSpeech(TextToSpeechActivity.this, this);

        tts=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                // TODO Auto-generated method stub

                if (status == TextToSpeech.SUCCESS) {

                    // 한국어 설정
                    int result = tts.setLanguage(Locale.KOREAN);

                    // tts.setPitch(5); // set pitch level
                    // tts.setSpeechRate(2); // set speech speed rate

                    // 한국어가 안된다면,
                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Language is not supported");
                    } else {
                        Intent intent = getIntent();
                        String TTS_Data = intent.getStringExtra("TTS_Data");
                        // tts가 사용중이면, 말하지않는다.

                        if(!tts.isSpeaking()) {
                            tts.speak(TTS_Data, TextToSpeech.QUEUE_FLUSH, null);

                            Log.i("NotificationListener", "[snowdeer] TTS_text_speaking:" + TTS_Data);

                        }
                        //speakJust(TTS_Data);
                    }
                } else {
                    Log.e("TTS", "Initilization Failed");
                }
            }
        });
                //mContext = this;

        Intent intent = getIntent();
        String TTS_Data = intent.getStringExtra("TTS_Data");
        Log.i("NotificationListener", "[snowdeer] TTS_text_oncreate:" + TTS_Data);

        // TODO : other code

    }


    // 앱 종료시 등
    @Override
    protected void onDestroy() {

        // Don't forget to shutdown!
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }

        super.onDestroy();

    }

    /*@Override
    public void onInit(int status) {
        // TODO Auto-generated method stub

        if (status == TextToSpeech.SUCCESS) {

            // 한국어 설정
            int result = tts.setLanguage(Locale.KOREAN);

            // tts.setPitch(5); // set pitch level
            // tts.setSpeechRate(2); // set speech speed rate

            // 한국어가 안된다면,
            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "Language is not supported");
            } else {
                Intent intent = getIntent();
                String TTS_Data = intent.getStringExtra("TTS_Data");
                speakJust(TTS_Data);
            }
        } else {
            Log.e("TTS", "Initilization Failed");
        }
    }*/

    // TODO : TTS
    public void speakJust(String text) {

        int result = tts.setLanguage(Locale.KOREAN);

        // tts가 사용중이면, 말하지않는다.
        if(!tts.isSpeaking()) {
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);

            Log.i("NotificationListener", "[snowdeer] TTS_text_speaking:" + text);

            //Intent intent = new Intent(this,SnowNotificationListenerService.class);
            //startActivity(intent);
        }

    }
}