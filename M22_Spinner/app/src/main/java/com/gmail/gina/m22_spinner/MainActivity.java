package com.gmail.gina.m22_spinner;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private Button btnok;
    private Spinner spinner1;
    private int selectedIndex;
    private String[] codeArray=null;

    private LinearLayout  layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initeView();
        initHandler();
    }

    private void initHandler() {
        btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = ((ViewGroup)v.getParent()).getChildAt(0);
               // LinearLayout layout = (LinearLayout) findViewById(R.id.activity_main);
                if(selectedIndex >0)
                {layout.setBackgroundColor(Color.parseColor(codeArray[selectedIndex]));}
               // Toast.makeText(MainActivity.this,view.getId(),Toast.LENGTH_LONG ).show();


            }
        });
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,"your selectiotn:"+position +codeArray[position],Toast.LENGTH_LONG ).show();
                selectedIndex = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    private void initeView() {
        btnok = (Button)findViewById(R.id.button1);
        spinner1 =(Spinner) findViewById(R.id.spinner1);
        codeArray = getResources().getStringArray(R.array.ColorCodes);
        layout = (LinearLayout) findViewById(R.id.activity_main);


        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,R.array.ColorNames,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);
        spinner1.setBackgroundColor(Color.parseColor("#ffffff"));

    }
}
