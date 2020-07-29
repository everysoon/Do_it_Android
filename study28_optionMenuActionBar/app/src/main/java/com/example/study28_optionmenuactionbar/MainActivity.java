package com.example.study28_optionmenuactionbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ActionBar a=getSupportActionBar();
       // a.hide(); //style가서도 DarkActionBar-> NoActionBar하면돼!
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu_refresh:
                Toast.makeText(this,"새로고침 메뉴 클릭됨",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_search:
                Toast.makeText(this,"검색 메뉴 클릭됨",Toast.LENGTH_SHORT).show();
              return true;
            case R.id.menu_settings:
                Toast.makeText(this,"설정 메뉴 클릭됨",Toast.LENGTH_SHORT).show();
          return true;

        }
        return super.onOptionsItemSelected(item);
    }
}
