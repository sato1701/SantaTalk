package com.example.santatalk;

public class Mode {
    private int isSpeechMode = 0;
    enum TRANSLATE_MODE {
        SJtoSS,
        SStoNJ,
        NJtoSJ
    }
    private TRANSLATE_MODE translateMode = TRANSLATE_MODE.SJtoSS;
    private int isRecording = 0;

    public Mode(Controller controller) {
        TRANSLATE_MODE translateMode = TRANSLATE_MODE.SJtoSS;
    }

    public boolean isRecording(){
        return isRecording != 0;
    }

    public boolean isSpeechMode(){
        return isSpeechMode != 0;
    }

    public TRANSLATE_MODE getTranslateMode(){
        return translateMode;
    }

    public void setTranslateMode(TRANSLATE_MODE translateMode){
        this.translateMode = translateMode;
    }
}
