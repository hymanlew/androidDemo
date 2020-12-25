package com.hyman.demo.sms;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hyman.demo.R;

public class SmsMainActivity extends AppCompatActivity {

    private EditText phonevalue;
    private EditText smsvalue;
    private Button phoneCall;
    private Button smsSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_main);

        phonevalue = findViewById(R.id.phonevalue);
        smsvalue = findViewById(R.id.smsvalue);
        phoneCall = findViewById(R.id.phone_button);
        smsSend = findViewById(R.id.sms_button);

        phoneCall.setOnClickListener(clickListener);
        smsSend.setOnClickListener(clickListener);
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };
}