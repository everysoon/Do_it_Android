package com.example.study49_asynctask_progressbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    ProgressBar progressBar;
   // CompletionThread comthread;
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
                progressTask task=new progressTask();
                task.execute("시작!");
            }});



    }
    class progressTask extends AsyncTask<String ,Integer ,Integer>{
            //AsyncTask의 파라메터인 String ,Integer,Integer는
        //  순서대로 doInBackground-> String
        //onProgressUpdate->Integer
        //onPostExecute->Integer !

        int value=0;
        @Override
        protected Integer doInBackground(String... strings) {
            //스레드 안에 넣는 코드 넣어주면돼 ->자동 호출됨
            //어떤값을 리턴하냐에따라 onPostExcute에 전달됨
            //업데이트하고싶을때 onProgressUpdate 내가 원할때 불러주고싶으면 publishProgress 호출해줌돼
            while (true) {
                if (value > 100) break;

                value += 1;
                publishProgress(value);
                try {
                    Thread.sleep(200);
                } catch (Exception e) {
                }

            }
            return value;
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            //UI업데이트 하고싶은 코드!
            super.onProgressUpdate(values);

            progressBar.setProgress(values[0].intValue());
            //핸들러에서 실행하듯이 UI접근해도 문제없어짐
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);

            Toast.makeText(getApplicationContext(),"완료됨!",Toast.LENGTH_SHORT).show();
        }


    }

}
