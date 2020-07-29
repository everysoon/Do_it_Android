package com.example.study52_drawgraph;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    ProgressBar progressBar;
    ProgressBar progressBar2;
    ProgressBar progressBar3;
    Animation grow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        progressBar2=(ProgressBar)findViewById(R.id.progressBar2);
        progressBar3=(ProgressBar)findViewById(R.id.progressBar3);

        grow= AnimationUtils.loadAnimation(this,R.anim.grow);
        progressBar.setAnimation(grow);
        progressBar2.setAnimation(grow);
        progressBar3.setAnimation(grow);
/*  버튼눌렀을때 애니메이션 시작할수도있고 화면떴을때 시작하고싶으면 onWindowFocusChanged메소드 정의
        Button button =(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grow.start();
            }
        });*/


    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            grow.start();
        }else{
            grow.reset();
        }
    }

}
