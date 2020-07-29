package com.example.study46_thread;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    Handler handler = new Handler();

    //ValueHandler valueHandler=new ValueHandler();
    //int value =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        Button button1 = (Button) findViewById(R.id.button);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // BackgroundThread thread=new BackgroundThread();
                // thread.start();

                new Thread(new Runnable() {

                    boolean running = true;
                    int value = 0;

                    @Override
                    public void run() {

                        while (running) {
                            value += 1;

                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    textView.setText("현재값:" + value);
                                }
                            });
                            try {
                                Thread.sleep(1000);
                            } catch (Exception e) {

                            }
                        }
                    }
                }).start();
            }
        });


        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}

   /* class BackgroundThread extends Thread{
   int value=0;
   boolean running=true;
        public void run(){
        while(running){
        value+=1;
                Message message=valueHandler.obtainMessage();
                Bundle bundle=new Bundle();
                bundle.putInt("value",value);
                message.setData(bundle);
                valueHandler.sendMessage(message);
        try{
        Thread.sleep(1000);
        }catch(Exception e){}
}
}
            }
        }
    }*/
   /* class ValueHandler extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

           Bundle bundle= msg.getData();
           int value=bundle.getInt("value");
             textView.setText("현재값:"+value);
        }
    }
}
    */
