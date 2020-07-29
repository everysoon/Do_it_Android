package com.example.study12_call_intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button button;
    EditText et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=(Button)findViewById(R.id.button);
        et=(EditText)findViewById(R.id.editText);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String phoneNumber= et.getText().toString();
                Intent intent =new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phoneNumber));
                startActivity(intent);
                /*Intent intent1=new Intent();
                ComponentName name=new ComponentName("com.example.study12_call_intent","com.example.study12_call_intent.MenuActivity");
                intent1.setComponent(name);
                startActivity(intent1);  -->장점: 객체를 지정하는것이아니라 문자열로 액티비티를 지정할수있음.
                                    액티비티가 만들어지지않은상태나 남들이 만들어놓은 액티비티를 사용할수있음  */
                
            }
        });
    }
}
