package com.example.study60_camera_surfaceview;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    CameraSurfaceView surfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView=(ImageView)findViewById(R.id.imageView);
        surfaceView=(CameraSurfaceView)findViewById(R.id.surfaceView);

        Button button=(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                capture();
            }
        });
    }
    public void capture(){
        surfaceView.capture(new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] data, Camera camera) {
                //캡처한 이미지가 바이트객체로 전해짐
                BitmapFactory.Options options=new BitmapFactory.Options();
                options.inSampleSize=8;
               Bitmap bitmap=BitmapFactory.decodeByteArray(data,0,data.length,options);
                imageView.setImageBitmap(bitmap);

                camera.startPreview();

            }
        });
    }
}
