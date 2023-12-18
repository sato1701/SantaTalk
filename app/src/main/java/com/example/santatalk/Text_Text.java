package com.example.santatalk;

import android.content.Context;
import android.os.Bundle;
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
        OnItemSelectListener onItemSelectedListener = new OnItemSelectListener(view.conText_main, buttonContainer,Category_spinner,Detail_spinner);

        Category_spinner.setOnItemSelectedListener(onItemSelectedListener);


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
        updateSecondSpinner(view.conText_main, Detail_spinner,Category_spinner.getSelectedItem().toString());

        //buttonの処理
        Select_Word_scroll = tmpView.findViewById(R.id.Select_Word_scroll);
        buttonContainer = Select_Word_scroll.findViewById(R.id.buttonContainer);
        generateButton(view.conText_main, buttonContainer, Category_spinner.getSelectedItem().toString());





        return tmpView;
    }





    // Spinnerで選択された要素に応じてボタンを生成するメソッド
    public static void generateButton(Context context,LinearLayout buttonContainer, String selectedOption){
        // 既存のボタンがあれば削除
        buttonContainer.removeAllViews();

        // ボタンを生成
        Button dynamicButton = new Button(context);
        dynamicButton.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        dynamicButton.setText("Dynamic Button for " + selectedOption);

        // ボタンがクリックされたときの処理
        dynamicButton.setOnClickListener(v -> {
            Toast.makeText(context, "Dynamic Button Clicked for " + selectedOption, Toast.LENGTH_SHORT).show();
            // ここにボタンがクリックされたときの追加の処理を記述
        });

        // ボタンをレイアウトに追加
        buttonContainer.addView(dynamicButton);
    }

    //TODO
    // making
    public static void updateSecondSpinner(Context context, Spinner secondSpinner, String selectedOption){
// 2つめのSpinnerにアダプターを設定


        ArrayAdapter<CharSequence> secondAdapter;
        if ("名詞".equals(selectedOption)) {
            secondAdapter = ArrayAdapter.createFromResource(
                    context,
                    R.array.detail_noun_array,
                    android.R.layout.simple_spinner_item
            );
        } else if ("動詞".equals(selectedOption)) {
            secondAdapter = ArrayAdapter.createFromResource(
                    context,
                    R.array.detail_verb_array,
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
