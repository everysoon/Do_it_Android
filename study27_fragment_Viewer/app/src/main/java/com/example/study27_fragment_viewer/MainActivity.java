package com.example.study27_fragment_viewer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    ListFragment fragment1;
    ViewerFragment fragment2;
    FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager=getSupportFragmentManager();

        fragment1=(ListFragment)manager.findFragmentById(R.id.listfragment);
        fragment2=(ViewerFragment)manager.findFragmentById(R.id.viewerfragment);
    }
    public void onImageChange(int index){
            fragment2.setImage(index);

    }
}
