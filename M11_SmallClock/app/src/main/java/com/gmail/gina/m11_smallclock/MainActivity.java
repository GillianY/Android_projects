package com.gmail.gina.m11_smallclock;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.*;
import java.text.*;

public class MainActivity extends AppCompatActivity {

    private TextView tv_display;

    private String Tag;
    private SimpleDateFormat sdFormat;
    private boolean isRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Tag ="CLOCK";
        isRunning = true;

        initView();
        initHandler();
    }

    private void initHandler() {
    }

    private void initView() {
        tv_display = (TextView)findViewById(R.id.tvclock);

       // seconds= (current.getTime()/1000);
         Thread  t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(;isRunning;)
                {
                   updateUI( new Date());
                    try {
                        Thread.sleep(1000);
                    }catch(Exception e)
                    {
                        Log.e(Tag,"thread sleep error");
                    }
                }
            }
        });
        t1.start();
    }

    private String getDateString() {
        Date date = new Date();
        sdFormat = new SimpleDateFormat("yyyy/MM/dd a HH:mm:ss.SSS");
        sdFormat.setTimeZone(TimeZone.getTimeZone("Asia/Taipei"));
        return sdFormat.format(date) ;
    }

    private void updateUI(final Date date) { //!!!!FINAL
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tv_display.setText(getDateString());
                //tv_display.setText(sdFormat.format(date));
               // Log.i(Tag,sdFormat.format( new Date()));

            }
        });



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isRunning = false;
        Log.e(Tag,"onDestroy....");
    }
}
