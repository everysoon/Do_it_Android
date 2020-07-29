package com.example.study09_basicwidget;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RadioButton r=(RadioButton)findViewById(R.id.radioButton);
        boolean checked=r.isChecked();


    }
}
