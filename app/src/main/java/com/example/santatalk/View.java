package com.example.santatalk;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Scroller;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class View extends AppCompatActivity {
    public Context conText_main;    //MainActivityのContext

    Text_Text text_text = new Text_Text(this);
    Speech_Text speech_text = new Speech_Text(this);

//    public View onView;
    public Controller controller;   //Controller
    public View thisView;

    public TextView Mode_Name;      //Mode_Nameを格納するTextView
    public Button Change_Mode_button;      //モード変更を行うボタン(Debug?)

//
//    public TextView Input_text;
//    public TextView Output_text;
//
//    public Button Change_Lang_button;      //入力言語変更を行うボタン
//
//
//    public Spinner Category_spinner;
//    public Spinner Detail_spinner;
//
//    public Scroller Select_Word_scroll;
//
//    public Button[] Words_button;

    //コンストラクタ replaceFragmentを使用したいのでcontrollerを取得しておく
    public View(Controller controller) {
        //TODO
        this.controller = controller;
        thisView = this;
    }

    //アプリ起動時の初期化関数 Text to Textのレイアウトを表示する
    void init(Context conText_main) {
        //TODO

        //MainActivityのContextを取得
        this.conText_main = conText_main;

        controller.replaceFragment(new Text_Text(thisView));

        // ボタンがクリックされた時の処理
        Change_Mode_button = ((com.example.santatalk.Controller) conText_main).findViewById(R.id.btnFragmentOne);
        Change_Mode_button.setOnClickListener(v -> controller.replaceFragment(new Text_Text(thisView)));
        Change_Mode_button = ((com.example.santatalk.Controller) conText_main).findViewById(R.id.btnFragmentTwo);
        Change_Mode_button.setOnClickListener(v -> controller.replaceFragment(new Speech_Text(thisView)));

    }


    void changeModeHandler() {
        //TODO
    }

    void changeLanguageHandler() {
        //TODO
    }

    void recordHandler() {
        //TODO
    }

    void translateHandler() {
        //TODO
    }

    void changeMode() {
        //TODO
    }

    void changeLanguage() {
        //TODO
    }

    void record() {
        //TODO
    }

    void translateResult() {
        //TODO
    }
}
