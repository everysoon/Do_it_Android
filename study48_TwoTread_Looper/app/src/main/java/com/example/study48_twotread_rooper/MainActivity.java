package com.example.study48_twotread_rooper;
//프로그래스바 5초뒤에 실행되게!
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.study48_twotread_rooper.R;

public class MainActivity extends AppCompatActivity {
    ProgressBar progressBar;
    CompletionThread comthread;
    Handler handler=new Handler();
    String msg="";
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
        comthread=new CompletionThread();
        comthread.start();

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
            comthread.completionHandler.post(new Runnable() {
                @Override
                public void run() {
                msg="OK!!!";
                Log.d("MainActivity","메세지:"+msg);
                }
            });

        }
    }

    class CompletionThread extends Thread{ //프로그레스바가 100퍼까지 찼을때 작동하는 스레드
        Handler completionHandler=new Handler();
        public void run(){
            Looper.prepare();
            Looper.loop(); //루퍼를 대기상태로 만들어놓기
        }
    }
}
