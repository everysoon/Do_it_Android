package com.example.study26_fragment_java;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MenuFragment extends Fragment {
    MainActivity act;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        act=(MainActivity)getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        act=null;
    }
    @Nullable
    @Override
    //setcontentView 없어서 콜백메소드 사용
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       ViewGroup rootView= (ViewGroup) inflater.inflate(R.layout.fragment_menu,container,false);
        //rootView는 최상위 레이아웃

        Button button4=(Button)rootView.findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                act.onFragmentChange(0);
            }
        });

       return rootView;
    }
}
