package com.example.study37_multitouch;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class ImageDisplayView extends View {
    Paint paint;
    Matrix matrix;
    public ImageDisplayView(Context context) {
        super(context);
        init(context);
    }

    public ImageDisplayView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    private void init(Context context){
        paint=new Paint();
        matrix =new Matrix(); //확대 축소할때 사용

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) { //레이아웃 크기 결정되면 자동호출
        super.onSizeChanged(w, h, oldw, oldh);


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
