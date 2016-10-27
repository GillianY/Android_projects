package com.gmail.gina.gillian_02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class ClockActivity extends AppCompatActivity {

    private Button btn1start ;
    private Button btn1restart ;
    private Button btn1pause ;
    private Button btn1continue ;
    private Button btn2_start ;
    private Button btn2_end ;
    
    private TextView tvCountDown;
    private TextView tvClock;

    private int counter;
    private Date new;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);
        initView();
        initHandler();

    }

    

    private void initView() {
          btn1start =(Button)findViewById(R.id.btnstart);
          btn1restart =(Button)findViewById(R.id.btnrestart) ;
          btn1pause =(Button)findViewById(R.id.btnpause);
          btn1continue =(Button)findViewById(R.id.btncontinue);
          btn2_start=(Button)findViewById(R.id.btn2start) ;
          btn2_end=(Button)findViewById(R.id.btn2end) ;

          tvCountDown =(TextView) findViewById(R.id.tv_countdown);
          tvClock =(TextView) findViewById(R.id.tv_clock);
      
    }

    private void initHandler() {



        btn1start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Timer  :scheduleAtFixedRate
                Timer clocktimer = new Timer();
                TimerTask ts = new TimerTask(){};
                
                
            }
        });


        // strat countdount;
        
    }
}
