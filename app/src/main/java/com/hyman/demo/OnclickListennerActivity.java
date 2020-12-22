package com.hyman.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hyman.demo.common.Const;

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

    /**
     * v 就是操作的当前视图中的对象
     * @param v
     */
    @Override
    public void onClick(View v) {
        if(v == callbackButton){
            Toast.makeText(this, "回调按钮~~~", Toast.LENGTH_SHORT).show();
            // 带回调启动
            Intent intent = new Intent(this, DisplayMessageActivity.class);
            intent.putExtra(Const.EXTRA_MESSAGE,"回调信息");
            startActivityForResult(intent, 2);
        }
        if(v == normalButton){
            Toast.makeText(this, "跳转按钮~~~", Toast.LENGTH_SHORT).show();
            // 关闭当前页面，回到之前的一个页面
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2 && resultCode == 3) {
            String extra = data.getStringExtra(Const.EXTRA_RESULT);
            callbackButton.setText(extra);
        }
    }
}