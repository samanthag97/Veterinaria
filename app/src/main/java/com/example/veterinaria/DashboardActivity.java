package com.example.veterinaria;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.veterinaria.databinding.ActivityDashboardBinding;

public class DashboardActivity extends DrawerBaseActivity {

    ActivityDashboardBinding activityDashboardBinding;

    //piccolo escamotage per vedere cliniche in zona
    String url = "https://www.google.it/maps/search/veterinary/@45.9845092,13.4187732,10z";

    //enci not working
    //"https://www.enci.it/enci/news";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityDashboardBinding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(activityDashboardBinding.getRoot());
        allocateActivityTitle(getString(R.string.dashboard));

        WebView webView = (WebView) findViewById(R.id.web_view_dashboard);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient());

    }


}