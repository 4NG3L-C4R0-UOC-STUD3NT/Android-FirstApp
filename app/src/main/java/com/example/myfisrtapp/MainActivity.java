package com.example.myfisrtapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.MenuCompat;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener, TextWatcher {

    // any number but should be unique
    final int STORAGE_PERMISSION = 1;

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



        RelativeLayout ll = (RelativeLayout)findViewById(R.id.mainLayout);

        Button clearButton = new Button(this);
        clearButton.setText("Clear");
        clearButton.setId(R.id.clearButton);
        RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        clearButton.setLayoutParams(params1);
        ll.addView(clearButton);
        params1.addRule(RelativeLayout.LEFT_OF, R.id.okButton);
        params1.addRule(RelativeLayout.BELOW, R.id.name);
        clearButton.setVisibility(View.INVISIBLE);
        clearButton.setOnClickListener(this);

        Button saveButton = new Button(this);
        saveButton.setText("Save");
        saveButton.setId(R.id.saveButton);
        RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        saveButton.setLayoutParams(params2);
        ll.addView(saveButton);
        saveButton.setOnClickListener(this);

        Button openCameraButton = new Button(this);
        openCameraButton.setText("Camera");
        openCameraButton.setId(R.id.openCameraButton);
        RelativeLayout.LayoutParams params3 = new RelativeLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        openCameraButton.setLayoutParams(params3);
        ll.addView(openCameraButton);
        params3.addRule(RelativeLayout.RIGHT_OF, R.id.saveButton);
        openCameraButton.setOnClickListener(this);

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
    protected void onPause() {
        Toast.makeText(this, "Please don't leave!", Toast.LENGTH_SHORT).show();
        super.onPause();
    }

    @Override
    protected void onStop() {
        Toast.makeText(this, "OnStop!", Toast.LENGTH_SHORT).show();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Toast.makeText(this, "OnStop!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        //Toast.makeText(this, "OnDestroy!", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        MenuCompat.setGroupDividerEnabled(menu, true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.mnuNewUser:
                Toast.makeText(this, "Add new User item clicked", Toast.LENGTH_SHORT).show();
                //addNewUser();
                return true;
            case R.id.mnuSettings:
                Toast.makeText(this, "Settings item clicked", Toast.LENGTH_SHORT).show();
                //showSettings();
                return true;
            case R.id.mnuAbout:
                Toast.makeText(this, "About item clicked", Toast.LENGTH_SHORT).show();
                //showAbout();
                return true;
            case R.id.mnuHelp:
                Toast.makeText(this, "Help item clicked", Toast.LENGTH_SHORT).show();
                //showHelp();
                return true;
            case R.id.mnuShowCompleted:
            case R.id.mnuShowPending:
            case R.id.mnuShowCanceled:
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    boolean checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                if (shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    Toast.makeText(this, "Need write to SD card permission to write file to SD Card", Toast.LENGTH_SHORT).show();
                }
                requestPermissions(new String[] { Manifest.permission.WRITE_EXTERNAL_STORAGE }, STORAGE_PERMISSION);
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case STORAGE_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission Granted!!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Permission Denied!!", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    void writeToFile() {
        if (checkPermission()) {
            File sdCard =
                    (Build.VERSION.SDK_INT > Build.VERSION_CODES.Q) ? getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS) : Environment.getExternalStorageDirectory();
            File file = new File(sdCard, "hello-world.txt");
            try {
                FileOutputStream outputStream = new FileOutputStream(file);
                String hello = "Hello world";
                outputStream.write(hello.getBytes());
                outputStream.close();
                Toast.makeText(this, "Success!!!", Toast.LENGTH_SHORT).show();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
                Toast.makeText(this, "Error writing to File!!", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Error writing to File!!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Permission denied, can't write to file!!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {
        // for memory optimizations purposes, use this form to
        // develop events (instead of firs version commented above)
        switch (view.getId()) {
            case R.id.openCameraButton:
                Toast.makeText(this, "Open Camera was clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.saveButton:
                //Toast.makeText(this, "Save button was clicked", Toast.LENGTH_SHORT).show();
                writeToFile();
                break;
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