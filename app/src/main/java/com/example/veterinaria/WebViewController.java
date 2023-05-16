package com.example.veterinaria;

import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewController extends WebViewClient {

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {

        view.loadUrl(url);
        return true;
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        //nasconde parti di website non volute
        view.loadUrl("javascript:document.getElementById('omnibox-container').style.display = 'none';");
    }


}
