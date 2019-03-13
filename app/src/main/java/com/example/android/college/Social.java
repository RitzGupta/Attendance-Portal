package com.example.android.college;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Social extends AppCompatActivity {

    WebView myPersonalSite;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social);

        url ="https://twitter.com/ABESEC032";
        myPersonalSite =(WebView)findViewById(R.id.mypersonalsite);
        myPersonalSite.getSettings().setJavaScriptEnabled(true);
        myPersonalSite.loadUrl(url);
        myPersonalSite.setWebViewClient(new WebViewClient());
    }
}
