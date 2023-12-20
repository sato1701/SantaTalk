package com.kosenhacku2023.santatalk;

import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class Speech_Text extends Fragment {

    public com.kosenhacku2023.santatalk.View myView;
    Speech_Text(com.kosenhacku2023.santatalk.View view){
        myView = view;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Fragmentのレイアウトをインフレート
        View view = inflater.inflate(R.layout.fragment_s_t, container, false);

        TextView InputText = view.findViewById(R.id.s_Input_text);
        TextView OutputText = view.findViewById(R.id.s_OutPut_text);
        Button Recording = view.findViewById(R.id.Record_cState_button);

        Recording.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myView.recordHandler();
            }
        });




        return inflater.inflate(R.layout.fragment_s_t, container, false);
    }
}
