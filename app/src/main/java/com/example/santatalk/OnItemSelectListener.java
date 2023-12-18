package com.example.santatalk;

import static com.example.santatalk.Text_Text.generateButton;
import static com.example.santatalk.Text_Text.updateSecondSpinner;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class OnItemSelectListener extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static Context conText_main;
    public static Spinner Category_spinner;
    public static Spinner Detail_spinner;

    public static LinearLayout buttonContainer;

    OnItemSelectListener(Context ct, LinearLayout buttonContainer, Spinner Category, Spinner Detail) {
        setConText(ct);
        conText_main = ct;
        this.buttonContainer = buttonContainer;
        this.Category_spinner = Category;
        this.Detail_spinner = Detail;
    }


    public static void setConText(Context ct) {
        conText_main = ct;
        Log.d("onItemSelectListener", "set_Contest");
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        //Spinnerで選択された要素に応じてボタンを再生成
        String selectedOption = parent.getItemAtPosition(position).toString();
        updateSecondSpinner(conText_main, Text_Text.Detail_spinner, selectedOption);


        generateButton(conText_main, Text_Text.buttonContainer, selectedOption);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}

