package com.example.study15_service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    private  static  final  String TAG="MyService";

    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d(TAG,"oncreate() 호출됨");
    }

    @Override
    //서비스는 한번 실행되면 계속 실행되있음 (서비스의특성), 실제로는 startService계속 실행한다고해도
    //인텐트로 전달된건 oncreate 한번만 실행되기때문에
    //인텐트는 onStartCommand메소드로 확인함! ->명령어를 처리해주세요 하는 메소드!
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG,"onStartCommand() 호출됨");

        if(intent==null) {
            return Service.START_STICKY; //서비스 종료됬을때도 다시실행해줘!
        }else {
            processCommand(intent);
        }
        return super.onStartCommand(intent, flags, startId);

    }
    //인텐트 안에 extra data 뽑아내!
    private void processCommand(Intent intent) {
        String command=intent.getStringExtra("command");
        String name=intent.getStringExtra("name");

        Log.d(TAG,"전달받은 데이터 :"+command+","+name);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //데이터를 주고받아야할때
        Intent showIntent=new Intent(getApplicationContext(),MainActivity.class);
        showIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|
                             Intent.FLAG_ACTIVITY_SINGLE_TOP
                            |Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //서비스는 화면이없는데
        // 화면없는곳에서 화면쪽(Mian Activity)으로 화면을 띄워달라고하면 에러->옵션을줘야함: flag이용
        //flag사용하면 화면없는곳에서 화면을 띄워줄수있음 -> 일반적으로 new_task,single_top,clear_top같이 사용

        showIntent.putExtra("command","show!");
        showIntent.putExtra("name",name+"from Service");
        startActivity(showIntent);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy() 호출됨");
    }

    //부가데이터(Extra Data)를 처리하고싶을때 서비스를 이용
    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
