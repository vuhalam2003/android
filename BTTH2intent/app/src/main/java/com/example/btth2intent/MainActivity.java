package com.example.btth2intent;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button btnCallPhone;
    Button btnSendSMS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCallPhone = findViewById(R.id.btncallphone);
        btnSendSMS = findViewById(R.id.btnsendsms);

        if (btnCallPhone != null) {
            btnCallPhone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent callPhoneIntent = new Intent(MainActivity.this, CallPhoneActivity.class);
                    startActivity(callPhoneIntent);
                }
            });
        } else {
            Log.e("MainActivity", "btnCallPhone is null");
        }

        if (btnSendSMS != null) {
            btnSendSMS.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent sendSMSIntent = new Intent(MainActivity.this, smsactivity.class);
                    startActivity(sendSMSIntent);
                }
            });
        } else {
            Log.e("MainActivity", "btnSendSMS is null");
        }
    }
}
