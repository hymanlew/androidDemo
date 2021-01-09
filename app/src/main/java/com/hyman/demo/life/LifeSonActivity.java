package com.hyman.demo.life;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.hyman.demo.R;

public class LifeSonActivity extends AppCompatActivity {

    public LifeSonActivity(){
        Log.i("LIVE", "LiveSonActivity");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_son);
    }
}