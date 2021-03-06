package com.gmail.gina.m20_media_services;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initHander();
    }

    private void initHander() {
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
    }
}
