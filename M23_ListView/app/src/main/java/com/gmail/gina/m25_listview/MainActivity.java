package com.gmail.gina.m25_listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private ListView listView;
    private String[] PPAP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initHandler();
    }

    private void initHandler() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,"U Select : "+PPAP[position],Toast.LENGTH_LONG).show();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,"U Long Select : "+PPAP[position],Toast.LENGTH_LONG).show();

                return false;  // 不穿透  ex: e.stopPropagation()
            }
        });
    }

    private void initView() {
        listView= (ListView)findViewById(R.id.listview);
        PPAP = getResources().getStringArray(R.array.PPAP);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,PPAP);
        listView.setAdapter(adapter);
    }
}
