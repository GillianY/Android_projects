package com.example.administrator.m25_http;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    /*
    *  http://ikenapp.appspot.com/json/postProductsByCategory.jsp?cateId=2
    * */
    //UI
    private Spinner spinner;
    private Button get;
    private Button post;
    private TextView display;
    //Data
    private String[] data;
    private int selectedIndex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initHandler();
    }

    private void initHandler() {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedIndex = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread t = new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        processHttpGetMethod();
                    }
                };
                t.start();

            }
        });
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        processHttpPostMethod();

                    }
                }).start();

            }
        });
    }

    private void processHttpPostMethod() {


        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost("http://ikenapp.appspot.com/json/postProductsByCategory.jsp");
        try {
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
            nameValuePairs.add(new BasicNameValuePair("cateId", ""+(selectedIndex+1)));
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
// Execute HTTP Post Request
            HttpResponse response = httpclient.execute(httppost);
              final String  str = EntityUtils.toString(response.getEntity(),"utf-8");
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    display.setText(str);
                    display.setMovementMethod(ScrollingMovementMethod.getInstance());
                }
            });

        } catch (Exception e) {
            Log.i("Gina",e.toString());
        }

    }

    private void processHttpGetMethod() {
        HttpClient httpclient = new DefaultHttpClient();
        String url = "https://www.dropbox.com/s/7z1530esmb6gtlq/myjson.txt?dl=1";// //("http://ikenapp.appspot.com/json/getCategory.jsp");
        try {
            HttpResponse response = httpclient.execute(new HttpGet(url));
            final String  str = EntityUtils.toString(response.getEntity(),"utf-8");
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    display.setText(str);
                }
            });
        } catch (Exception e) {
            Log.e("GET", e.getMessage());
        }
    }

    private void initView() {
        spinner = (Spinner) findViewById(R.id.spinner);
        get = (Button) findViewById(R.id.btn_get);
        post = (Button) findViewById(R.id.btn_post);
        display = (TextView) findViewById(R.id.tv_display);

        data = new String[]{"1","2","3","4","5","6"};
        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,
                data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);



    }
}
