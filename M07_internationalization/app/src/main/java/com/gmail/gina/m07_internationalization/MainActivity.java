package com.gmail.gina.m07_internationalization;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tv1 ;
    private Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initHndler();
    }

    private void initView() {
         tv1 = (TextView)findViewById(R.id.tV_msg);
         String text = getString(R.string.pumkinMsg);
         tv1.setText(text);
         btn1 = (Button) findViewById(R.id.btn_pumpkin);
    }

    private void initHndler() {
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
