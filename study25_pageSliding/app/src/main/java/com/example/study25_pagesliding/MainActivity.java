package com.example.study25_pagesliding;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    LinearLayout page;
    Animation left;
    Animation right;
    Button button;
    boolean isPageOpen=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        left= AnimationUtils.loadAnimation(this,R.anim.sliding_left);
        right=AnimationUtils.loadAnimation(this,R.anim.sliding_right);
        page=(LinearLayout)findViewById(R.id.page);
        button =(Button)findViewById(R.id.button);

        SlidingAnimationListener listener=new SlidingAnimationListener();
        left.setAnimationListener(listener);
        right.setAnimationListener(listener);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(isPageOpen){
                   page.startAnimation(right);
               }else
               {
                   page.setVisibility(View.VISIBLE);
                   page.startAnimation(left);
               }
            }
        });
    }
    class SlidingAnimationListener implements Animation.AnimationListener{

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            if(isPageOpen){
                page.setVisibility(View.INVISIBLE);
                button.setText("열기");
                isPageOpen=false;
            }
            else {
                button.setText("닫기");
                isPageOpen=true;
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}
