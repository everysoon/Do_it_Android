package com.example.study13_parcelable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getApplicationContext(),MenuActivity.class);

                ArrayList<String> names=new ArrayList<String>();

                names.add("박민선");
                names.add("박상준");

                intent.putExtra("names",names);

                //객체를 넣을수없는데 parcel로 구현한 객체는 넣을수있음!
                SimpleData data =new SimpleData(100,"Hello");
                intent.putExtra("data",data);

            startActivityForResult(intent,101);
            }
        });

    }
}
