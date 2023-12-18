package com.example.santatalk;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.lang.reflect.Array;

public class Text_Text extends Fragment {

    public Controller controller;   //Controller
    public static Mode mode;
    public com.example.santatalk.View view;
    public TextView Input_text;
    public TextView Output_text;

    public static Button Change_Lang_button;      //入力言語変更を行うボタン


    public static Spinner Category_spinner;
    public static Spinner Detail_spinner;

    public ScrollView Select_Word_scroll;

    // buttonのコンテナ
    public static LinearLayout buttonContainer;
    public static String[] InputLang = {"Japanese","Santanish"};
    public static int flagInputLang = 0;

    public static String InputText = "";

    Text_Text(com.example.santatalk.View view,Mode mode){
        this.view = view;
        this.mode = mode;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Fragmentのレイアウトをインフレート
        View tmpView = inflater.inflate(R.layout.fragment_t_t, container, false);
        Log.d("onCreateView","flagInputLang : "+flagInputLang);

        //Spinnerの取得
        Category_spinner = tmpView.findViewById(R.id.Category_spinner);

        // ArrayAdapterを作成し、Spinnerに設定
        ArrayAdapter<CharSequence> Category_adapter = ArrayAdapter.createFromResource(
                requireContext(),  // コンテキストを取得
                R.array.category_array,  // strings.xmlのリソースID
                android.R.layout.simple_spinner_item
        );
        // スピナーのドロップダウンリストのレイアウトを設定
        Category_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // CategoryアダプターをCategory_Spinnerに設定
        Category_spinner.setAdapter(Category_adapter);

        // OnItemSelectListenerを作成
        OnItemSelectListener CategoryListener = new OnItemSelectListener(view.conText_main, tmpView, view, "Category",mode);
        OnItemSelectListener DetailListener = new OnItemSelectListener(view.conText_main, tmpView, view, "Detail",mode);

        Category_spinner.setOnItemSelectedListener(CategoryListener);


        // Spinnerの取得
        Detail_spinner = tmpView.findViewById(R.id.Detail_spinner);

        // ArrayAdapterを作成し、Spinnerに設定
        ArrayAdapter<CharSequence> Detail_adapter = ArrayAdapter.createFromResource(
                requireContext(),  // コンテキストを取得
                R.array.detail_noun_array,  // strings.xmlのリソースID
                android.R.layout.simple_spinner_item
        );
        // スピナーのドロップダウンリストのレイアウトを設定
        Detail_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // アダプターをSpinnerに設定
        Detail_spinner.setAdapter(Detail_adapter);
        Detail_spinner.setOnItemSelectedListener(DetailListener);


        updateSecondSpinner(view.conText_main, Detail_spinner, Category_spinner.getSelectedItem().toString());

        //buttonの処理
        Select_Word_scroll = tmpView.findViewById(R.id.Select_Word_scroll);
        buttonContainer = Select_Word_scroll.findViewById(R.id.buttonContainer);

        Change_Lang_button = tmpView.findViewById(R.id.Change_Lang_button);
        Change_Lang_button.setText("Input Language is : " + InputLang[flagInputLang]);
        Change_Lang_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ボタンの文字列をInputText変数に格納
                Button clickedButton = (Button) v;
                TextView Input_text = tmpView.findViewById(R.id.Input_text);
                TextView Output_text = tmpView.findViewById(R.id.OutPut_text);
                InputText = "";
                Input_text.setText("INPUT TEXT");
                Output_text.setText("OUTPUT TEXT");

                if(mode.getTranslateMode() == Mode.TRANSLATE_MODE.SJtoSS){
                    flagInputLang = 1;
                    mode.setTranslateMode(Mode.TRANSLATE_MODE.SStoNJ);
                    generateButton(view.conText_main,tmpView,view,buttonContainer,"Santanish");
                }
                else if(mode.getTranslateMode() == Mode.TRANSLATE_MODE.SStoNJ){
                    mode.setTranslateMode(Mode.TRANSLATE_MODE.SJtoSS);
                    flagInputLang = 0;
                    Category_spinner.getItemAtPosition(0);
                    Detail_spinner.getItemAtPosition(0);
                    generateButton(view.conText_main,tmpView,view,buttonContainer,Detail_spinner.getItemAtPosition(0).toString());
                }
                else{
                    flagInputLang = 0;
                }

                Change_Lang_button.setText("Input Language is : " + InputLang[flagInputLang]);


                // 格納された文字列を表示
                Toast.makeText(view.conText_main, "Input Language is : " + InputLang[flagInputLang], Toast.LENGTH_SHORT).show();
            }
        });


        return tmpView;
    }


    // Spinnerで選択された要素に応じてボタンを生成するメソッド
    public static void generateButton(Context context, View tmpView, com.example.santatalk.View view, LinearLayout buttonContainer, String selectedOption){
        // 既存のボタンがあれば削除
        buttonContainer.removeAllViews();

        Resources resources = context.getResources();
        String[] buttonTexts = new String[0];

        Log.d("Text_Text", "selectedOption" + selectedOption);

        if(selectedOption.equals("代名詞")){
            buttonTexts = resources.getStringArray(R.array.noun_people_array);
        } else if (selectedOption.equals("固有名詞")) {
            buttonTexts = resources.getStringArray(R.array.noun_unique_array);

        } else if (selectedOption.equals("一般名詞")) {
            buttonTexts = resources.getStringArray(R.array.noun_general_array);

        } else if (selectedOption.equals("数詞")) {
            buttonTexts = resources.getStringArray(R.array.noun_numeral_array);

        } else if (selectedOption.equals("自動詞")) {
            buttonTexts = resources.getStringArray(R.array.verb_intransitive_array);

        } else if (selectedOption.equals("他動詞")) {
            buttonTexts = resources.getStringArray(R.array.verb_transitive_array);

        } else if (selectedOption.equals("感情")) {
            buttonTexts = resources.getStringArray(R.array.adjective_emotion_array);

        } else if (selectedOption.equals("表現")) {
            buttonTexts = resources.getStringArray(R.array.adjective_expression_array);

        } else if (selectedOption.equals("挨拶")) {
            buttonTexts = resources.getStringArray(R.array.interjection_array);

        } else if (selectedOption.equals("助動詞")) {
            buttonTexts = resources.getStringArray(R.array.auxiliaryVerb_array);

        } else if (selectedOption.equals("Santanish")) {
            buttonTexts = resources.getStringArray(R.array.Santanish_array);

        }
        else{
            buttonTexts = resources.getStringArray(R.array.noun_people_array);
        }

        for(String buttonText : buttonTexts) {
            // ボタンを生成
            Button dynamicButton = new Button(context);
            dynamicButton.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            dynamicButton.setText(buttonText);

            // ボタンがクリックされたときの処理
            dynamicButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // ボタンの文字列をInputText変数に格納
                    Button clickedButton = (Button) v;
                    if(clickedButton.getText().toString().equals("init")){
                        InputText = "";
                    } else if (clickedButton.getText().toString().equals("Space")) {
                        InputText += " | ";
                    } else {
                        InputText += " " + clickedButton.getText().toString();
                        // 格納された文字列を表示
                        Toast.makeText(context, "InputText: " + InputText, Toast.LENGTH_SHORT).show();

                    }
                    TextView Input_text = tmpView.findViewById(R.id.Input_text);
                    TextView Output_text = tmpView.findViewById(R.id.OutPut_text);

                    Input_text.setText(InputText);

                    String OutPutText =  view.translateHandler(InputText);
                    Output_text.setText(OutPutText);
                }
            });

            // ボタンをレイアウトに追加
            buttonContainer.addView(dynamicButton);
        }
    }

    //TODO
    // making
    public static void updateSecondSpinner(Context context, Spinner secondSpinner, String selectedOption){
// 2つめのSpinnerにアダプターを設定

        secondSpinner.setSelection(0);
        Log.d("Text_Text", "updateSecondSpinner" + selectedOption);

        ArrayAdapter<CharSequence> secondAdapter;
        if (selectedOption.equals("名詞")) {
            secondAdapter = ArrayAdapter.createFromResource(
                    context,
                    R.array.detail_noun_array,
                    android.R.layout.simple_spinner_item
            );
        } else if (selectedOption.equals("動詞")) {
            secondAdapter = ArrayAdapter.createFromResource(
                    context,
                    R.array.detail_verb_array,
                    android.R.layout.simple_spinner_item
            );
        } else if (selectedOption.equals("形容詞")) {
            secondAdapter = ArrayAdapter.createFromResource(
                    context,
                    R.array.detail_adjective_array,
                    android.R.layout.simple_spinner_item
            );
        } else if (selectedOption.equals("感動詞")) {
            secondAdapter = ArrayAdapter.createFromResource(
                    context,
                    R.array.detail_interjection_array,
                    android.R.layout.simple_spinner_item
            );
        } else if (selectedOption.equals("助動詞")) {
            secondAdapter = ArrayAdapter.createFromResource(
                    context,
                    R.array.detail_auxiliaryVerb_array,
                    android.R.layout.simple_spinner_item
            );
        } else {
            // Default case, you can handle other options as needed
            secondAdapter = ArrayAdapter.createFromResource(
                    context,
                    R.array.detail_noun_array,
                    android.R.layout.simple_spinner_item
            );
        }

        secondAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        secondSpinner.setAdapter(secondAdapter);


    }
}
