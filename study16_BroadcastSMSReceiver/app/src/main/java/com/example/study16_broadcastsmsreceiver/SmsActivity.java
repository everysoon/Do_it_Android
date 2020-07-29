package com.example.study16_broadcastsmsreceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SmsActivity extends AppCompatActivity {
    EditText editText;
    EditText time;
    EditText content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        editText=(EditText)findViewById(R.id.editText);
        time=(EditText)findViewById(R.id.time);
        content=(EditText)findViewById(R.id.context);
        Button button=(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Intent pass=getIntent();
        processCommand(pass);

    }

    @Override
    protected void onNewIntent(Intent intent) {
        processCommand(intent);
        super.onNewIntent(intent);
    }

    private void processCommand(Intent pass) {
        if(pass!=null){
         String sender= pass.getStringExtra("sender");
         String contents= pass.getStringExtra("contents");
         String receivedDate= pass.getStringExtra("receivedDate");

            editText.setText(sender);
         time.setText(receivedDate);
         content.setText(contents);

        }
    }
}
