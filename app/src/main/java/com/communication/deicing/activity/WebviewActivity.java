package com.communication.deicing.activity;

import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.communication.deicing.R;
import com.communication.deicing.base.BaseActivity;
import com.communication.deicing.base.BasePresenter;
import com.communication.deicing.util.AndroidJavascriptInterface;
import com.squareup.picasso.Picasso;

import butterknife.BindView;


/**
 * webview
 */
public class WebviewActivity extends BaseActivity {

    @BindView(R.id.iv_show_pic)
    ImageView ivShowPic;
    @BindView(R.id.wv)
    WebView webView;


    @Override
    public int getLayoutId() {
        return R.layout.activity_webview;
    }

    @Override
    public void initView() {

//        String url = "http://www.baidu.com";
        String url = "http://192.168.1.236:8080/liguo/02.docx";
//        String url = "http://192.168.1.236:8080/liguo/05.jpg";
//        String url = "http://192.168.1.236:8080/liguo/51.pdf";

        if (url.endsWith(".jpg") || url.endsWith(".png") || url.endsWith(".doc") || url.endsWith(".pdf")) {
//            ivShowPic.setLayerType(View.LAYER_TYPE_SOFTWARE,null);
            Picasso.get().load(url).into(ivShowPic);
        }else{

            Log.i("OkGo", "" + url);
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.removeSessionCookies(null);
            cookieManager.flush();
            cookieManager.setAcceptCookie(true);
            cookieManager.setCookie(url, "");

            WebSettings webSettings = webView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webSettings.setAllowFileAccess(true);
            webSettings.setLoadWithOverviewMode(true);
            webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
            webSettings.setSupportZoom(true); // 可以缩放
            webSettings.setBuiltInZoomControls(true); // 显示放大缩小
            webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
            webView.addJavascriptInterface(new AndroidJavascriptInterface(this), "Android");
            webView.setWebViewClient(new WebViewClient() {

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
            webView.setWebChromeClient(new WebChromeClient() {
                @Override
                public void onProgressChanged(WebView view, int newProgress) {

                }
            });
            webView.loadUrl(url);
        }

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
