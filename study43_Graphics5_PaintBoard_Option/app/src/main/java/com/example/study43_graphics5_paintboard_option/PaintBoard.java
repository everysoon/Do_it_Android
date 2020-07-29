package com.example.study43_graphics5_paintboard_option;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class PaintBoard extends View {
    Paint paint;
    Bitmap mbitmap;
    Canvas mcanvas;

    int oldx,oldy;

    public PaintBoard(Context context) {
        super(context);
        init(context);
    }

    public PaintBoard(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }
    public void setColor(int color){
        paint.setColor(color);
    }
    public void setLineWidth(float line){
        paint.setStrokeWidth(line);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mbitmap=Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888);
        mcanvas=new Canvas();
        mcanvas.setBitmap(mbitmap);
        mcanvas.drawColor(Color.WHITE);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action=event.getAction();
        int curX=(int)event.getX();
        int curY=(int)event.getY();
        if(action==MotionEvent.ACTION_DOWN){
            oldx=curX;
            oldy=curY;
        }else if(action==MotionEvent.ACTION_MOVE){
                if(oldx>0||oldy>0){
                    mcanvas.drawLine(oldx,oldy,curX,curY,paint);
                }
                oldx=curX;
                oldy=curY;
        }else if(action== MotionEvent.ACTION_UP){

        }
        invalidate(); //다시그려달라
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(mbitmap,0,0,null);
    }

    private void init(Context context){
        paint=new Paint();
        paint.setColor(Color.BLACK);

    }
}
