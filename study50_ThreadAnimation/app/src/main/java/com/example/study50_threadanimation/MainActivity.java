package com.example.study50_threadanimation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Drawable> imageList=new ArrayList<>();
    Handler handler=new Handler();
    ImageView imageView;
    Drawable drawable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView=(ImageView)findViewById(R.id.imageView);

        Resources res= getResources();
        imageList.add(res.getDrawable(R.drawable.face1));
        imageList.add(res.getDrawable(R.drawable.face2));
        imageList.add(res.getDrawable(R.drawable.face3));
        imageList.add(res.getDrawable(R.drawable.face4));
        imageList.add(res.getDrawable(R.drawable.face5));

        Button button=(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    AnimThread thread=new AnimThread();
                    thread.start();
            }
        });

    }

    class AnimThread extends Thread{
        public void run(){
            int index=0;
            for(int i=0; i<100; i++){
                index=i%5;
               drawable=imageList.get(index);
            handler.post(new Runnable() {
                @Override
                public void run() {
                    imageView.setImageDrawable(drawable);
                }
            });

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
