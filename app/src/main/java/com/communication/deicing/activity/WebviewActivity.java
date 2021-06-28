package com.communication.deicing.activity;

import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.communication.deicing.R;
import com.communication.deicing.base.BaseActivity;
import com.communication.deicing.base.BasePresenter;
import com.communication.deicing.util.AndroidJavascriptInterface;


/**
 * webview
 */
public class WebviewActivity extends BaseActivity {
    private WebView webView;


    @Override
    public int getLayoutId() {
        return R.layout.activity_webview;
    }

    @Override
    public void initView() {

        String url = "http://www.baidu.com";

        Log.i("OkGo", "" + url);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.removeSessionCookies(null);
        cookieManager.flush();
        cookieManager.setAcceptCookie(true);
        cookieManager.setCookie(url, "");

        webView = findViewById(R.id.wv);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webView.addJavascriptInterface(new AndroidJavascriptInterface(this), "Android");
        webView.setWebViewClient(new WebViewClient(){

            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                view.setVisibility(View.GONE);
                finish();
            }
        });
        webView.loadUrl(url);

    }

    public WebView getWebView() {
        return webView;
    }

    @Override
    public void onBackPressed() {

        if (webView != null) {

            if (webView.canGoBack()) {
                webView.goBack();
            } else {
                setResult(RESULT_OK);
                super.onBackPressed();
            }
        }
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
}
