package com.hyman.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 设置多按钮同时监听某一事件
 */
public class OnclickListennerActivity extends AppCompatActivity implements View.OnClickListener {

    private Button callbackButton;
    private Button normalButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onclick_listener);

        callbackButton = findViewById(R.id.callbackButton);
        normalButton = findViewById(R.id.normalButton);

        callbackButton.setOnClickListener(this);
        normalButton.setOnClickListener(this);
    }

    public void sendMessage(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        startActivity(intent);
    }

    /**
     * v 就是操作的当前视图中的对象
     * @param v
     */
    @Override
    public void onClick(View v) {
        if(v == callbackButton){
            Toast.makeText(this, "回调按钮~~~", Toast.LENGTH_SHORT).show();
        }
        if(v == normalButton){
            Toast.makeText(this, "跳转按钮~~~", Toast.LENGTH_SHORT).show();
        }
    }
}