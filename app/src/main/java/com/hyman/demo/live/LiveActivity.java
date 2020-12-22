package com.hyman.demo.live;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.hyman.demo.R;

/**
 * 测试 Activity 的生命周期，观察日志
 *
 *  1）界面从“死亡”-->“运行"（正常启动时的流程）
 *     创建对象-->onCreate()-->onStart()-->onResume()---可见可操作(运行状态)
 *
 * 2) 界面从“运行”-->“死亡"（返回键退出当前页面，或是直接退出当前应用返回到主屏幕）
 *     onPause()-->onStop()-->onDestroy()-->Activity对象成为垃圾对象---不可见也不存在死亡状态)
 *
 * 3) 界面从“运行”-->“停止"（直接按 HOME 键回到主屏幕）
 *     onPause()-->onStop()---不可见但存在
 *
 * 4) 界面从“停止” -->“运行"（选择并重新进入本应用）
 *     onRestart()-->onStart()-->onResume()
 *
 * 5) 界面从“运行”-->“暂停"
 *     onPause()
 *
 * 6) 界面从“暂停” -->“运行"
 *    onResume()
 *
 * 重要的:
 * 1. onCreate(): 在 Activity 对象创建后调用, 只执行一次
 * 2. onDestroy(): 在 Activity 死亡之前调用, 只执行一次
 * 3. onResume(): 界面只有经历此方法才能可见可操作
 */
public class LiveActivity extends AppCompatActivity {

    public LiveActivity(){
        Log.i("LIVE", "LiveActivity");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("LIVE", "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live);
    }

    @Override
    protected void onStart() {
        Log.i("LIVE", "onStart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.i("LIVE", "onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.i("LIVE", "onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.i("LIVE", "onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.i("LIVE", "onDestroy");
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        Log.i("LIVE", "onRestart");
        super.onRestart();
    }

    public void liveButton(View view){
        startActivity(new Intent(this, LiveSonActivity.class));
    }
}