package com.example.ttsfornoti;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            Thread.sleep(1000); //대기 초 설정
            startActivity(new Intent(SplashActivity.this, PermissionActivity.class));
            finish();
        } catch (Exception e) {
            Log.e("Error", "SplashActivity ERROR", e);
        }
    }
}