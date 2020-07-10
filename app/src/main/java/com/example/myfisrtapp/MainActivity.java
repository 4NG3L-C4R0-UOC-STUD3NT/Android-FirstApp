package com.example.myfisrtapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener, TextWatcher {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView goodMorning = findViewById(R.id.goodMorning);
        goodMorning.setOnClickListener(this);
        goodMorning.setOnLongClickListener(this);

        TextView helloWorld = findViewById(R.id.helloWorld);
        helloWorld.setOnClickListener(this);
        helloWorld.setOnLongClickListener(this);

        TextView editControl = findViewById(R.id.name);
        editControl.addTextChangedListener(this);

        Button okButton = findViewById(R.id.okButton);
        okButton.setOnClickListener(this);
        okButton.setVisibility(View.INVISIBLE);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        Button clearButton = new Button(this);
        clearButton.setText("Clear");
        clearButton.setId(R.id.clearButton);
        clearButton.setLayoutParams(params);
        RelativeLayout ll = (RelativeLayout)findViewById(R.id.mainLayout);
        ll.addView(clearButton);
        params.addRule(RelativeLayout.LEFT_OF, R.id.okButton);
        params.addRule(RelativeLayout.BELOW, R.id.name);
        clearButton.setVisibility(View.INVISIBLE);

        //clearButton.setLayoutParams(params);

        //clearButton.Id("@+id/clearButton");

        //LinearLayout ll = (LinearLayout)findViewById(R.id.buttonlayout);
        //LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        //ll.addView(myButton, lp);

        //goodMorning.setText("testing");

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
                goodMorning.setText("You press click");
                goodMorning.setTextColor(0xFF00FF00);
                break;
            case R.id.helloWorld:
                TextView helloWorld = (TextView)view;
                helloWorld.setText("You press click");
                helloWorld.setTextColor(ResourcesCompat.getColor(getResources(), R.color.colorPurple, null));
                break;
            case R.id.okButton:
                TextView editControl = findViewById(R.id.name);
                String valueText = editControl.getText().toString();
                if (valueText != "") {
                    TextView textControl = findViewById(R.id.helloWorld);
                    textControl.setText(valueText);
                }
                break;
            case R.id.clearButton:
                TextView editControl2 = findViewById(R.id.name);
                editControl2.setText("");
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
            case R.id.helloWorld:
                TextView helloWorld = (TextView)view;
                helloWorld.setText("long click!!");
                helloWorld.setTextColor(ResourcesCompat.getColor(getResources(), R.color.colorPurple, null));
                break;
        }
        return true;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
        Button okButton = findViewById(R.id.okButton);
        okButton.setVisibility((count > 0) ? View.VISIBLE : View.INVISIBLE);
        Button clearButton = findViewById(R.id.clearButton);
        clearButton.setVisibility((count > 0) ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}