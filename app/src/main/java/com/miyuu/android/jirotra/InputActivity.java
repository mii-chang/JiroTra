package com.miyuu.android.jirotra;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;

public class InputActivity extends AppCompatActivity implements InputDialogFragment.OnDialogButtonClickListener {

    int[] buttonId = {R.id.menButton, R.id.yasaiButton, R.id.aburaButton, R.id.garlicButton};
    final String[] titles = {"麺の量を選択", "野菜の量を選択", "アブラの量を選択", "にんにくの量を選択"};
    final String[] messages = {"麺の量を選択", "野菜の量を選択", "アブラの量を選択", "にんにくの量を選択"};
    final String[] masiCall = {"なし", "少なめ", "普通", "マシ", "マシマシ"};
    final String[] menCall = {"普通", "少なめ", "半分", "多め", "マシマシ"};
    Button[] buttons = new Button[5];

    Button decisionButton;

    HashMap<String, String> callList = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        for (int i = 0; i < buttonId.length; i++) {
            final String title = titles[i];
            final String message = messages[i];
            final int callType = i;


            buttons[i] = findViewById(buttonId[i]);
            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    InputDialogFragment fragment = InputDialogFragment.newInstance(title, message,menCall, masiCall,callType);
                    fragment.show(getSupportFragmentManager(), "input_dialog");
                }
            });
        }

        decisionButton = findViewById(R.id.button2);
        decisionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InputActivity.this, TrainingActivity.class);
                intent.putExtra("callList",callList);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onPositiveClick(int call, String callItem) {
        String callType;
        switch (call) {
            case 0:
                callType = "men";
                break;
            case 1:
                callType = "yasai";
                break;
            case 2:
                callType = "abura";
                break;
            case 3:
                callType = "garlic";
                break;
            default:
                callType = "men";
                break;
        }
        callList.put(callType,callItem);

    }

    @Override
    public void onNegativeClick() {

    }
}
