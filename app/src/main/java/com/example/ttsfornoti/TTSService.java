package com.example.ttsfornoti;

import android.app.IntentService;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import java.util.Locale;

public class TTSService extends IntentService {
    public TTSService(String name) {
        super(name);
    }

    public TextToSpeech tts;

    @Override
    protected void onHandleIntent(final Intent workIntent) {
        // Gets data from the incoming Intent
        final String TTS_Data = workIntent.getDataString();
        // Do work here, based on the contents of dataString
        Log.e("TTS", "TTS onHandleIntent");

        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                // TODO Auto-generated method stub

                if (status == TextToSpeech.SUCCESS) {

                    Log.e("TTS", "TTS SUCCESS");

                    // 한국어 설정
                    int result = tts.setLanguage(Locale.KOREAN);

                    tts.setPitch(5); // set pitch level
                    tts.setSpeechRate(2); // set speech speed rate

                    // 한국어가 안된다면,
                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Language is not supported");
                    }
                    else {
                        speakJust(TTS_Data);
                    }
                } else {
                    Log.e("TTS", "Initilization Failed");
                }
            }
        });
    }

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

    @Override
    public void onDestroy() {

        // Don't forget to shutdown!
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }

        super.onDestroy();

    }
}