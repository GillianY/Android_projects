package com.example.administrator.m26_sqlite;

import java.io.ByteArrayOutputStream;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import datatclass.Pub;
import datatclass.PubDAO;

public class EditorActivity extends Activity {
    //UI
    private EditText nameText;
    private EditText addressText;
    private ImageView image;
    private Button newBtn;
    private Button updateBtn;
    private Button deleteBtn;
    private Pub current;
    private PubDAO dao;

    //Data
    int sid ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        initView();
        initHandler();


        Intent it = getIntent();
        sid = it.getIntExtra("SID",-1);
        dao = new PubDAO(this);
        if(sid == -1) // new
        {
            updateBtn.setVisibility(View.GONE);
            deleteBtn.setVisibility(View.GONE);
            current = new Pub();
        }else
        {
            newBtn.setVisibility(View.GONE);
            current = dao.getPubBySid(sid);
            nameText.setText(current.getName());
            addressText.setText(current.getAddress());
            byte[] blob = current.getPhotop();
            Bitmap bmp = BitmapFactory.decodeByteArray(blob,0,blob.length);
            image.setImageBitmap(bmp);
        }

    }



    private void initHandler() {
        newBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                collectDataFromUI();
                dao.insert(current);
                finish();
            }
        });
        updateBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                collectDataFromUI();
                dao.update(current);
                finish();
            }


        });
        deleteBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dao.delete(current);
                finish();

            }
        });
    }

    private void collectDataFromUI() {
        current.setName(nameText.getText().toString());
        current.setAddress(addressText.getText().toString());
        image.setDrawingCacheEnabled(true);
        image.buildDrawingCache();
        Bitmap bm = image.getDrawingCache();
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        bm.compress(CompressFormat.PNG, 100, bao);
        byte[] ba = bao.toByteArray();
        current.setPhotop(ba);
    }


    private void initView() {
        nameText = (EditText) findViewById(R.id.et_name);
        addressText = (EditText) findViewById(R.id.et_address);
        image = (ImageView) findViewById(R.id.image);
        newBtn = (Button) findViewById(R.id.btn_new);
        updateBtn = (Button) findViewById(R.id.btn_update);
        deleteBtn = (Button) findViewById(R.id.btn_delete);


    }



}
