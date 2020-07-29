package com.example.study47_progressbar_delay;
//프로그래스바 5초뒤에 실행되게!
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    ProgressBar progressBar;

    Handler handler=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        Button button=(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ProgressThread thread=new ProgressThread();
                        thread.start();
                    }
                },5000);
            }
        });
    }
    class ProgressThread extends Thread{
        int value;
        public void run(){
            while(true){
                if(value>100)break;

            value+=1;
        handler.post(new Runnable() {
            @Override
            public void run() {
                progressBar.setProgress(value);
            }
        });
        try{
            Thread.sleep(500);
        }catch (Exception e){}
        }

        }
    }
}
