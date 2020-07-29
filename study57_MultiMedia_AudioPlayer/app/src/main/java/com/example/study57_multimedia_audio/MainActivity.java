package com.example.study57_multimedia_audio;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    public static String url ="http://sites.google.com/site/ubiaccessmobile/sample_audio.amr";

    MediaPlayer player;
    int position=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button =(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            playerAudio();
            }
        });


        Button button2 =(Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pauseAudio();
            }
        });


        Button button3 =(Button)findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resumeAudio();
            }
        });


        Button button4 =(Button)findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAudio();
            }
        });
    }
    public void stopAudio(){
        if(player!=null&&player.isPlaying()){
            player.stop();
            Toast.makeText(this,"중지됨 ",Toast.LENGTH_SHORT).show();
        }
    }
    public void resumeAudio(){
        if(player!=null&&!player.isPlaying()){
            player.seekTo(position);
            player.start();
            Toast.makeText(this,"재 시작됨 ",Toast.LENGTH_SHORT).show();
        }

    }
    public void pauseAudio(){
        if(player!=null){
            position=player.getCurrentPosition();
            player.pause();
            Toast.makeText(this,"일시 정지됨 ",Toast.LENGTH_SHORT).show();
        }
    }
    public void playerAudio(){

        try {
            closePlayer();

            player=new MediaPlayer();
            player.setDataSource(url);
            player.prepare();
            player.start();
            Toast.makeText(this,"재생 시작됨 ",Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void closePlayer(){
        if(player!=null){
            player.release();
            player=null;
        }
    }
}
