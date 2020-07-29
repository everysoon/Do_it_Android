package com.example.study34_gridview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    SingerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gridView =(GridView)findViewById(R.id.grid);

        adapter =new SingerAdapter();
        adapter.addItem(new girl("민선","010-0000-0000",R.drawable.moon));
        adapter.addItem(new girl("상순","010-1000-1000",R.drawable.green));
        adapter.addItem(new girl("상준","010-2000-2000",R.drawable.star));
        adapter.addItem(new girl("건우","010-3000-3000",R.drawable.sun));
        adapter.addItem(new girl("진원","010-4000-4000",R.drawable.whitemoon));
        adapter.addItem(new girl("진훈","00.010-5000-5000",R.drawable.yelowmoon));

        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                girl g=(girl)adapter.getItem(position);
                Toast.makeText(getApplicationContext(),"선택:"+g.getName(),Toast.LENGTH_SHORT).show();

            }
        });
    }
    class SingerAdapter extends BaseAdapter {
        ArrayList<girl> items=new ArrayList<girl>();

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int i) {
            return items.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View cview, ViewGroup viewGroup) {
            girlItemView girlview =null;
            if(cview==null) {
                girlview = new girlItemView(getApplicationContext());
            }
            else {
                girlview=(girlItemView)cview; //화면에 안보여지는 것들을 데이터만 바꿔서 재사용할수있도록
            }
            girl item =items.get(i);
            girlview.setName(item.getName());
            girlview.setMobile(item.getMobile());
            girlview.setImage(item.getResId());

            return girlview;
        }
        public void addItem(girl item){
            items.add(item);
        }

    }
}
