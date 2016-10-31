package com.gmail.gina.m20_media_services;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btn1 ;
    private Button btn2 ;
    private Button btn_play;
    private Button btn_pause;
    private Button btn_stop;
    private String ACTION_KEY = "com.playmusic";
    private String DATA_KEY = "COMMAND";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initHander();

        Intent it = new Intent();
        it.setClass(MainActivity.this, MyMusicService.class);
        it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startService(it);
    }

    private void initHander() {
        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(ACTION_KEY);
                //it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                //it.setClass(MainActivity.this,MyMusicService.class);
                it.putExtra(DATA_KEY,"PLAY");
                sendBroadcast(it);
                v.setEnabled(false);
                btn_pause.setEnabled(true);
                btn_stop.setEnabled(true);
            }
        });
        btn_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(ACTION_KEY);
                it.putExtra(DATA_KEY,"PAUSE");
                sendBroadcast(it);
                v.setEnabled(false);
                btn_play.setEnabled(true);
                btn_stop.setEnabled(true);
            }
        });
        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(ACTION_KEY);
                it.putExtra(DATA_KEY,"STOP");
                sendBroadcast(it);
                v.setEnabled(false);
                btn_play.setEnabled(true);
                btn_pause.setEnabled(false);
            }
        });


        btn1.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        MediaPlayer player1 = MediaPlayer.create(MainActivity.this, R.raw.theshow);
                                        player1.setVolume(1,1);
                                        try {
                                          //   player1.prepare();
                                        } catch (Exception e)
                                        { e.printStackTrace();
                                        }
                                        player1.start();
                                        Log.i("gina","playing");
                                    }
                                }

        );
        btn2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent it = new Intent();
                        it.setClass(MainActivity.this, MyMusicService.class);
                        it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startService(it);
                    }
                }
        );
    }

    private void initView() {
        btn1 = (Button) findViewById(R.id.button);
        btn2 = (Button) findViewById(R.id.button2);
        btn1.setVisibility(View.GONE);
        btn2.setEnabled(false);

        btn_play=(Button) findViewById(R.id.btnplay);
        btn_pause =(Button) findViewById(R.id.btnpause);
        btn_stop =(Button) findViewById(R.id.btnstop);
    }
}
