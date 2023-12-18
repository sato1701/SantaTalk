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
    public String Identifier;

    OnItemSelectListener(Context ct, String Spinner) {
        setConText(ct);
        Identifier = Spinner;
    }


    public static void setConText(Context ct) {
        conText_main = ct;
        Log.d("onItemSelectListener", "set_Contest");
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        //Spinnerで選択された要素に応じてボタンを再生成
        String selectedCategory = Text_Text.Category_spinner.getItemAtPosition(position).toString();
        int pos = Text_Text.Detail_spinner.getCount() - 1;
        Log.d("OnItemSelectListener", "Count " + pos);
        if(pos < position){
            position = pos;
        }

        String selectedDetail = Text_Text.Detail_spinner.getItemAtPosition(position).toString();


        if(Identifier == "Category"){
            updateSecondSpinner(conText_main, Text_Text.Detail_spinner, selectedCategory);
        }
        else if(Identifier == "Detail"){
            generateButton(conText_main, Text_Text.buttonContainer, selectedDetail);
        }



    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}

