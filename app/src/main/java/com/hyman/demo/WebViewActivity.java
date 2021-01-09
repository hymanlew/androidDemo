package com.hyman.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * WebView 是 Android 系统中的原生控件，其主要功能与前端页面进行响应交互，快捷省时地实现如期的功能，相当于
 * 增强版的内置浏览器。
 * WebView 内部实现是采用渲染引擎（WebKit）来展示 view 的内容，提供网页前进后退、网页放大、缩小、搜索等功能。
 * 是一个基于WebKit引擎、展现Web页面的控件，Android的WebView在低版本和高版本采用了不同的WebKit版本内核。
 *
 * 使用时需要在配置文件里设置网络权限，定义布局大小和样式，绑定和操作控件。
 */
public class WebViewActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        // 该创建方式，可以避免 WebView 内存泄露。
        // 不要在 xml 布局文件中 getApplicationgContext() 定义 Webview 控件，而是在需要的时候在 Activity
        // 中直接创建，并且推荐使用 Context 上下文对象。
        webView = (WebView) findViewById(R.id.webpage);

        String fileUrl = "file:///android_asset/XX.html";
        String netUrl = "http://www.baidu.com/";
        webView.loadUrl(netUrl);

        // 如果只是使用内嵌的 H5 页面，则就不需要配置 webview，因为它是与本应用页面进行交互的
        // webSetting(webView);

        webView.setWebChromeClient(new WebChromeClient(){

            // 获得网页的加载进度并显示
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                // 这里是设置 activity 的标题， 也可以根据自己的需求做一些其他的操作
                if(newProgress == 100){
                    Log.i("INFO","加载完成");
                }else{
                    Log.i("INFO","加载中......");
                }
            }

            // 获取Web页中的标题
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
            }
        });

        // WebView 默认会调用系统浏览器加载 url，通过重写该方法，实现在当前应用内完成页面加载。
        webView.setWebViewClient(new WebViewClient(){

            /**
             * 当前界面是安卓原生加载的 webview 界面，点击返回之后是仍然在当前界面对 url 进行替换，即是在
             * 不停的刷新当前主界面，怎么都返不回。原因是因为默认这个界面有重定向，那么如何判断是否重定向呢：
             *
             * WebView 有一个 getHitTestResult() 返回的是一个 HitTestResult，一般会根据打开的链接的类型，
             * 返回一个 extra 的信息，如果打开链接不是一个url，或者打开的链接是 JavaScript的 url，他的类型
             * 是 UNKNOWN_TYPE，这个 url 就会通过 requestFocusNodeHref(Message) 异步重定向。返回的 extra
             * 为 null，或者没有返回 extra。根据此方法的返回值，判断是否为null，可以用于解决网页重定向问题。
             */
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                WebView.HitTestResult result = view.getHitTestResult();

                /**
                 * 当返回值不为 null 时，则代表 url 跳转是在 webview 内部执行，需要重定向。返回 false。
                 * 当返回值为 null 时，则代表 url 跳转是根据程序来执行的，即不进行重定向。返回 true。
                 */
                if(result != null){
                    return false;
                }else {
                    /**
                     * 注意：本方法里面就不要再写 view.loadUrl(url) 了，因为初始化的时候肯定已经 load 过了，
                     * 它默认引用你传的那个 url。
                     */
                    view.loadUrl(url);
                    return true;
                }
            }

            // 开始载入页面时调用此方法，在这里可以设定一个 loading 的页面，告诉用户程序正在等待网络响应
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            // 在页面加载结束时调用。我们可以关闭 loading 条，切换程序动作
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            // 在加载页面资源时会调用，每一个资源（比如图片）的加载都会调用一次
            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
            }

            // 加载页面的服务器出现错误时（如404）调用
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                if (errorCode == 404) {
                    view.loadUrl("file:///android_assets/error_handle.html");
                }
            }

            // 重写此方法让 webview 处理 https 请求，webView 默认是不处理 https 请求的，页面显示空白
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                // 表示等待证书响应
                handler.proceed();

                // 表示挂起连接，为默认方式
                // handler.cancel();

                //可做其他处理
                // handler.handleMessage(null);
            }
        });

        // WebView监听网页内部返回键 实现前进、后退、与刷新功能
        webView.setOnKeyListener((v, keyCode, event) -> {
            if (event.getAction() == KeyEvent.ACTION_DOWN) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {

                    // 返回时判断是否能返回上个 url，不能就直接 finish 掉这个界面
                    if (webView.canGoBack()) {
                        webView.goBack();
                        return true;
                    } else {
                        finish();
                    }
                }
            }
            return false;
        });
    }

    @Override
    protected void onDestroy() {
        /**
         * 销毁 Webview：在关闭 Activity 时，如果 Webview 的音乐或视频，还在播放，就必须销毁 Webview。
         *
         * 但要注意 webview 调用 destory 时，它自己仍是绑定在 Activity 上的。这是由于自定义 webview 构建时
         * 传入了该 Activity 的 context 对象。因此需要先从父容器中移除 webview，然后再销毁 webview。
         *
         * 先让 WebView 加载 null 内容，然后移除 WebView，再销毁 WebView，最后把 WebView 设置为 null。
         */
        if (webView != null) {
            webView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            webView.clearHistory();

            ((ViewGroup)webView.getParent()).removeView(webView);
            webView.destroy();
            webView = null;
        }
        super.onDestroy();
    }

    private void webMethod(WebView webView){

        //激活 WebView 为活跃状态，能正常执行网页的响应
        webView.onResume();

        // 当页面被失去焦点被切换到后台不可见状态，需要执行 onPause()，通过 onPause() 动作通知内核暂停所有
        // 的动作，比如 DOM 的解析、JavaScript 执行等
        webView.onPause();

        // 当应用程序（存在 webview）被切换到后台时，这个方法不仅仅针对当前的 webview 而是全局的全应用程序
        // 的 webview。它会暂停所有 webview 的布局显示、解析、延时，从而降低 CPU 功耗。
        webView.pauseTimers();

        // 恢复 pauseTimers 状态
        webView.resumeTimers();


        // 清除网页访问留下的缓存，由于内核缓存是全局的，因此这个方法不仅仅针对 webview 而是针对整个应用程序
        webView.clearCache(true);

        // 清除当前 webview 访问的历史记录，只会清除访问历史记录里除了当前访问记录的所有记录
        webView.clearHistory();

        // 这个 api 仅仅清除自动完成填充的表单数据，并不会清除 WebView 存储到本地的数据
        webView.clearFormData();


        // 判断网页是否可以回退
        boolean back = webView.canGoBack();
        // 回退一页
        webView.goBack();

        // 判断网页是否可以前进
        boolean forward = webView.canGoForward();
        // 前进一页
        webView.goForward();

        // 正数为前进
        webView.goBackOrForward(1);
        // 负数为后退
        webView.goBackOrForward(-1);

        // 刷新
        webView.reload();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void webSetting(WebView webView){
        WebSettings webSettings = webView.getSettings();
        webSettings.setAllowContentAccess(true);

        // 是否开启JS支持
        webSettings.setJavaScriptEnabled(true);
        // 是否允许JS打开新窗口
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);

        /**
         * 优先使用缓存，缓存模式如下：
         * LOAD_CACHE_ONLY: 不使用网络，只读取本地缓存数据
         * LOAD_DEFAULT: （默认）根据cache-control决定是否从网络上取数据。
         * LOAD_NO_CACHE: 不使用缓存，只从网络获取数据。
         * LOAD_CACHE_ELSE_NETWORK，只要本地有，无论是否过期，或者no-cache，都使用缓存中的数据
         */
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

        // 设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true);
        // 缩放至屏幕大小
        webSettings.setLoadWithOverviewMode(true);
        // 是否支持缩放
        webSettings.setSupportZoom(true);
        // 设置内置的缩放控件，是否支持缩放变焦，前提是支持缩放
        webSettings.setBuiltInZoomControls(true);
        // 是否隐藏原生的缩放控件
        webSettings.setDisplayZoomControls(false);

        // 是否允许访问文件
        webSettings.setAllowFileAccess(true);
        // 是否节点缓存
        webSettings.setDomStorageEnabled(true);
        // 是否数据缓存
        webSettings.setDatabaseEnabled(true);

        // 设置字体库格式
        webSettings.setStandardFontFamily("sans-serif");
        webSettings.setFixedFontFamily("monospace");
        webSettings.setSansSerifFontFamily("sans-serif");
        webSettings.setSerifFontFamily("sans-serif");
        webSettings.setCursiveFontFamily("cursive");
        webSettings.setFantasyFontFamily("fantasy");

        // 设置文本缩放的百分比
        webSettings.setTextZoom(100);
        // 设置文本字体的最小值(1~72)
        webSettings.setMinimumFontSize(8);
        // 设置文本字体默认的大小
        webSettings.setDefaultFontSize(16);

        // 按规则重新布局
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        // 是否自动加载图片
        webSettings.setLoadsImagesAutomatically(false);
        // 设置编码格式
        webSettings.setDefaultTextEncodingName("UTF-8");
        // 是否需要获取焦点
        webSettings.setNeedInitialFocus(true);
        // 设置开启定位功能
        webSettings.setGeolocationEnabled(false);
        // 是否从网络获取资源
        webSettings.setBlockNetworkLoads(false);
    }
}