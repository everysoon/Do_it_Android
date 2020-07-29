package com.example.study40_drawable;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.util.AttributeSet;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;


public class CustomDrawView extends View {

    Paint paint;
    int devieceWidth;
    int deviceHeight;

    ShapeDrawable drawable;

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

        //화면의 크기알아내기위해 -windowManager
        WindowManager windowManager=(WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        Display display =windowManager.getDefaultDisplay();
        devieceWidth=display.getWidth();
        deviceHeight=display.getHeight();

         drawable=new ShapeDrawable();
        RectShape rect=new RectShape();
        rect.resize(devieceWidth,deviceHeight);
        drawable.setShape(rect);//모양설정
        drawable.setBounds(0,0,devieceWidth,deviceHeight);


        LinearGradient gradient=new LinearGradient(0,0,0,deviceHeight,Color.WHITE,Color.YELLOW,Shader.TileMode.CLAMP);
        Paint curPaint= drawable.getPaint();
        curPaint.setShader(gradient); //점차색깔이 변경되는
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

        drawable.draw(canvas);
    }
}
