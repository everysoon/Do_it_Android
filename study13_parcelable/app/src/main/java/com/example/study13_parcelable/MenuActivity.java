package com.example.study13_parcelable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        button=(Button)findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Intent passed =getIntent();
        processIntent(passed);
    }

    private void processIntent(Intent intent) {
        if(intent!=null){
            ArrayList<String> names =(ArrayList<String>)intent.getSerializableExtra("names");
            if(names!=null){
                Toast.makeText(getApplicationContext(),"전달받은 이름 리스트개수:"+names.size(),Toast.LENGTH_LONG).show();
            }
            SimpleData data =(SimpleData)intent.getParcelableExtra("data");
            if(data !=null){
                Toast.makeText(getApplicationContext(),"전달받은 SimpleData Message:"+data.message,Toast.LENGTH_LONG).show();
            }
        }
    }
}
