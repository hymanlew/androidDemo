package com.hyman.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.hyman.demo.common.Const;

public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(Const.EXTRA_MESSAGE);

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.displayId);
        textView.setText(message);
    }

    /**
     * 关闭页面时，调用回调方法
     */
    public void backMessage(View v){
        Intent data = new Intent();
        data.putExtra(Const.EXTRA_RESULT, "回调返回的信息");
        setResult(3, data);
        finish();
    }
}