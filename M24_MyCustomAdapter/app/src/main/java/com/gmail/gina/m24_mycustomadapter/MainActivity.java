package com.gmail.gina.m24_mycustomadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.gmail.gina.m24_mycustomadapter.adapter.MyCellAdapter;
import com.gmail.gina.m24_mycustomadapter.data.User;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView listview;
    private ArrayList<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initHandler();
    }

    private void initHandler() {
    }

    private void initView() {
        listview =(ListView) findViewById(R.id.listview);
        users = new ArrayList<User>();
        users.add(new User("Gillian","Milano",R.mipmap.apple));
        users.add(new User("Galliano","Paris",R.mipmap.bananas));
        users.add(new User("Gina","NewYork",R.mipmap.carrot));
        users.add(new User("Sabrina","Kyoto",R.mipmap.grapes));
        users.add(new User("Bergamo","Taipei",R.mipmap.pear));
        MyCellAdapter adapter = new MyCellAdapter(this,users);
        listview.setAdapter(adapter);


    }
}
