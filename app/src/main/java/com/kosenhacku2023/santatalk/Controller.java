package com.kosenhacku2023.santatalk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class Controller extends AppCompatActivity {
    public Mode mode = new Mode(this);
    Model model = new Model(this);
    View myView = new View(this);

    //MainActivityのContextをView.javaに渡してView内でUIの変更を行えるようにする
    public Context conText_main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //MainActivityのContextを格納しておく
        conText_main = this;
        //初期のFragmentを設定 text -> textのレイアウトをデフォルトで表示する
//        replaceFragment(new Text_Text(view));

        init();
    }

    //Fragmentの置き換えを行う
    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.Text_Text, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    void init() {
        model.init();
        myView.init(conText_main);
    }

    String translate(String InputText, android.view.View view){
        String OutputText; //return
        String[] DispatchedText = Model.dispatchToWords(InputText);
        List<String> OutputList = new ArrayList<>();

        switch(mode.getTranslateMode()){
            case NJtoSJ:
                OutputList = model.translateNJtoSJ(DispatchedText);
                break;
            case SStoNJ:
                OutputList = model.translateSStoNJ(DispatchedText);
                break;
            case SJtoSS:
                OutputList = model.translateSJtoSS(DispatchedText);
                break;
        }
        OutputText = Model.connectToSentence(OutputList);
        myView.translateResult(OutputText, view);
        return OutputText;
    }

    void record(android.view.View view){
        String OutputText = "";

        // if (isRecording){
        model.requestPermissions();
        model.recordStart();
        myView.record();
        // }else{
        model.recordStop();
        myView.record();
        myView.translateResult(OutputText,view);
        // }
    }

    void changeMode(int flag){
        if(flag == 0){
            // Fragment1をcall
            myView.call_Text_Text();
        }else if(flag == 1){
            // Fragment2をcall
            myView.call_Speech_Text();
        }
        else{
            //error
            throw new RuntimeException("Unknown flag number:" + flag);
        }
//        view.changeMode();
    }

    void changeLanguage(android.view.View view,View myView){
        if(mode.getTranslateMode() == Mode.TRANSLATE_MODE.SStoNJ){
            mode.setTranslateMode(Mode.TRANSLATE_MODE.SJtoSS);
            myView.setLangSJ(view,myView);
        } else if (mode.getTranslateMode() == Mode.TRANSLATE_MODE.SJtoSS) {
            mode.setTranslateMode(Mode.TRANSLATE_MODE.SStoNJ);
            myView.setLangSS(view,myView);
        }
        else{
            //error;
            throw new RuntimeException("Unknown translate_mode number");
        }
    }
}