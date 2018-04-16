package com.example.luoshuimumu.TopNews.gank;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.luoshuimumu.TopNews.AtyWebviewBinding;
import com.example.luoshuimumu.TopNews.R;
import com.example.luoshuimumu.TopNews.utils.LogUtil;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

/**
 * Created by luoshuimumu on 2018/2/7.
 */

public class WebviewActivity extends RxAppCompatActivity {
    private final String TAG = this.getClass().getName();
    private AtyWebviewBinding mBinding;
    WebView mWebview;
    String mUri;
    ObservableField<String> mTitile = new ObservableField();
    ObservableField<Integer> mProgress = new ObservableField(0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.aty_webview);
        initTitleBar();

        try {
            mUri = getIntent().getData().toString();
        } catch (Exception e) {
            //TODO 展示错误页并返回
            return;
        }

        mWebview = findViewById(R.id.aty_webview_webview);
        initWebview();

        mWebview.loadUrl(mUri);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (null != intent.getData()) {
            mUri = intent.getData().toString();
        }
        mWebview.loadUrl(mUri);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mWebview.canGoBack()) {
            mWebview.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void initTitleBar() {
        mBinding.setTitle(mTitile.get());
        mBinding.setProgress(mProgress.get());
        mBinding.setReturnListener((view) -> {
            finish();
        });
    }

    private void initWebview() {
        WebSettings settings = mWebview.getSettings();
        settings.setJavaScriptEnabled(true);//js调用
        settings.setBuiltInZoomControls(true);//画面缩放
        settings.setSupportZoom(true);
//        settings.setDisplayZoomControls(false);

        WebChromeClient chromeClient = new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                //TODO 进度条
                mProgress.set(newProgress);

            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                mTitile.set(title);
            }
        };
        mWebview.setWebChromeClient(chromeClient);

        WebViewClient webviewClient = new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                    view.loadUrl(request.getUrl().toString());
//                }
                return true;
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                    if (404 == error.getErrorCode()) {
                    LogUtil.e(TAG,
                            "load " + request.getUrl().toString() + "error,code:" + error.getErrorCode() + ":" + error.getDescription());
//                    }
                } else {
                    LogUtil.e(TAG, error.toString());
                }
            }
        };
        mWebview.setWebViewClient(webviewClient);
    }
}
