package com.example.study02;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButtonClicked(View v){
        Toast.makeText(getApplicationContext(), "안뇽하세욤", Toast.LENGTH_LONG).show();
    }
    public void VisibleWeb(View v){
        Intent intent =new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.naver.com"));
        startActivity(intent);
    }
    public void VisibleView(View v){
        Intent intent=new Intent(this,Menu.class);
        startActivity(intent);
    }
}
