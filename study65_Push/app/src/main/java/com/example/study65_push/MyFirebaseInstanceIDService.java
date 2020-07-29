package com.example.study65_push;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;
    //firebaseInstanceIdService가 종료되서 firebaseMessagingService 상속하고 onNewToken으로! ->한 클래스에서 해도 될 듯
public class MyFirebaseInstanceIDService extends FirebaseMessagingService {
    private static final  String TAG="MyID";

    public void onNewToken(){
        String Token= FirebaseInstanceId.getInstance().getToken();
        super.onNewToken(Token);
        Log.d(TAG,"onTokenRefresh()호출됨");
    }
}
