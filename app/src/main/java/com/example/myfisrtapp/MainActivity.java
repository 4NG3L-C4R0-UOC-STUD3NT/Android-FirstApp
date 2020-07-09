package com.example.myfisrtapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView goodMorning = findViewById(R.id.goodMorning);
        goodMorning.setOnClickListener(this);
        goodMorning.setOnLongClickListener(this);

        goodMorning.setText("testing");

        // R.id contains all app ids
        // and the id is unique in the entire app.
        // it is not possible to have two controls using the same id.

        //goodMorning.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        //        goodMorning.setText("Welcome!!");
        //        goodMorning.setTextColor(0xFF00FF00);
        //    }
        //});

    }

    @Override
    public void onClick(View view) {
        // for memory optimizations purposes, use this form to
        // develop events (instead of firs version commented above)
        switch (view.getId()) {
            case R.id.goodMorning:
                TextView goodMorning = (TextView)view;
                goodMorning.setText("Welcome!!");
                goodMorning.setTextColor(0xFF00FF00);
                break;
        }
    }

    @Override
    public boolean onLongClick(View view) {
        switch (view.getId()) {
            case R.id.goodMorning:
                TextView goodMorning = (TextView)view;
                goodMorning.setText("long click!!");
                goodMorning.setTextColor(0xFF063F00);
                break;
        }
        return true;
    }
}