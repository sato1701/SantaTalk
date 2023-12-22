package com.kosenhacku2023.santatalk;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class Text_Text extends Fragment {

    public Controller controller;   //Controller
    public com.kosenhacku2023.santatalk.View myView;
    public TextView Input_text;
    public TextView Output_text;

    public static Button Change_Lang_button;      //入力言語変更を行うボタン


    public static Spinner Category_spinner;
    public static Spinner Detail_spinner;

    public ScrollView Select_Word_scroll;

    // buttonのコンテナ
    public static LinearLayout buttonContainer;
    public String InputLang = "";
    public  int flagInputLang = 0;

    public static String InputText = "Japanese";

    Text_Text(com.kosenhacku2023.santatalk.View view){
        this.myView = view;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Fragmentのレイアウトをインフレート
        View view = inflater.inflate(R.layout.fragment_t_t, container, false);
        Log.d("onCreateView","flagInputLang : "+flagInputLang);

        //Spinnerの取得
        Category_spinner = view.findViewById(R.id.Category_spinner);

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
        OnItemSelectListener CategoryListener = new OnItemSelectListener(myView.conText_main, view, myView, "Category");
        OnItemSelectListener DetailListener = new OnItemSelectListener(myView.conText_main, view, myView, "Detail");

        Category_spinner.setOnItemSelectedListener(CategoryListener);


        // Spinnerの取得
        Detail_spinner = view.findViewById(R.id.Detail_spinner);

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


        updateSecondSpinner(myView.conText_main, Detail_spinner, Category_spinner.getSelectedItem().toString());

        //buttonの処理
        Select_Word_scroll = view.findViewById(R.id.Select_Word_scroll);
        buttonContainer = Select_Word_scroll.findViewById(R.id.buttonContainer);

        Change_Lang_button = view.findViewById(R.id.Change_Lang_button);
        Change_Lang_button.setText("Input Language is : " + InputText);
        Change_Lang_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // changeLanguage
                myView.changeLanguageHandler(view,myView);

                // ボタンの文字列をInputText変数に格納
//                Button clickedButton = (Button) v;

                // 格納された文字列を表示
//                Toast.makeText(view.conText_main, "Input Language is : " + InputText, Toast.LENGTH_SHORT).show();
            }
        });


        return view;
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
