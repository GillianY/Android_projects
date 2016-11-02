package com.example.administrator.m26_sqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import adapter.MyCellAdapter;
import datatclass.Pub;
import datatclass.PubDAO;

public class MainActivity extends AppCompatActivity {
    //UI
    private EditText condition;
    private Button query;
    private Button add;
    private ListView listView;
    PubDAO dao ;
    ArrayList<Pub> list ;
    //Data
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dao = new PubDAO(this);
        list = dao.getAllPubs();
        Log.i("Gina",list.size()+"");
        list = dao.getAllPubsByName("Trio");
        Log.i("Gina",list.size()+"");
        Pub p = dao.getPubBySid(2);
        Log.i("Gina",p.getName()+":"+p.getAddress());

        initView();
        initHandler();
    }

    @Override
    protected void onResume() {
        super.onResume();
        String queryText = condition.getText().toString();
        Log.i("Gina_", ""+queryText.length());
        if(queryText.length()>0)
        {
            list = dao.getAllPubsByName(queryText);
        }else
        {list = dao.getAllPubs();}
        MyCellAdapter adapter = new MyCellAdapter(this,list);
        listView.setAdapter(adapter);
    }



    private void initHandler() {
        query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String queryText = condition.getText().toString();
                if(queryText.length()>0)
                {
                    list = dao.getAllPubsByName(queryText);
                }else
                {
                    list = dao.getAllPubs();
                }
                MyCellAdapter adapter = new MyCellAdapter(MainActivity.this, list);
                listView.setAdapter(adapter);

            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent();
                it.setClass(MainActivity.this, EditorActivity.class);
                startActivity(it);
            }
        });
    }

    private void initView() {
        condition = (EditText) findViewById(R.id.et_condition);
        query = (Button) findViewById(R.id.btn_query);
        add = (Button) findViewById(R.id.btn_add);
        listView = (ListView) findViewById(R.id.lv_pubs);
    }
}

