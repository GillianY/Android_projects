package com.gmail.gina.m13_activitycommunication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class NextActivity extends AppCompatActivity {


    private Button btn_submit ;
    private TextView tv_name;
    private EditText etnewName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
Log.i("Gina","hi");
        Intent it =  getIntent();
      /*  Bundle datas = it.getExtras();  // if null ,app break
        String name = (String)datas.get("Name");
        String age = (String)datas.get("Age");
        */
        String newname = it.getStringExtra("New");
        String age2= it.getStringExtra("Age2");
       // Log.i("Gina","name:"+name+"__"+age+"_"+newname+"__"+age2);

        Log.i("Gina","hi2");

        initNew();
        initHandler();

     }

    private void initHandler() {
         Log.i("Gina", "back");
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent();
                it.setClass(NextActivity.this, MainActivity.class);
                it.putExtra("MSG",etnewName.getText().toString());
               // startActivity(it);
                setResult(RESULT_OK,it);
                finish(); // finish this activity
            }
        });
    }

    private void initNew() {

        btn_submit = (Button) findViewById(R.id.bt_submit);
        etnewName = (EditText) findViewById(R.id.et_newName);
    }
}
