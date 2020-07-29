package com.example.study65_push;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    TextView logOutput;
    EditText dataInput;
    TextView dataOutput;
    String regId;

    RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logOutput=(TextView)findViewById(R.id.logOutput);
        dataInput=(EditText)findViewById(R.id.dataInput);
        dataOutput=(TextView)findViewById(R.id.dataOutput);

        getRegistrationId();

        Button button =(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data =dataInput.getText().toString().trim();
                send(data);
            }
        });

        queue = Volley.newRequestQueue(this);

        Intent passedIntent = getIntent();
        processIntent(passedIntent);
    }
    public void processIntent(Intent intent){
        if(intent!=null){
            String from = intent.getStringExtra("from");
            String contents= intent.getStringExtra("contents");

            println("DATA : "+from +","+contents);
            dataOutput.setText("DATA:"+contents);
        }

    }
    public void onNewIntent(Intent intent){
        super.onNewIntent(intent);

        processIntent(intent);
    }
    public void send(String input){
        JSONObject requestData =new JSONObject();
        try {
            requestData.put("priority", "high");

            JSONObject dataobj =new JSONObject();
            dataobj.put("contents",input);
            requestData.put("data",dataobj);

            JSONArray idArray=new JSONArray();
            idArray.put(0,regId);
            requestData.put("registration_ids",idArray);
            //Volley라이브러리를 통해 제이손데이터 보내

        }catch (Exception e){}

        sendData(requestData, new pushResponseListener() {
            @Override
            public void onRequestStarted() {
                println("onRequestStarted");
            }

            @Override
            public void onRequestCompleted() {
                println("onRequestCompleted");
            }

            @Override
            public void onRequestError(VolleyError error) {
                println("onRequestError");
            }
        });
    }
    public interface pushResponseListener{
        public void onRequestStarted();
        public void onRequestCompleted();
        public void onRequestError(VolleyError error);
    }
    public void sendData(JSONObject requestData,final pushResponseListener listener){
        JsonObjectRequest request=new JsonObjectRequest(
                Request.Method.POST,
                "https://fcm.googleapis.com/fcm/send",
                requestData,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                            listener.onRequestCompleted();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        listener.onRequestError(error);
                    }
                }
        );{
          /*  @Override
            public String getBodyContentType(){
                return "application/json";
            }
            @Override
            public Map<String,String>getHeaders() throw AuthFailureError{
                Map<String,String> headers = new HashMap<>();
                headers.put("Authorization","key = AAAAM09k6xM:APA91bGD5rA_jr2u3m-VexfIJQgCUlg3MLWISDijt13dnQlxeb8Fa5P1oZbN-7WFzqpwMsxVZ2reTm47D7OiHb-zUwI8yB0omh--ul2QMku7V9BTtJYt-VPICxnpoocRS_X_OzgJ6EQD");

                return headers;
            }
            @Override
            protected Map<String,String> getParams() throws AuthFailureError{
                Map<String,String> params =new HashMap<>();

                return params;
            }
*/
        }; //정보 더 넣어주기

        request.setShouldCache(false);//false로해놔야 매번갱신
        listener.onRequestStarted();
        queue.add(request);
    }
    public void getRegistrationId(){
       regId= FirebaseInstanceId.getInstance().getToken();
       println("등록ID->"+regId);
    }
    public void println(String data){
        logOutput.append(data+"\n");
    }
}
