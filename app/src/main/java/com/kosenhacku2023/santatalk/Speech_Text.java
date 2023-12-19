package com.kosenhacku2023.santatalk;

import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class Speech_Text extends Fragment {

    public com.kosenhacku2023.santatalk.View view;
    Speech_Text(com.kosenhacku2023.santatalk.View view){
        this.view = view;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Fragmentのレイアウトをインフレート
        View tmpView = inflater.inflate(R.layout.fragment_s_t, container, false);

        TextView InputText = tmpView.findViewById(R.id.s_Input_text);
        TextView OutputText = tmpView.findViewById(R.id.s_OutPut_text);
        Button Recording = tmpView.findViewById(R.id.Record_cState_button);

        Recording.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.recordHandler();
            }
        });




        return inflater.inflate(R.layout.fragment_s_t, container, false);
    }
}
