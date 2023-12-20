package com.kosenhacku2023.santatalk;

public class Mode {
    private boolean isSpeechMode = false;
    enum TRANSLATE_MODE {
        SJtoSS,
        SStoNJ,
        NJtoSJ
    }
    private TRANSLATE_MODE translateMode = TRANSLATE_MODE.SJtoSS;
    private boolean isRecording = false;

    public Mode(Controller controller) {
        TRANSLATE_MODE translateMode = TRANSLATE_MODE.SJtoSS;
    }

    public boolean isRecording(){
        return isRecording;
    }

    public boolean isSpeechMode() {
        return isSpeechMode;
    }

    public TRANSLATE_MODE getTranslateMode(){
        return translateMode;
    }

    public void setTranslateMode(TRANSLATE_MODE translateMode){
        this.translateMode = translateMode;
    }
}
