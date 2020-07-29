package com.example.study14_lifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this,"onCreate 호출됨",Toast.LENGTH_LONG).show();

        Button button =(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    //주로 사용되는 수명주기 메소드
    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this,"onStart 호출됨",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this,"onStop 호출됨",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this,"onDestroy 호출됨",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPause() { //필요한데이터 저장
        super.onPause();
        Toast.makeText(this,"onPause 호출됨",Toast.LENGTH_LONG).show();
        //쉽게 데이터 저장하기
       SharedPreferences pref= getSharedPreferences("pref", Activity.MODE_PRIVATE);
       SharedPreferences.Editor editor =pref.edit();
       editor.putString("name","minsun");
       editor.commit(); //commit해줘야 저장돼!

    }

    @Override
    protected void onResume() { //복구
        super.onResume();
        Toast.makeText(this,"onResume 호출됨",Toast.LENGTH_LONG).show();

        SharedPreferences pref= getSharedPreferences("pref", Activity.MODE_PRIVATE);
        if(pref !=null){
           String name= pref.getString("name","");
           Toast.makeText(this,"복구된이름:"+name,Toast.LENGTH_LONG).show();
        }
    }
    //onCreate ->onStart->onResume ->화면닫으면 onPause(일시정지)->onStop(중지)->onDestroy (소멸) 순으로 호출!
}
