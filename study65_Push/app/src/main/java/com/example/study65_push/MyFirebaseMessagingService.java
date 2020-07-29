package com.example.study65_push;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final  String TAG="MyMS";
    @Override
    //4단계에서 상대방 메세지 받았을때
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.d(TAG,"onMessageReceived()호출됨");

        String from = remoteMessage.getFrom();
        Map<String,String> data= remoteMessage.getData();
        String contents= data.get("contents");

        Log.d(TAG,"from~"+from+"contents:"+contents);

        sendToActivity(this,from,contents);

    }

    public void sendToActivity(Context context,String from,String contents){
        Intent intent =new Intent(context,MainActivity.class);
        intent.putExtra("from",from);
        intent.putExtra("contents",contents);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);

        context.startActivity(intent);
    }
}
