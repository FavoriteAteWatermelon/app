package com.example.viewpager;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Test1 extends Fragment {
    public  static  final  String TITLE = "title";
    public  String mTitle;
  public static  Test1 newInstance(String title){

    Test1 fragment = new Test1();
    Bundle bundle = new Bundle();
    bundle.putString(TITLE,title);
    fragment.setArguments(bundle);

    return  fragment;
  }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments()!= null) {
//            System.out.println(POSITION);
//            System.out.println( getArguments());
//            Log.d(String.valueOf(getArguments()));
//            强势转换
            mTitle = getArguments().getString(TITLE);

        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test,null);
        TextView textView = (TextView) view.findViewById(R.id.text_view);
//        String data = getArguments().getString("title") ;
        textView.setText(mTitle);

        return view;
    }
}
