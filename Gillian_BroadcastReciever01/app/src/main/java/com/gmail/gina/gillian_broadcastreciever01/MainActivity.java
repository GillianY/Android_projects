package com.gmail.gina.gillian_broadcastreciever01;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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

public class MainActivity extends AppCompatActivity {
    private Button btn_m_start;
    private Button btn_m_pause;
    private Button btn_m_stop;
    private Button btn_clock_start;
    private Button btn_clock_pause;
    private TextView tv_clock;
    private Timer timer1 ;
    private boolean isRunning =false;
    private String date="";

    private BroadcastReceiver br ;

    private final static String ACTION_KEY ="com.mymusic";
    private final static String DATA ="COMMAND";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent it = new Intent();
        it.setClass(MainActivity.this, MyMusicService.class);
        it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startService(it);

        initView();
        initHander();


        //TODO  recieve clock
        br = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String data = intent.getStringExtra("DATE");
                if(data.equals("DATE"))
                {
                    date = data;
                }else
                {
                    date="2000-01-01";
                }
            }
        };
        registerReceiver(br,new IntentFilter("com.counter"));

        for(;isRunning;)
        {
            updateUI2(date);
        }
        Log.i("Gina","hello");
    }

    private void initHander() {
        /*
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(;isRunning;)
                {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread1.start();
        */

        btn_m_start.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent it = new Intent(ACTION_KEY);
                        it.putExtra(DATA,"PLAY");
                        sendBroadcast(it);

                        v.setEnabled(false);
                        btn_m_pause.setEnabled(true);
                        btn_m_stop.setEnabled(true);

                    }
                }
        );

        btn_m_pause.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent it = new Intent(ACTION_KEY);
                        it.putExtra(DATA,"PLAY");
                        sendBroadcast(it);

                        v.setEnabled(false);
                        btn_m_start.setEnabled(true);
                        btn_m_stop.setEnabled(true);

                    }
                }
        );
        btn_m_stop.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent it = new Intent(ACTION_KEY);
                        it.putExtra(DATA,"PLAY");
                        sendBroadcast(it);

                        v.setEnabled(false);
                        btn_m_pause.setEnabled(false);
                        btn_m_start.setEnabled(true);
                    }
                }
        );
        btn_clock_start.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // timerTaks or thread
                        timer1 = new Timer();
                        timer1.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                updateUI();
                            }
                        },0,1000);
                        v.setEnabled(false);
                        btn_clock_pause.setEnabled(true);
                    }
                });

        btn_clock_pause.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clearTimer();
                        v.setEnabled(false);
                        btn_clock_start.setEnabled(true);
                    }
                }
        );

     /*
        btn_clock_start.setOnClickListener(
                //thread method
                new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                                isRunning= true;
                                v.setEnabled(false);
                                btn_clock_pause.setEnabled(true);
                       }
                });


                btn_clock_pause.setOnClickListener(
                        //thread method
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                isRunning= false;
                                v.setEnabled(false);
                                btn_clock_start.setEnabled(true);
                            }
                        }
                );
                */
    }

    private void initView() {
        btn_m_start = (Button) findViewById(R.id.btnMusicPlay);
        btn_m_pause  = (Button) findViewById(R.id.btnMusicPAUSE);
        btn_m_stop = (Button) findViewById(R.id.btnMusicStop);
        btn_clock_start  = (Button) findViewById(R.id.btnClockPlay);
        btn_clock_pause  = (Button) findViewById(R.id.btnClockPAUSE);
        tv_clock =(TextView)findViewById(R.id.tv_clock);

        btn_m_pause.setEnabled(false);
        btn_m_stop.setEnabled(false);
        btn_clock_pause.setEnabled(false);
        timer1 = null;

    }


    @Override
    protected void onDestroy() {
        clearTimer();
        super.onDestroy();
    }

    void clearTimer(){
        if(timer1!=null)
        {
            timer1.cancel();
            timer1.purge();
        }

    }
    void updateUI(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tv_clock.setText(getDate());
                Log.i("gina","updateUI");
            }
        });
    }

    void updateUI2(final String date){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tv_clock.setText(date);
                Log.i("gina","updateUI");
            }
        });
    }

    private String getDate() {
        SimpleDateFormat dformat = new SimpleDateFormat("yyyy/MM/dd a HH/mm/ss.SSS");
        dformat.setTimeZone(TimeZone.getTimeZone("Asia/Taipei"));
        return dformat.format( new Date());
    }
}
