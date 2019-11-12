package com.example.vijaya.androidhardware;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class StorageActivity extends AppCompatActivity {
    EditText txt_content;
    EditText contenttoDisplay;
    String FILENAME = "MyAppStorage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);
        txt_content = (EditText) findViewById(R.id.id_txt_mycontent);
        contenttoDisplay = (EditText) findViewById(R.id.id_txt_display);
    }

    public void saveTofile(View v) throws IOException {

        // ICP Task4: Write the code to save the text
        FileOutputStream fileOutputStream = openFileOutput(FILENAME, Context.MODE_APPEND);
        String msg = txt_content.getText().toString();
        fileOutputStream.write(msg.getBytes());

        fileOutputStream.close();
        

    }

    public void retrieveFromFile(View v) throws IOException {

        // ICP Task4: Write the code to display the above saved text
        FileInputStream fileInputStream = openFileInput(FILENAME);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String receivestring = "";
        StringBuilder stringBuilder = new StringBuilder();
        while ((receivestring = bufferedReader.readLine()) != null){
            stringBuilder.append(receivestring);
        }

        fileInputStream.close();
        String text = stringBuilder.toString();
        contenttoDisplay.setText(text);
        contenttoDisplay.setVisibility(View.VISIBLE);

    }
}
