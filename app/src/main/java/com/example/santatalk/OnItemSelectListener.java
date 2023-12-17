package com.example.santatalk;

import static com.example.santatalk.Text_Text.generateButton;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class OnItemSelectListener extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static Context conText_main;
    public static LinearLayout buttonContainer;

    OnItemSelectListener(Context ct,LinearLayout buttonContainer) {
        setConText(ct);
        this.buttonContainer = buttonContainer;
    }


    public static void setConText(Context ct) {
        conText_main = ct;
        Log.d("onItemSelectListener", "set_Contest");
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        //Spinnerで選択された要素に応じてボタンを再生成
        String selectedOption = parent.getItemAtPosition(position).toString();
        generateButton(conText_main, Text_Text.buttonContainer, selectedOption);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
