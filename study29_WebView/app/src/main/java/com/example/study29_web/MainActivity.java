package com.example.study29_web;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    WebView webview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         webview =(WebView)findViewById(R.id.web);
        WebSettings settings=webview.getSettings();//웹 설정
        settings.setJavaScriptEnabled(true); //자바스크립트쓰겠다

        Button button =(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webview.loadUrl("file:///android_asset/sample.html");
            }
        });


    }
}
