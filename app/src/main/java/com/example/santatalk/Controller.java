package com.example.santatalk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.icu.util.Output;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class Controller extends AppCompatActivity {
    public Mode mode = new Mode(this);
    Model model = new Model(this, mode);
    View view = new View(this,mode);

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
        view.init(conText_main);
    }

    String translate(String InputText){
        String OutPutText = "";
        OutPutText = model.translate(InputText);
//        view.translateResult(OutPutText);
        return OutPutText;
    }

    void record(){
        String OutputText = "";

        // if (isRecording){
            model.requestPermissions();
            model.recordStart();
            view.record();
        // }else{
            model.recordStop();
            view.record();
            view.translateResult(OutputText);
        // }
    }

    void changeMode(int flag){
        if(flag == 0){
            // Fragment1をcall]
            view.call_Text_Text();;
        }else if(flag == 1){
            // Fragment2をcall
            view.call_Speech_Text();
        }
        else{
            //error
        }
//        view.changeMode();
    }

    void changeLanguage(android.view.View view){
        if(mode.getTranslateMode() == Mode.TRANSLATE_MODE.SStoNJ){
            mode.setTranslateMode(Mode.TRANSLATE_MODE.SJtoSS);
            this.view.setLangSJ(view);
        } else if (mode.getTranslateMode() == Mode.TRANSLATE_MODE.SJtoSS) {
            mode.setTranslateMode(Mode.TRANSLATE_MODE.SStoNJ);
            this.view.setLangSS(view);
        }
        else{
            //error;
        }
//        view.changeLanguage();
    }
}