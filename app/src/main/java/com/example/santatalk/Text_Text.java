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
    public com.example.santatalk.View view;
    public TextView Input_text;
    public TextView Output_text;

    public Button Change_Lang_button;      //入力言語変更を行うボタン


    public static Spinner Category_spinner;
    public static Spinner Detail_spinner;

    public ScrollView Select_Word_scroll;

    // buttonのコンテナ
    public static LinearLayout buttonContainer;
    public Button[] Words_button;

    Text_Text(com.example.santatalk.View view){
        this.view = view;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Fragmentのレイアウトをインフレート
        View tmpView = inflater.inflate(R.layout.fragment_t_t, container, false);

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

        // アダプターをSpinnerに設定
        Category_spinner.setAdapter(Category_adapter);

        // OnItemSelectListenerを作成
        OnItemSelectListener CategoryListener = new OnItemSelectListener(view.conText_main,"Category");
        OnItemSelectListener DetailListener = new OnItemSelectListener(view.conText_main,"Detail");

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


        updateSecondSpinner(view.conText_main, Detail_spinner,Category_spinner.getSelectedItem().toString());

        //buttonの処理
        Select_Word_scroll = tmpView.findViewById(R.id.Select_Word_scroll);
        buttonContainer = Select_Word_scroll.findViewById(R.id.buttonContainer);





        return tmpView;
    }

    String JapanWordNoun[] = {  };
    String JapanWordIntVerb[] = {  };
    String JapanWordTranVerb[] = {  };
    String JapanWordAdjective[] = {  };
    String JapanWordNumeral[] = {  };
    String JapanWordInterjection[] = {  };
    String JapanWordAuxVerb[] = {  };

    String JapanWordIntVerbFuture[] = { "降るだろう(したい)", "あるだろう(したい)", "寝るだろう(したい)", "笑うだろう(したい)", "喜ぶだろう(したい)" };
    String JapanWordTranVerbFuture[] = { "行くだろう(したい)", "見るだろう(したい)", "食べるだろう(したい)", "与えるだろう(したい)", "だろう", "するだろう(したい)",
            "置くだろう(したい)", "作るだろう(したい)", "かくだろう(したい)", "話すだろう(したい)", "感謝するだろう(したい)" };
    String JapanWordIntVerbPast[] = { "降った", "あった", "寝た", "笑った", "喜んだ" };
    String JapanWordTranVerbPast[] = { "行った", "見た", "食べた", "与えた", "だった", "した", "置いた", "作った", "かいた", "話した", "感謝した" };
    String SantaWordNoun[] = { "aaa", "aab", "aac", "aad", "aba", "abb", "abc", "abd", "acb", "acc", "acd", "adb",
            "adc", "baa", "bab", "bac", "bad", "bba", "bbb", "bcd", "bdc", "caa", "cab", "cac", "cad", "cba",
            "cbb", "cbc", "cbd", "cca", "ccb", "ccd", "cdb", "cdc", "daa", "dab" };
    String SantaWordIntVerb[] = { "dac", "dad", "dba", "dbb", "dbc" };
    String SantaWordTranVerb[] = { "dbd", "dca", "dcc", "dcd", "ddb", "ddc", "aa", "ab", "ac", "ad", "ba" };
    String SantaWordAdjective[] = { "bb", "bc", "bd", "ca", "cb" };
    String SantaWordNumeral[] = { "cc", "cd" };
    String SantaWordInterjection[] = { "bcc", "db", "dc", "bca" };
    String SantaWordAuxVerb[] = { "bbc", "bbd" };




    // Spinnerで選択された要素に応じてボタンを生成するメソッド
    public static void generateButton(Context context,LinearLayout buttonContainer, String selectedOption){
        // 既存のボタンがあれば削除
        buttonContainer.removeAllViews();

        Resources resources = context.getResources();
        String[] buttonTexts = new String[0];

        int Words;
        int amount = 0;
//        String[] Words;
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
//            dynamicButton.setOnClickListener(v -> {
//                Toast.makeText(context, "Dynamic Button Clicked for " + selectedOption, Toast.LENGTH_SHORT).show();
//                // ここにボタンがクリックされたときの追加の処理を記述
//            });

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
