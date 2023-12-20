package com.kosenhacku2023.santatalk;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class View extends AppCompatActivity {
    public Context conText_main;    //MainActivityのContext



//    public View onView;
    public Controller controller;   //Controller
    public View myView;
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
        this.myView = this;
    }

//    Text_Text text_text = new Text_Text(this,mode);
//    Speech_Text speech_text = new Speech_Text(this);

    //アプリ起動時の初期化関数 Text to Textのレイアウトを表示する
    void init(Context conText_main) {
        //TODO

        //MainActivityのContextを取得
        this.conText_main = conText_main;

        controller.replaceFragment(new Text_Text(myView));

        // ChangeMode
        // ボタンがクリックされた時の処理
        Change_Mode_button = ((com.kosenhacku2023.santatalk.Controller) conText_main).findViewById(R.id.btnFragmentOne);
        Change_Mode_button.setOnClickListener(v -> changeModeHandler(0));
        Change_Mode_button = ((com.kosenhacku2023.santatalk.Controller) conText_main).findViewById(R.id.btnFragmentTwo);
        Change_Mode_button.setOnClickListener(v -> changeModeHandler(1));

    }

    void call_Text_Text(){
        controller.replaceFragment(new Text_Text(myView));
    }
    void call_Speech_Text(){
        controller.replaceFragment(new Speech_Text(myView));
    }


    void changeModeHandler(int flag) {
        //TODO
        controller.changeMode(flag);

    }

    void changeLanguageHandler(android.view.View view,View _view) {
        //TODO
        controller.changeLanguage(view,_view);
    }

    void setLangSS(android.view.View view,View myView){
        TextView Input_text = view.findViewById(R.id.Input_text);
        TextView Output_text = view.findViewById(R.id.OutPut_text);

        ScrollView Select_Word_scroll = view.findViewById(R.id.Select_Word_scroll);
        LinearLayout buttonContainer = Select_Word_scroll.findViewById(R.id.buttonContainer);

        Button Change_Lang_button = view.findViewById(R.id.Change_Lang_button);
        Change_Lang_button.setText("Input Language is : Santanish");

        generateButton(conText_main,view,myView,buttonContainer,"Santanish");
        Input_text.setText("input santanish");
        Output_text.setText("OUTPUT TEXT");
    }

    void setLangSJ(android.view.View view,View myView){
        TextView Input_text = view.findViewById(R.id.Input_text);
        TextView Output_text = view.findViewById(R.id.OutPut_text);

        ScrollView Select_Word_scroll = view.findViewById(R.id.Select_Word_scroll);
        LinearLayout buttonContainer = Select_Word_scroll.findViewById(R.id.buttonContainer);

        Spinner Category_spinner = view.findViewById(R.id.Category_spinner);
        Spinner Detail_spinner = view.findViewById(R.id.Detail_spinner);
        Category_spinner.getItemAtPosition(0);
        Detail_spinner.getItemAtPosition(0);

        generateButton(conText_main,view,myView,buttonContainer,Detail_spinner.getItemAtPosition(0).toString());

        Button Change_Lang_button = view.findViewById(R.id.Change_Lang_button);
        Change_Lang_button.setText("Input Language is : japanese");
        Input_text.setText("input japanese");
        Output_text.setText("OUTPUT TEXT");
    }


    void recordHandler() {
        //TODO
    }

    void translateHandler(String InputText,android.view.View view) {
        //TODO
        controller.translate(InputText,view);
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

    void translateResult(String OutPutText, android.view.View view) {
        //TODO
        TextView Output_text = view.findViewById(R.id.OutPut_text);
        Output_text.setText(OutPutText);
    }
    // Spinnerで選択された要素に応じてボタンを生成するメソッド
    public static void generateButton(Context context, android.view.View view, com.kosenhacku2023.santatalk.View myView, LinearLayout buttonContainer, String selectedOption){
        // 既存のボタンがあれば削除
        buttonContainer.removeAllViews();

        Resources resources = context.getResources();
        String[] buttonTexts = new String[0];

        Log.d("Text_Text", "selectedOption" + selectedOption);

        switch (selectedOption) {
            case "代名詞":
                buttonTexts = resources.getStringArray(R.array.noun_people_array);
                break;
            case "固有名詞":
                buttonTexts = resources.getStringArray(R.array.noun_unique_array);
                break;
            case "一般名詞":
                buttonTexts = resources.getStringArray(R.array.noun_general_array);
                break;
            case "数詞":
                buttonTexts = resources.getStringArray(R.array.noun_numeral_array);
                break;
            case "自動詞":
                buttonTexts = resources.getStringArray(R.array.verb_intransitive_array);
                break;
            case "他動詞":
                buttonTexts = resources.getStringArray(R.array.verb_transitive_array);
                break;
            case "感情":
                buttonTexts = resources.getStringArray(R.array.adjective_emotion_array);
                break;
            case "表現":
                buttonTexts = resources.getStringArray(R.array.adjective_expression_array);
                break;
            case "挨拶":
                buttonTexts = resources.getStringArray(R.array.interjection_array);
                break;
            case "助動詞":
                buttonTexts = resources.getStringArray(R.array.auxiliaryVerb_array);
                break;
            case "Santanish":
                buttonTexts = resources.getStringArray(R.array.Santanish_array);
                break;
            default:
                buttonTexts = resources.getStringArray(R.array.noun_people_array);
                break;
        }

        for(String buttonText : buttonTexts) {
            // ボタンを生成
            Button dynamicButton = new Button(context);
            dynamicButton.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            dynamicButton.setText(buttonText);

            // ボタンがクリックされたときの処理
            dynamicButton.setOnClickListener(new android.view.View.OnClickListener() {
                @Override
                public void onClick(android.view.View v) {
                    // ボタンの文字列をInputText変数に格納
                    Button clickedButton = (Button) v;
                    TextView Input_text = view.findViewById(R.id.Input_text);
                    TextView Output_text = view.findViewById(R.id.OutPut_text);
                    String InputText = Input_text.getText().toString();
                    if (InputText.equals("input japanese")) {
                        InputText = "";
                    }  else if (InputText.equals("input santanish")) {
                        InputText = "";
                    }

                    if(clickedButton.getText().toString().equals("init")){
                        InputText = "";
                    } else if (clickedButton.getText().toString().equals("Space")) {
                        InputText += " ";
                    } else {
                        if(InputText.equals(""))
                            InputText = clickedButton.getText().toString();
                        else
                            InputText += " " + clickedButton.getText().toString();
                        // 格納された文字列を表示
                        Toast.makeText(context, "InputText: " + InputText, Toast.LENGTH_SHORT).show();
                    }
                    Input_text.setText(InputText);
                    myView.translateHandler(InputText, view);
                }
            });

            // ボタンをレイアウトに追加
            buttonContainer.addView(dynamicButton);
        }
    }
}
