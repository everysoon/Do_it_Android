package com.example.study38_graphics;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class CustomView extends View {

    Paint paint;

    public CustomView(Context context) {
        super(context);
        init(context);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    private void init(Context context){
        paint =new Paint();
        paint.setColor(Color.RED);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
       int action =event.getAction();

       if(action==MotionEvent.ACTION_DOWN){
           Toast.makeText(getContext(),"눌렸어용용",Toast.LENGTH_SHORT).show();
      }
       return true;
    }

    @Override  //화면에 그리기위해선 onDraw 재정의해야함
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRect(100,100,200,200,paint);  //px단위

    }
}
