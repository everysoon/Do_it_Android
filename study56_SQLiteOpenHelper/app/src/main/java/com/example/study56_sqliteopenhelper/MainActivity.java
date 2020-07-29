package com.example.study56_sqliteopenhelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    TextView textView;
    EditText editText2;
    EditText editText3;
    EditText editText4;
    EditText editText5;
    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText=(EditText)findViewById(R.id.editText);
        editText2=(EditText)findViewById(R.id.editText2);
        editText3=(EditText)findViewById(R.id.editText3);
        editText4=(EditText)findViewById(R.id.editText4);
        editText5=(EditText)findViewById(R.id.editText5);
        textView=(TextView)findViewById(R.id.textView);

        Button button=(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=editText.getText().toString();
                OpenDataBase(name);
            }
        });

        Button button2=(Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tablename=editText2.getText().toString();
                createTable(tablename);
            }
        });
        Button button3=(Button)findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=editText3.getText().toString().trim();
                String agestr=editText4.getText().toString().trim();
                String phone=editText5.getText().toString().trim();
                int age=Integer.parseInt(agestr);

                insertData(name,age,phone);
            }
        });

        Button button4=(Button)findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tablename=editText2.getText().toString();
               selectData(tablename);
            }
        });
    }
    public void selectData(String tablename){
        println("selectData()호출됨");
        if(database != null){
            String sql="select name, age, mobile from "+tablename;
           Cursor cursor= database.rawQuery(sql,null);
           println("조회된 데이터 개수:"+cursor.getCount());

         for(int i=0; i<cursor.getCount();i++){
             cursor.moveToNext();
             String name=cursor.getString(0);
             int age=cursor.getInt(1);
             String mobile=cursor.getString(2);

             println("#" + i +" -> " + name + ", " + age + " , " + mobile);

         }
         cursor.close();
        }
    }
    public void insertData(String name,int age,String phone){
        println("insetData ()호출됨");
        if(database!=null){
            String sql="insert into customer(name,age,mobile) values(?,?,?)";
            Object[]params ={name,age,phone};
            database.execSQL(sql,params);
            println("데이터추가됨");
        }else {
            println("에러!");
        }
    }
    public void createTable(String tablename){
        println("createTable()호출됨");
         if(database!=null){
             String sql= "create table if not exists " + tablename + "("
                     + " _id integer PRIMARY KEY autoincrement, "
                     + " name text, "
                     + " age integer, "
                     + " mobile text)";
             database.execSQL(sql);
             println("테이블 생성됨");
         }else {
             println("먼저 데이터베이스를 오픈하세요 ");
         }
    }
    public void OpenDataBase(String name){
        println("openDatabase()호출됨");
        DatabaseHelper helper=new DatabaseHelper(this,name,null,2);
        database=helper.getWritableDatabase();

    }
    public void println(String data){
        textView.append(data+"\n");
    }

    class DatabaseHelper extends SQLiteOpenHelper{

        public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            println("onCreate()호출됨");
            String tablename="customer";
                String sql= "create table if not exists " + tablename + "("
                        + " _id integer PRIMARY KEY autoincrement, "
                        + " name text, "
                        + " age integer, "
                        + " mobile text)";
                sqLiteDatabase.execSQL(sql);
                println("테이블 생성됨");

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion) {
            println("onUpgrade()호출됨:"+oldversion+","+newversion);
            String tablename="customer";
            if(newversion>1){
                  db.execSQL("drop table if exists "+tablename);
              }
        }
    }
}
