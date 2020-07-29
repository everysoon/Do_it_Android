package com.example.study15_service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText et;

//액티비티간 인텐트를 이용해 실행시켜주고,
    //데이터를 전달하는 용도로 많이씀
    //이미 서비스가 실행되있는경우, 이미 액티비티가 실행되있는경우를 주의해야함!
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       et=(EditText)findViewById(R.id.editText);
        Button button=(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name= et.getText().toString();

                Intent intent=new Intent(getApplicationContext(),MyService.class);
                intent.putExtra("command","show");
                intent.putExtra("name",name);
                startService(intent);
            }
        });

    Intent passed=getIntent();
    processCommand(passed);
    }

    @Override //MainActivity가 만들어져있는 상황에는 oncreate 대신 이 메소드가 호출됨!
    protected void onNewIntent(Intent intent) {
        processCommand(intent);
        super.onNewIntent(intent);
    }

    private void processCommand(Intent intent) {
    if(intent!=null){
        String command =intent.getStringExtra("command");
        String name=intent.getStringExtra("name");

        Toast.makeText(this,"서비스로부터 전달받은 데이터 :"+command+","+name,Toast.LENGTH_LONG).show();
    }

    }

}
