package com.gmail.gina.m14_voice;

import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private ImageButton imgbtn;
    private EditText etinput;

    private static final int VOICECODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initHandler();

    }

    private void initHandler() {
        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
               // it.setClass(MainActivity.this, class);
                it.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                it.putExtra(RecognizerIntent.EXTRA_PROMPT,"請說");
                startActivityForResult(it,VOICECODE);

            }
        });
    }

    private void initView() {
        imgbtn= (ImageButton)findViewById(R.id.btn_voice);
        etinput=(EditText)findViewById(R.id.et_input);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==VOICECODE)
        {
            ArrayList<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if( (results != null) && results.size()>0)
            {
                etinput.setText(results.get(0));
            }
        }

    }
}
