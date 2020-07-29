package com.example.study31_button;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.appcompat.widget.AppCompatButton;

public class Bitmapbutton extends AppCompatButton {

    public Bitmapbutton(Context context) {
        super(context);
        init(context);
    }

    public Bitmapbutton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    private void init(Context context){
        setBackgroundResource(R.drawable.whitemoon);
        float size =getResources().getDimension(R.dimen.text_size);
        setTextSize(size); //픽셀단위임 ->dimens.xml파일로 dp로바꿈
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action=event.getAction();

        switch (action){
            case MotionEvent.ACTION_DOWN:
                setBackgroundResource(R.drawable.yelowmoon);
                break;
            case MotionEvent.ACTION_UP:
                setBackgroundResource(R.drawable.sun);
                break;
        }
        invalidate();
        return true;
    }
}
