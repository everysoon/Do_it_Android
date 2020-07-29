package com.example.study26_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MainFragment extends Fragment {
    @Nullable
    @Override
    //setcontentView 없어서 콜백메소드 사용
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       ViewGroup rootView= (ViewGroup) inflater.inflate(R.layout.fragment_main,container,false);
        //rootView는 최상위 레이아웃

       return rootView;
    }
}
