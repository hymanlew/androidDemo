package com.hyman.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
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
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
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