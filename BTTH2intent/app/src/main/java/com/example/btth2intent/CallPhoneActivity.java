package com.example.btth2intent;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class CallPhoneActivity extends AppCompatActivity {
    EditText edtcall;
    ImageButton btncallphone;
    Button btnback1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_phone);
        edtcall = (EditText) findViewById(R.id.edtcall);
        btnback1 = (Button) findViewById(R.id.btnback1);
        btncallphone = (ImageButton) findViewById(R.id.btncall);
        btncallphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callintent = new Intent(Intent.ACTION_CALL,
                        Uri.parse("tel:"+edtcall.getText().toString()));
                if (ActivityCompat.checkSelfPermission(CallPhoneActivity.this,
                        android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(CallPhoneActivity.this, new
                            String[]{android.Manifest.permission.CALL_PHONE},1);
                    return;
                }
                startActivity(callintent);

            }
        });
        btnback1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}