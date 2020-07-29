package com.example.study08_framelayout;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView pig;
    ImageView daruma;
    int index=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         pig =(ImageView)findViewById(R.id.pig);
         daruma=(ImageView)findViewById(R.id.daruma);
    }
    public void changeImage(View v){
        index+=1;
        if(index>1){ index=0;}
        if(index==0){
            pig.setVisibility(View.VISIBLE);
            daruma.setVisibility(View.INVISIBLE);
        }
        else if(index==1){
            pig.setVisibility(View.INVISIBLE);
            daruma.setVisibility(View.VISIBLE);
        }
    }
}
