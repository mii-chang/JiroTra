package com.miyuu.android.jirotra;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class InputActivity extends AppCompatActivity implements InputDialogFragment.OnDialogButtonClickListener {

    int[] buttonId = {R.id.menButton, R.id.yasaiButton, R.id.aburaButton, R.id.tareButton, R.id.garlicButton};
    String[] titles = {"麺の量を選択", "野菜の量を選択", "アブラの量を選択", "カラメを選択", "にんにくの量を選択"};
    String[] messages = {"麺の量を選択", "野菜の量を選択", "アブラの量を選択", "カラメを選択", "にんにくの量を選択"};
    String[] masiCall = {"なし", "少なめ", "普通", "マシ", "マシマシ"};
    String[] menCall = {"普通", "少なめ", "半分", "多め", "マシマシ"};
    ArrayList<String> masiList = new ArrayList<>();
    ArrayList<String> menList = new ArrayList<>();
    Button[] buttons = new Button[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        for (int i = 0; i < masiCall.length; i++) {
            masiList.add(masiCall[i]);
        }
        for (int i = 0; i < menCall.length; i++) {
            menList.add(menCall[i]);
        }


        for (int i = 0; i < buttonId.length; i++) {
            final String title = titles[i];
            final String message = messages[i];
            final ArrayList<String> callMenList = menList;
            final ArrayList<String> callMasiList = masiList;;
            final int callType = i;


            buttons[i] = findViewById(buttonId[i]);
            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    InputDialogFragment fragment = InputDialogFragment.newInstance(title, message,callMenList, callMasiList,callType);
                    fragment.show(getSupportFragmentManager(), "input_dialog");
                }
            });
        }
    }

    @Override
    public void onPositiveClick(String item) {

    }

    @Override
    public void onNegativeClick() {

    }
}
