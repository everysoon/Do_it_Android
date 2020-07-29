package com.example.study59_multimedia_audiorecord;
import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.study59_multimedia_audiorecord.R;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    public static String url ="http://sites.google.com/site/ubiaccessmobile/sample_audio.amr";
    MediaRecorder recorder;
    MediaPlayer player;
    int position=0;
    String filename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       File sdcard=Environment.getExternalStorageDirectory();
       File file=new File(sdcard,"recorded.mp4");
       filename=file.getAbsolutePath();
        Log.d("MainActivity","저장할 파일명:"+filename);

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

        Button button5 =(Button)findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recordAudio();
            }
        });

        Button button6=(Button)findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopRecording();
            }
        });
    }
    public void stopRecording(){
        if(recorder!=null){
            recorder.stop();
            recorder.release();
            recorder=null;
            Toast.makeText(this,"녹음 중지됨 ",Toast.LENGTH_SHORT).show();
        }
    }
    public void recordAudio() {
        recorder = new MediaRecorder();
        if (getPackageManager().hasSystemFeature((PackageManager.FEATURE_MICROPHONE))) {
            Toast.makeText(this,"마이크있음..",Toast.LENGTH_SHORT).show();
            recorder.setAudioSource(MediaRecorder.AudioSource.MIC);//어디에서 음성데이터 받을건지
            recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);//압축형식 설정
            recorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);

            recorder.setOutputFile(filename);

            try {
                recorder.prepare();
                recorder.start();
                Toast.makeText(this, "녹음 시작됨 ", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else Toast.makeText(this,"마이크없음...",Toast.LENGTH_SHORT).show();
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
            player.setDataSource(filename);
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
