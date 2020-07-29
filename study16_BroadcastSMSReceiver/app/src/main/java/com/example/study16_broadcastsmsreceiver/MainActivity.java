package com.example.study16_broadcastsmsreceiver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //위험권한
        int permissioncheck = ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS);
        if (permissioncheck == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "SMS수신 권한 주어져 있음.", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "SMS수신 권한 없음.", Toast.LENGTH_LONG).show();
        }
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECEIVE_SMS)) {
            Toast.makeText(this, "SMS권한 설명 필요함.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECEIVE_SMS}, 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if(grantResults.length>0){
                    if(grantResults[0]==PackageManager.PERMISSION_GRANTED)//수락{
                        Toast.makeText(this,"SMS수신 권한을 사용자가 승인함 ",Toast.LENGTH_LONG).show();
                }else if(grantResults[0]==PackageManager.PERMISSION_DENIED){
                    Toast.makeText(this,"SMS수신 권한을 사용자가 거부함 ",Toast.LENGTH_LONG).show();
                }else
                    Toast.makeText(this,"SMS수신 권한을 부여받지 못함 ",Toast.LENGTH_LONG).show();
        }
    }

}
