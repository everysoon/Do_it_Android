package com.example.study45_coverflow;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coverFlow coverFlow=(com.example.study45_coverflow.coverFlow)findViewById(R.id.coverflow);
        ImageAdapter adapter=new ImageAdapter();
        coverFlow.setAdapter(adapter);

    }

    class ImageAdapter extends BaseAdapter{
            int[]items={R.drawable.green,R.drawable.star,R.drawable.sun,R.drawable.whitemoon,R.drawable.yelowmoon};
        @Override
        public int getCount() {
            return items.length;
        }

        @Override
        public Object getItem(int i) {
            return items[i];
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View convertview, ViewGroup viewGroup) {
            ImageView view =new ImageView(getApplicationContext());
            view.setImageResource(items[i]);
            view.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
          BitmapDrawable drawable= (BitmapDrawable)view.getDrawable();
          drawable.setAntiAlias(true);//부드럽게 그려주기
            return view ;
        }
    }
}
