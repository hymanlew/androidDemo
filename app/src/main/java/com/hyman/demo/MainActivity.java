package com.hyman.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.logging.Logger;

/**
 * 主界面类，启动页面
 */
public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.hyman.demo.MESSAGE";

    /**
     * R.java 类对应了 res 整个文件夹目录
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 监控下载事件
        download();
    }

    /**
     * Called when the user taps the Send button
     *
     * Intent 构造函数会获取两个参数：Context 和 Class。因为 Activity 类是 Context 的子类。
     * 在本例中，系统将 Intent 传递到的应用组件的 Class Activity 并启动。
     *
     * putExtra() 方法将 EditText 的值添加到 Intent。Intent 能够以名为 “extra” 的键值对形式携带数据类型。
     * 键是公共常量 EXTRA_MESSAGE，因为下一个 Activity 将使用该键检索文本值。为 Intent extra 定义键时，最
     * 好使用应用的软件包名称作为前缀。这样可以确保这些键是独一无二的，万一您的应用需要与其他应用进行交互。
     *
     * startActivity() 方法将启动一个由 Intent 指定的 DisplayMessageActivity 实例。
     *
     * 注意：
     * 也可以通过导航架构组件，使用导航编辑器将一个 Activity 与另一个 Activity 相关联。然后利用 API触发关联
     * 的操作（例如用户点击某个按钮时）时启动第二个 Activity。如需了解详情，请参阅[导航]（需要额外导入包）
     * (https://developer.android.google.cn/topic/libraries/architecture/navigation)。
     */
    public void sendMessage(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.textValue);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void download() {
        Button dbutton = (Button) findViewById(R.id.downloadButton);

        dbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /**
                 * 创建显示短时间提示文本的类：
                 * 1，获取到外部类的当前对象：外部类.this
                 *
                 * 2，第三个参数，只有两个值可选择：
                 * LENGTH_SHORT，短时间显示
                 * LENGTH_LONG，长时间显示
                 */
                Toast toast = Toast.makeText(MainActivity.this, "开始下载了~~~", Toast.LENGTH_SHORT);
                toast.show();
                dbutton.setText("正在下载中。。");
            }
        });

        /**
         * 比较 Log 与 System.out
         * - Log 提供了多个级别的打印输出方法，在 LogCat 窗口中显示的颜色不同。
         * - Log 打印时必须指定 TAG（并且 TAG 可以自定义），在 LogCat 中可以通过添加 TAG 过滤查看。
         * - LogCat 中可以通过两种方式过滤
         *   - TAG名：显示所有此标签名的输出
         *   - 应用包名：显示指定包名应用的所有输出
         */
        Log.i("INFO","输出 Info 信息");
        Log.e("ERROR", "输出 ERROR 信息");
    }
}