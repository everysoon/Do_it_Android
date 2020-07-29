package com.example.mission3_1;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView moon1;
    ImageView moon2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        moon1=(ImageView)findViewById(R.id.yelowmoon);
        moon2=(ImageView)findViewById(R.id.whitemoon);
    }
    public void upClicked(View v){
       moon1.setImageResource(R.drawable.whitemoon);
       moon2.setImageResource(R.drawable.yelowmoon);
    }
    public void downClicked(View v){
        moon1.setImageResource(R.drawable.yelowmoon);
        moon2.setImageResource(R.drawable.whitemoon);
    }
}
