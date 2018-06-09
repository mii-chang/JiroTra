package com.miyuu.android.jirotra;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.util.HashMap;

public class TrainingActivity extends AppCompatActivity {

    TextView calText, jogingu, hukkin, udetate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);
        Intent i = getIntent();
        HashMap<String, Object> hashMap = (HashMap<String, Object>) i.getSerializableExtra("callList");
        Log.e("map: ", hashMap + "");
        String garlic = hashMap.get("garlic").toString();
        String yasai = hashMap.get("yasai").toString();
        String abura = hashMap.get("abura").toString();
        String men = hashMap.get("men").toString();

        int garlicCal = 0;
        int yasaiCal = 0;
        int aburaCal = 0;
        int menCal = 0;
        int totalCal = 0;

        switch (garlic) {
            case "なし":
                garlicCal = 0;
                break;
            case "少なめ":
                garlicCal = 50;
                break;
            case "普通":
                garlicCal = 100;
                break;
            case "マシ":
                garlicCal = 150;
                break;
            case "マシマシ":
                garlicCal = 200;
                break;
        }

        switch (yasai) {
            case "なし":
                yasaiCal = 0;
                break;
            case "少なめ":
                yasaiCal = 12;
                break;
            case "普通":
                yasaiCal = 18;
                break;
            case "マシ":
                yasaiCal = 24;
                break;
            case "マシマシ":
                yasaiCal = 36;
                break;
        }

        switch (abura) {
            case "なし":
                aburaCal = 0;
                break;
            case "少なめ":
                aburaCal = 200;
                break;
            case "普通":
                aburaCal = 300;
                break;
            case "マシ":
                aburaCal = 400;
                break;
            case "マシマシ":
                aburaCal = 500;
                break;
        }

        switch (men) {
            case "普通":
                menCal = 550;
                break;
            case "少なめ":
                menCal = 440;
                break;
            case "半分":
                menCal = 300;
                break;
            case "多め":
                menCal = 700;
                break;
            case "マシマシ":
                menCal = 1000;
                break;
        }

        totalCal = garlicCal + yasaiCal + aburaCal + menCal + 300;

        calText = findViewById(R.id.calTextView);
        calText.setText(totalCal + " kcal");

        String job1, job2, job3;


        jogingu = findViewById(R.id.jogingu);
        hukkin = findViewById(R.id.hukkin);
        udetate = findViewById(R.id.udetate);

        if (totalCal < 1000) {
            job1 = "ランニング60分";
            job2 = "水泳60分";
            job3 = "カラオケ60分";
        } else if (totalCal >= 1000 && totalCal < 1200) {
            job1 = "腹筋30分";
            job2 = "ウォーキング60分";
            job3 = "腕立て120分";
        } else if (totalCal >= 1200 && totalCal < 1400) {
            job1 = "ランニング90分";
            job2 = "腹筋60分";
            job3 = "スクワット60分";
        } else if (totalCal >= 1400 && totalCal < 1600) {
            job1 = "水泳90分";
            job2 = "ウォーキング120分";
            job3 = "腕立て60分";
        } else if (totalCal >= 1600 && totalCal < 1800) {
            job1 = "カラオケ120分";
            job2 = "ランニング120分";
            job3 = "水泳60分";
        } else if (totalCal >= 1800 && totalCal < 2000) {
            job1 = "腹筋60分";
            job2 = "水泳120分";
            job3 = "ウォーキング90分";
        } else if (totalCal >= 2000) {
            job1 = "ランニング120分";
            job2 = "水泳120分";
            job3 = "スクワット120分";
        }else {
            job1 = "ランニング120分";
            job2 = "水泳120分";
            job3 = "スクワット120分";
        }

        jogingu.setText(job1);
        hukkin.setText(job2);
        udetate.setText(job3);


    }
}
