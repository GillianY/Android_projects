package com.gmail.gina.gillian_02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

public class ClockActivity extends AppCompatActivity {

    private Button btn1start ;
    private Button btn1restart ;
    private Button btn1pause ;
    private Button btn1continue ;
    private Button btn2_start ;
    private Button btn2_end ;
    
    public TextView tvCountDown;
    public TextView tvClock;

    private int counter;
    private Date now;
    private Timer timer;
    private Timer timer_count;
    private int Status;

  private enum status{
      START,RESTART,PAUSE,
  }


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

        btn2_start.setEnabled(true);
        btn2_end.setEnabled(false);
      
    }

    private void initHandler() {
        counter=0;
        now = new Date();

        //countdown
        /*
        timer_count = new Timer();
        timer_count.schedule(new TimerTask() {
            @Override
            public void run() {


            }
        },0,100);*/


        //clock
        btn2_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Timer  :scheduleAtFixedRate
                btn2_end.setEnabled(true);

                timer = new Timer();
                timer.schedule(new TimerTask(){
                   public void run() {
                       updateUI();
                    }
                } ,0,1000 );

                btn2_start.setEnabled(false);
            }
        });

        btn2_end.setOnClickListener(new View.OnClickListener(
        ) {
            @Override
            public void onClick(View v) {

                Log.i("Gina","end btn");
                clearTimer();
                btn2_start.setEnabled(true);
                btn2_end.setEnabled(false);

            }
        });


        // strat countdount;
        
    }


    private void clearTimer(){
        timer.cancel(); //cancel timer
        timer.purge();
    }

    private String getDateString(){
        now = new Date();

        SimpleDateFormat day  = new SimpleDateFormat("yyyy/MM/dd a HH:mm:ss.SSS");
        day.setTimeZone(TimeZone.getTimeZone("Asia/Taipei"));
        return day.format(now);
    }
    private void updateUI()
    {
        runOnUiThread( new Runnable(){
            @Override
            public void run() {
              //  outer.this.tvClock.setText();
              ClockActivity.this.tvClock.setText(getDateString());
            }

    });

    }


}
