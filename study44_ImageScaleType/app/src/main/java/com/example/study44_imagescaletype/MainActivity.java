package com.example.study44_imagescaletype;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Matrix;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView=(ImageView)findViewById(R.id.imageView);

        Matrix matrix =new Matrix();
        matrix.postRotate(45.0f);

        imageView.setImageMatrix(matrix);
        //scaleType을 matrix로 주면 자바파일에서 matrix속성으로 이미지를 변환해줄수있음
    }
}
