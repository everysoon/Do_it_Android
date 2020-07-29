package com.example.study36_month_calender;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    TextView monthText;
    GridView gridView;
    MonthAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        monthText=(TextView)findViewById(R.id.textView2);
        Button right=(Button)findViewById(R.id.right);
        Button left=(Button)findViewById(R.id.left);
        gridView=(GridView)findViewById(R.id.monthView);

        adapter =new MonthAdapter();
        gridView.setAdapter(adapter);

        monthText.setText(adapter.getCurrentYear()+"년"+(adapter.getCurrentMonth()+1)+"월");

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            adapter.setNextMonth();
            adapter.notifyDataSetChanged(); //어뎁터 정보바뀐거 알려줘
            monthText.setText(adapter.getCurrentYear()+"년"+(adapter.getCurrentMonth()+1)+"월");
            }
        });
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.setPreviousMonth();
                adapter.notifyDataSetChanged();
            monthText.setText(adapter.getCurrentYear()+"년"+(adapter.getCurrentMonth()+1)+"월");
            }
        });
    }
    class MonthAdapter extends BaseAdapter{
        MonthItemView view;
        MonthItem[] days;
        Calendar calendar;
        int firstday;
        int lastday;
        int year;
        int month;
        int day;

        public MonthAdapter() {
            days=new MonthItem[7*6];

            Date date =new Date();
            calendar=Calendar.getInstance();
            calendar.setTime(date);

            recalculate();
            resetDayNumbers();
        }
        public void setPreviousMonth() {
        calendar.add(Calendar.MONTH,-1); //이전 월로 설정

        recalculate();
        resetDayNumbers();
        }
        public int getCurrentYear() {
            return year;
        }
        public int getCurrentMonth() {
            return month;
        }
        public void setNextMonth() {
            calendar.add(Calendar.MONTH,1);

            recalculate();
            resetDayNumbers();
        }
        public void recalculate(){ //시작하는 요일
            calendar.set(Calendar.DAY_OF_MONTH,1); //현재시간에 해당하는 월에서 1일로설정

            int dayOfweek = calendar.get(Calendar.DAY_OF_WEEK); //해당 주에서 몇번째 일인지
            firstday = getFirstDay(dayOfweek);

            year=calendar.get(Calendar.YEAR);
            month=calendar.get(Calendar.MONTH);
            day=calendar.get(Calendar.DAY_OF_WEEK);

            lastday = getLastDay();
            if(lastday==0) {
                view.setDay(0);

            }
           // int firstDayOfWeek=calendar.getFirstDayOfWeek();
           // int startDay=getFirstDayWeek(firstDayOfWeek);
        }
        public int getFirstDay(int dayOfweek){
            int result=0; //칼럼 인덱스

            if(dayOfweek==Calendar.SUNDAY){
                result=0;
            }else if(dayOfweek==Calendar.MONDAY){
                result=1;
            }else if(dayOfweek==Calendar.TUESDAY){
                result=2;
            }else if(dayOfweek==Calendar.WEDNESDAY){
                result=3;
            }else if(dayOfweek==Calendar.THURSDAY){
                result=4;
            }else if(dayOfweek==Calendar.FRIDAY){
                result=5;
            }else if(dayOfweek==Calendar.SATURDAY){
                result=6;
            }
            return result;
        }
        public int getLastDay(){
            switch (month){
                case 0:
                case 2:
                case 4:
                case 5:
                case 7:
                case 9:
                case 11:
                    return 31;
                case 1:
                case 3:
                case 6:
                case 8:
                case 10:
                    return 30;
                    default:
                        if(((year%4==0)&&(year%100!=0))||(year%400==0)) { //윤년
                            return 28;
                        }else {
                            return 0;
                        }

            }
        }
        public void resetDayNumbers(){
            for(int i=0; i<42; i++){
                int dayNumber =(i+1)-firstday;
                if(dayNumber<1||dayNumber>lastday) {
                    dayNumber = 0;
                }
                days[i]=new MonthItem(dayNumber);
            }
        }
        @Override
        public int getCount() {
            return 42;
        }

        @Override
        public Object getItem(int i) {
            return days[i];
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup viewGroup) {
             view = null;
            if (convertView == null) {
                view = new MonthItemView(getApplicationContext());
            } else {
                view = (MonthItemView) convertView;
            }
            view.setDay(days[i].day);
            return view;
        }
    }

}

