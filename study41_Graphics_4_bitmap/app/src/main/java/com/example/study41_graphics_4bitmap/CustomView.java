package com.example.study41_graphics_4bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class CustomView  extends View {

    Bitmap bitmap;
    Bitmap mbitmap;
    Canvas mcanvas;

    public CustomView(Context context) {
        super(context);
        init(context);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    private void init(Context context){
        bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.moon);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(mbitmap,0,0,null);
    }

    @Override //뷰 크기가 변경되거나 결정될때 자동호출되는 메소드
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mbitmap=Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888);
        mcanvas=new Canvas();
        mcanvas.setBitmap(mbitmap);

        Paint paint=new Paint();
        paint.setColor(Color.RED);
        mcanvas.drawLine(100,100,400,200,paint);
        mcanvas.drawBitmap(bitmap,0,0,null);


    }
}
