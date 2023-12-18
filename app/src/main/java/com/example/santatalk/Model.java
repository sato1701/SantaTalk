package com.example.santatalk;

public class Model {
    public Model(Controller controller) {
    }

    void init() {
        //TODO
    }

    void requestPermissions( ){
        //TODO
    }

    void recordStart( ){
        //TODO
    }

    void recordStop( ){
        //TODO
    }

    String translate(String InputText){
        this.dispatchToWords();
        //TODO
        String OutPutText = "";

        OutPutText += InputText + "translate";
        // ここに翻訳処理記述お願いします。
        // 返り値はStringでおねがいします。

        return OutPutText;
    }

    void dispatchToWords() {
        //TODO
    }
}
