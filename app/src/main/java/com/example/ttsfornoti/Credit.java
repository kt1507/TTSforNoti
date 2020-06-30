package com.example.ttsfornoti;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Credit extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.credit);

        findViewById(R.id.button02).setOnClickListener(mClickListener);

        Button button = (Button) findViewById(R.id.button01);
    }

    Button.OnClickListener mClickListener = new Button.OnClickListener(){
        @Override
        public void onClick(View view) {
            //startActivity(new Intent(Credit.this, MainActivity.class));
            finish();
        }
    };
}
