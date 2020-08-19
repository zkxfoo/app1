package com.example.myapplication;
//
import androidx.appcompat.app.AppCompatActivity;

import android.net.http.SslError;
import android.os.Bundle;
import android.view.Window;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;






public class MainActivity extends AppCompatActivity {
    private WebView webView;
    @Override
    public void onSaveInstanceState(Bundle outState){    super.onSaveInstanceState(outState);    webView.saveState(outState);}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        webView = (WebView) findViewById(R.id.webView); //获得web控件
        if (savedInstanceState != null) {    webView.restoreState(savedInstanceState);} else { webView.loadUrl("https://192.168.1.243/web/login");}


//      //系统默认会通过手机浏览器打开网页，为了能够直接通过WebView显示网页，则必须设置
        webView.setWebViewClient(new WebViewClient() {
            // 当点击链接时,覆盖窗口
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                view.loadUrl(url);// 加载url
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed(); //忽略SSL证书错误，继续加载页面proceed( )
            }
        });

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);// 启用JS脚本
        webSettings.setDomStorageEnabled(true); // 启用localstorage
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); // 允计js 自动打开窗口
        //支持缓存
        String appCachePath = getApplicationContext().getCacheDir().getAbsolutePath();
        webSettings.setAppCachePath(appCachePath);
        webSettings.setAppCacheEnabled(true);
        // 支自适应屏目
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
//        webSettings.setSavePassword(true);
        webView.setWebChromeClient(new WebChromeClient() {
            // 当WebView进度改变时更新窗口进度
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
            }
        });





    }


}




