package com.hyman.demo.live;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.hyman.demo.R;

public class LiveSonActivity extends AppCompatActivity {

    public LiveSonActivity(){
        Log.i("LIVE", "LiveSonActivity");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_son);
    }
}