package com.example.study35_picker;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;

public class DateTimePicker extends LinearLayout {
    DatePicker datePicker;
    TimePicker timePicker;
    CheckBox checkBox;
    onDateTimeChangeListener listener;

    public DateTimePicker(Context context) {
        super(context);
        init(context);
    }

    public DateTimePicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    private void init(Context context){
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.picker,this,true);

       datePicker=(DatePicker)findViewById(R.id.date);
       timePicker=(TimePicker)findViewById(R.id.time);
        checkBox=(CheckBox)findViewById(R.id.checkTimePicker);
        Date date=new Date();
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);

        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH);
        int day=calendar.get(Calendar.DAY_OF_MONTH);
        int hour=calendar.get(Calendar.HOUR_OF_DAY);
        int minute=calendar.get(Calendar.MINUTE);

       datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
           @Override
           public void onDateChanged(DatePicker datePicker, int year, int month, int day) {
                //날짜 변경됬을때 호출되는 메소드
               if(listener!=null){
                   listener.onDateTimeChange(DateTimePicker.this,year,month,day,timePicker.getCurrentHour(),timePicker.getCurrentMinute());
               }
           }
       });

       timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
           @Override
           public void onTimeChanged(TimePicker timePicker, int hour, int minute) {
               if(listener!=null){
                   listener.onDateTimeChange(DateTimePicker.this,datePicker.getYear(),datePicker.getMonth(),datePicker.getDayOfMonth(),hour,minute);
               }
           }
       });
       checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
               timePicker.setEnabled(isChecked);
               timePicker.setVisibility(checkBox.isChecked()? View.VISIBLE:View.INVISIBLE);
           }
       });
    }
    public void setOnDateTimeChangeListener(onDateTimeChangeListener listener)
    {
        this.listener=listener;
    }
    public void update(int year,int month,int day,int hour,int minute){
        datePicker.updateDate(year,month,day);
        timePicker.setCurrentHour(hour);
        timePicker.setCurrentMinute(minute);
    }
}
