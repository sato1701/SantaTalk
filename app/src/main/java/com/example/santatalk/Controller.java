package com.example.santatalk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Controller extends AppCompatActivity {
    Mode mode = new Mode(this);
    Model model = new Model(this);
    View view = new View(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    void init() {
        model.init();
        view.init();
    }

    void translate( ){
        model.translate();
        view.translateResult();
    }

    void record(){
        // if (isRecording){
            model.requestPermissions();
            model.recordStart();
            view.record();
        // }else{
            model.recordStop();
            view.record();
            view.translateResult();
        // }
    }

    void changeMode(){
        view.changeMode();
    }

    void changeLanguage(){
        view.changeLanguage();
    }
}