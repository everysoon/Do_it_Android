package com.example.study20_orientation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    String contents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this,"onCreate()호출됨",Toast.LENGTH_LONG).show();
        Button button=(Button)findViewById(R.id.button);

        editText=(EditText)findViewById(R.id.editText);
if(button!=null) {
    button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            contents = editText.getText().toString();
            Toast.makeText(getApplicationContext(), "입력한 값 할당함.", Toast.LENGTH_LONG).show();

        }
    });
}
        if(savedInstanceState!=null){
            contents=savedInstanceState.getString("contents");
            if(editText!=null)
                editText.setText("복원된 값:"+contents);
            Toast.makeText(getApplicationContext()," 복원됐음."+contents,Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("contents",contents);
    }

    @Override
    protected void onStart() {
        Toast.makeText(this,"onStart()호출됨",Toast.LENGTH_LONG).show();
        super.onStart();
    }

    @Override
    protected void onStop() {
        Toast.makeText(this,"onStop()호출됨",Toast.LENGTH_LONG).show();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Toast.makeText(this,"onDestroy()호출됨",Toast.LENGTH_LONG).show();
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        Toast.makeText(this,"onPause()호출됨",Toast.LENGTH_LONG).show();
        super.onPause();
    }

    @Override
    protected void onResume() {
        Toast.makeText(this,"onResume()호출됨",Toast.LENGTH_LONG).show();
        super.onResume();
    }
}
