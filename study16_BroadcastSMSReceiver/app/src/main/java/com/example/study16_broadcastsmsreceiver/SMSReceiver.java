package com.example.study16_broadcastsmsreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SMSReceiver extends BroadcastReceiver {
    private static  final  String TAG="SMSReceiver";
    private static SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd- HH:mm");
    @Override //sms받겠다하면 자동호출되는 콜백메소드
    public void onReceive(Context context, Intent intent) {
       Log.d(TAG,"onReceive()호출됨");
       Bundle bundle=intent.getExtras();
        SmsMessage[] messages =parseSmsMessage(bundle);

        if(messages.length>0) { //메세지가있으면
            String sender = messages[0].getOriginatingAddress(); //발신번호(상대방번호)
            Log.d(TAG,"sender :"+sender);

            String contents=messages[0].getMessageBody().toString(); //메세지 내용
            Log.d(TAG,"contents :"+contents);

            Date receivedDate=new Date(messages[0].getTimestampMillis()); //발신시각
            Log.d(TAG,"received date: "+receivedDate);

        sendToActivity(context,sender,contents,receivedDate);
        }
    }

    private void sendToActivity(Context context, String sender, String contents, Date receivedDate) {

        Intent intent =new Intent(context,SmsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|
                        Intent.FLAG_ACTIVITY_CLEAR_TOP|
                        Intent.FLAG_ACTIVITY_SINGLE_TOP);

        intent.putExtra("sender",sender);
        intent.putExtra("contents",contents);
        intent.putExtra("receovedDate",format.format(receivedDate));

        context.startActivity(intent);

    }

    private SmsMessage[] parseSmsMessage(Bundle bundle){//sms데이터있는 bundle을 받아서
                                                        //object로받고 SmsMessage로 변환시키는 메소드
        Object[] objs=(Object[])bundle.get("pdus");
        SmsMessage[] messages=new SmsMessage[objs.length];

        for(int i=0; i<objs.length; i++) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                String format = bundle.getString("format"); //마시멜로우 버전 이후에는
                                                                //bundle에 format으로 들어가있음
                messages[i] = SmsMessage.createFromPdu((byte[]) objs[i], format);
                            //Pdu ?
            } else {
                messages[i] = SmsMessage.createFromPdu((byte[]) objs[i]);
            }
        }
        return messages; //bundle객체안에있는 데이터를 SmsMessage로 변환뒤 리턴!
    }
}
