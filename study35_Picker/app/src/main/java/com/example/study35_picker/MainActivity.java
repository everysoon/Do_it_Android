package com.example.study35_picker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    DateTimePicker picker;
    SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView=(TextView)findViewById(R.id.textView);
        picker=(DateTimePicker) findViewById(R.id.picker);

        picker.setOnDateTimeChangeListener(new onDateTimeChangeListener() {
            @Override
            public void onDateTimeChange(DateTimePicker view, int year, int month, int day, int hour, int minute) {
               Calendar calendar= Calendar.getInstance();
               calendar.set(year,month,day,hour,minute);
               String curTime=format.format(calendar.getTime());
               
               textView.setText(curTime);
            }
        });

    }
}
