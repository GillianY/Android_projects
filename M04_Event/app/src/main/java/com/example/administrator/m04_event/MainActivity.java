package com.example.administrator.m04_event;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.* ;


public class MainActivity extends AppCompatActivity {
    private EditText nameInput;
    private Button btn;
    private TextView display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initHandler();
    }

    private void initHandler() {
        btn.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        String Iptname = nameInput.getText().toString();
                        display.setText("Hi "+Iptname);
                    }
                } );


    }

    private void initView() {
        nameInput =(EditText)findViewById(R.id.et_name);
        btn =( Button)findViewById(R.id.bt_show);
          display = (TextView)findViewById(R.id.tv_diaplay);

        btn.setText("Go");
        display.setText("welcome");
    }
}
