package com.example.santatalk;

public class Mode {
    private int isSpeechMode = 0;
    private int isToSanta = 0;
    private int isRecording = 0;

    public Mode(Controller controller) {
    }

    public boolean isRecording(){
        return isRecording != 0;
    }

    public boolean isSpeechMode(){
        return isSpeechMode != 0;
    }

    public boolean isToSanta(){
        return isToSanta != 0;
    }
}
