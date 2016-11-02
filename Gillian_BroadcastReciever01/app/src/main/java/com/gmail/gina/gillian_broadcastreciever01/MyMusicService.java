package com.gmail.gina.gillian_broadcastreciever01;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class MyMusicService extends Service {
    private BroadcastReceiver br;
    private  MediaPlayer player1;
    private String ACTION_KEY ="com.counter";
    private String DATA ="DATE";
    public boolean isRunning = true;
    public MyMusicService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Thread counter_thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Intent it = new Intent(ACTION_KEY);
                for(;isRunning;)
                {
                    try {
                        Thread.sleep(1000);
                        // send intent
                        // counter
                        it.putExtra(DATA,getDate()); //sen
                        sendBroadcast(it);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        counter_thread.start();



        player1 = MediaPlayer.create(this,R.raw.theshow);
        player1.setVolume(1,1);
        try {
            player1.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // player1.start();
        br = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String cmd = intent.getStringExtra("COMMAND");
                Log.i("Gina","CMD"+cmd);
                if(cmd.equals("PLAY"))
                {
                    Log.i("Gina","start music");
                    player1.start();
                }else if(cmd.equals("PAUSE"))
                {
                    player1.pause();
                }else if(cmd.equals("STOP"))
                {
                    player1.stop();
                    player1 = MediaPlayer.create(MyMusicService.this,R.raw.theshow);
                    player1.setVolume(1,1);
                    try {
                        player1.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        };
        //!!!!!
        registerReceiver(br, new IntentFilter("com.mymusic"));

        Log.i("Gina","onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        if(br != null)
        {unregisterReceiver(br);}
        if(player1 != null)
        {player1.stop();}
        Log.i("Gina","onDestroy");
        super.onDestroy();
    }

    private String getDate() {
        SimpleDateFormat dformat = new SimpleDateFormat("yyyy/MM/dd a HH/mm/ss.SSS");
        dformat.setTimeZone(TimeZone.getTimeZone("Asia/Taipei"));
        return dformat.format( new Date());
    }
}
