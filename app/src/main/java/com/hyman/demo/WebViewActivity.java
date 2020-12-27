package com.hyman.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        // String url = "http://uadminmdev1.niceloo.com/home";
        String url = "http://www.baidu.com/";
        WebView webView = (WebView) findViewById(R.id.webpage);
        webView.loadUrl(url);

        // 如果只是使用内嵌的 H5 页面，则就不需要配置 webview，因为它是与本应用页面进行交互的
        // webSetting(webView);

        // WebView 默认会调用系统浏览器加载 url，通过重写该方法，实现在当前应用内完成页面加载。
        webView.setWebViewClient(new WebViewClient(){

            /**
             * 当前界面呢是安卓原生加载的 webview 界面，点击返回之后仍然在当前界面对url进行替换，即是在不停
             * 的刷新当前主界面，怎么都返不回。
             * 原因是因为某个界面有重定向的话就会出现这种问题，那么首先我们该如何判断他是重定向界面呢：
             *
             * WebView 有一个 getHitTestResult():返回的是一个 HitTestResult，一般会根据打开的链接的类型，
             * 返回一个extra的信息，如果打开链接不是一个url，或者打开的链接是JavaScript的url，他的类型是
             * UNKNOWN_TYPE，这个url就会通过requestFocusNodeHref(Message)异步重定向。返回的extra为null，或者没有返回extra。根据此方法的返回值，判断是否为null，可以用于解决网页重定向问题。
             *
             *
             *
             *
             * 返回: return true;  webview处理url是根据程序来执行的。 
             *
             * 返回: return false; webview处理url是在webview内部执行。
             *
             * 那么如果是重定向的呢，我们就return false,不是重定向就return true
             *
             * 注意：这个shouldOverrideUrlLoading方法里面就不要再写view.loadUrl(url)了
             *
             * 因为你初始化的时候肯定已经load过了，然而这个会默认引用你传的那个url，
             *
             * 返回时判断是否能返回上个url，不能就直接finish掉这个界面，希望能帮助到大家
             *
             */
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                WebView.HitTestResult result = view.getHitTestResult();
                if(result != null){
                    return false;
                }else {
                    view.loadUrl(url);
                    return true;
                }
            }
        });

        // WebView监听网页内部返回键 实现前进、后退、与刷新功能
        webView.setOnKeyListener((v, keyCode, event) -> {
            if (event.getAction() == KeyEvent.ACTION_DOWN) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
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

    private void webMethod(WebView webView){
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
        webSettings.setAllowFileAccess(true);
        webSettings.setAllowContentAccess(true);

        // 是否开启JS支持
        webSettings.setJavaScriptEnabled(true);

        // 是否允许JS打开新窗口
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);

        // 缩放至屏幕大小
        webSettings.setUseWideViewPort(true);
        // 缩放至屏幕大小
        webSettings.setLoadWithOverviewMode(true);
        // 是否支持缩放
        webSettings.setSupportZoom(true);
        // 是否支持缩放变焦，前提是支持缩放
        webSettings.setBuiltInZoomControls(true);
        // 是否隐藏缩放控件
        webSettings.setDisplayZoomControls(false);

        // 是否允许访问文件
        webSettings.setAllowFileAccess(true);
        // 是否节点缓存
        webSettings.setDomStorageEnabled(true);
        // 是否数据缓存
        webSettings.setDatabaseEnabled(true);
        // 是否应用缓存
        webSettings.setAppCacheEnabled(true);

        // 设置缓存路径
        // webSettings.setAppCachePath(uri);

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