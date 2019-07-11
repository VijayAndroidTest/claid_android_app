package com.example.claid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class three_d_body extends AppCompatActivity {
 WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_three_d_body);
        webView=findViewById(R.id.webview);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://ozosmatrix.com/3d-modeling/");
        webView.setHorizontalScrollBarEnabled(false);

    }
}
