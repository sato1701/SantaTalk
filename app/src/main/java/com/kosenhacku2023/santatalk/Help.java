package com.kosenhacku2023.santatalk;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.fragment.app.Fragment;

public class Help extends Fragment {
    public com.kosenhacku2023.santatalk.View myView;
    Help(com.kosenhacku2023.santatalk.View view){
        this.myView = view;
    }

    @Override
    public android.view.View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Fragmentのレイアウトをインフレート
        android.view.View view = inflater.inflate(R.layout.help, container, false);


        return view;
    }

}
