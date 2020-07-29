package com.example.study54_http_request;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    EditText editText;
    Handler handler=new Handler();
    String urlString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView=(TextView)findViewById(R.id.textView);
        editText=(EditText)findViewById(R.id.editText);
        Button button =(Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 urlString=editText.getText().toString();
              RequestThread thread=new RequestThread();
              thread.start();
            }
        });
    }

    public class RequestThread extends Thread{
        public void run(){


            try {
                URL url=new URL(urlString);
                HttpURLConnection conn=(HttpURLConnection)url.openConnection();
                if(conn!=null){
                    conn.setConnectTimeout(10000);
                    conn.setRequestMethod("GET");
                    conn.setDoInput(true);
                    conn.setDoOutput(true);

                    int resCode=conn.getResponseCode();
                    //bufferedReader는 한줄씩 읽어줌
                    BufferedReader reader=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String line=null;

                    while(true){
                        line=reader.readLine();
                        if(line==null)break;
                        println(line);
                    }
                    reader.close();
                    conn.disconnect();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
    public void println(final String data){
        handler.post(new Runnable() {
            @Override
            public void run() {
                textView.append(data+"\n"); //append는추가를해줌
            }
        });

    }
}
