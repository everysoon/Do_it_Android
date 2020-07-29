package com.example.study39_graphics_2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.media.midi.MidiOutputPort;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;



public class CustomDrawView  extends View {

    Paint paint;

    public CustomDrawView(Context context) {
        super(context);

        init(context);
    }

    public CustomDrawView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }
    private void init(Context context){
        paint=new Paint();
        paint.setColor(Color.RED);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
       int action= event.getAction();

       if(action== MotionEvent.ACTION_DOWN){
           Toast.makeText(getContext(),"눌렸어요오옹",Toast.LENGTH_SHORT).show();
       }
       return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(4.0f);
        paint.setColor(Color.YELLOW);
        canvas.drawRect(10,10,100,100,paint);


        paint.setStyle(Paint.Style.FILL);
        paint.setARGB(128,0,0,255);
        canvas.drawRect(120,10,210,100,paint);

        //dashPathEffect는  대쉬는 점선, 점선의 속성을 넣어준것!
        DashPathEffect dashPathEffect=new DashPathEffect(new float[]{5,5},1);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5.0f);
        paint.setPathEffect(dashPathEffect); //실선이아니라 점선으로 바꿔줌
        paint.setColor(Color.BLUE);
        canvas.drawRect(120,10,210,100,paint);

        //대각선이 깨지지않고 하려면  paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(40.0f);
        canvas.drawText("안뇽하세요옹",20,320,paint);


    }
}
