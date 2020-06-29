package com.example.ttsfornoti;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TTSService extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("TTSService","onCreate()");

        TextView tx1 = (TextView)findViewById(R.id.textview01);

        Intent intent = getIntent();

        String TTS_Data = intent.getExtras().getString("TTS_Data");
        tx1.setText(TTS_Data);
    }
}