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



//    public View onView;
    public Controller controller;   //Controller
    public Mode mode;
    public View view;
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
    public View(Controller controller,Mode mode) {
        //TODO
        this.controller = controller;
        this.view = this;
        this.mode = mode;
    }

//    Text_Text text_text = new Text_Text(this,mode);
//    Speech_Text speech_text = new Speech_Text(this);

    //アプリ起動時の初期化関数 Text to Textのレイアウトを表示する
    void init(Context conText_main) {
        //TODO

        //MainActivityのContextを取得
        this.conText_main = conText_main;

        controller.replaceFragment(new Text_Text(view));

        // ChangeMode
        // ボタンがクリックされた時の処理
        Change_Mode_button = ((com.example.santatalk.Controller) conText_main).findViewById(R.id.btnFragmentOne);
        Change_Mode_button.setOnClickListener(v -> changeModeHandler(0));
        Change_Mode_button = ((com.example.santatalk.Controller) conText_main).findViewById(R.id.btnFragmentTwo);
        Change_Mode_button.setOnClickListener(v -> changeModeHandler(1));

    }

    void call_Text_Text(){
        controller.replaceFragment(new Text_Text(view));
    }
    void call_Speech_Text(){
        controller.replaceFragment(new Speech_Text(view));
    }


    void changeModeHandler(int flag) {
        //TODO
        controller.changeMode(flag);

    }

    void changeLanguageHandler(android.view.View view) {
        //TODO
        controller.changeLanguage(view);
    }

    void setLangSS(android.view.View view){
        TextView Input_text = view.findViewById(R.id.Input_text);
        TextView Output_text = view.findViewById(R.id.OutPut_text);

        Input_text.setText("santanish");
    }

    void setLangSJ(android.view.View view){
        TextView Input_text = view.findViewById(R.id.Input_text);
        TextView Output_text = view.findViewById(R.id.OutPut_text);

        Input_text.setText("japanese");

    }


    void recordHandler() {
        //TODO
    }

    String translateHandler(String InputText) {
        //TODO
        String OutPutText = "";
        OutPutText = controller.translate(InputText);
        return OutPutText;
    }

    void changeMode() {
        //TODO
//        Change_Mode_button.setOnClickListener(v -> controller.replaceFragment(new Text_Text(thisView)));
//        Change_Mode_button.setOnClickListener(v -> controller.replaceFragment(new Speech_Text(thisView)));
    }

    void changeLanguage() {
        //TODO
    }

    void record() {
        //TODO
    }

    String translateResult(String OutPutText) {
        //TODO
        return OutPutText;
    }
}
