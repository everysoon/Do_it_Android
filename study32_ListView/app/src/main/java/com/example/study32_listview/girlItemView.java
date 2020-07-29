package com.example.study32_listview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class girlItemView extends LinearLayout {
    TextView textview2;
    TextView textview1;
    ImageView image;

    public girlItemView(Context context) {
        super(context);
        init(context);
    }

    public girlItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    private void init(Context context){
        LayoutInflater inflater =(LayoutInflater)context.getSystemService((Context.LAYOUT_INFLATER_SERVICE));
        inflater.inflate(R.layout.girl_item,this,true);

       textview1=(TextView)findViewById(R.id.textview1);
       textview2=(TextView)findViewById(R.id.textview2);
       image=(ImageView)findViewById(R.id.imageView);
    }
    public void setName(String name){
        textview1.setText(name);
    }
    public void setMobile(String mobile){
        textview2.setText(mobile);
    }
    public void setImage(int resId){
        image.setImageResource(resId);
    }
}
