package com.example.study45_coverflow;

import android.content.Context;
import android.graphics.Camera; //소프트카메라
import android.graphics.Matrix;
import android.media.Image;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Transformation;
import android.widget.Gallery;
import android.widget.ImageView;

public class coverFlow extends Gallery {

    Camera camera=new Camera();
    public static  int maxRotationAngle=55;
    public static int maxZoom=-60;
    private int centerPoint;

    public coverFlow(Context context) {
        super(context);

        init(context);
    }

    public coverFlow(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        centerPoint=((getWidth()-getPaddingLeft()-getPaddingRight())/2+getPaddingLeft());
    }

    @Override
    protected boolean getChildStaticTransformation(View child, Transformation t) {
       int childCenter= child.getLeft()+child.getWidth()/2;
        int childWidth=child.getWidth();

        int rotationAngle=0;
        t.clear();
        t.setTransformationType(Transformation.TYPE_MATRIX);
        if(childCenter==centerPoint){
            transforImageBitmap((ImageView)child,t,0);
        }else {
            rotationAngle=(int)(((float)centerPoint-childCenter/childWidth)*maxRotationAngle);
            if(Math.abs(rotationAngle)>maxRotationAngle){
                rotationAngle=(rotationAngle<0)?-maxRotationAngle:maxRotationAngle;

            }
            transforImageBitmap((ImageView)child,t,rotationAngle);

        }
        return true;
    }

    private void init(Context context){
        setStaticTransformationsEnabled(true);//아이템을 변형시키겠다

    }

    public void transforImageBitmap(ImageView child,Transformation t,int rotationAngle){
        camera.save();

        Matrix matrix=t.getMatrix();
        int height=child.getLayoutParams().height;
        int width=child.getLayoutParams().width;
        int rotation=Math.abs(rotationAngle);

        camera.translate(0.0f,0.0f,100.0f);

        if(rotation<maxRotationAngle){
            float zoomAmount=(float)(maxZoom+(rotation*1.5));
            camera.translate(0.0f,0.0f,zoomAmount);
        }
        camera.rotateZ(rotationAngle);
        camera.getMatrix(matrix);

        matrix.preTranslate(-(width/2),-(height/2));
        matrix.postTranslate((width/2),(height/2));

        camera.restore();
    }
}
