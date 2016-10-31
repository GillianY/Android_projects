package com.gmail.gina.m20_media_services;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

public class MyMusicService extends Service {
    MediaPlayer player1;
    private BroadcastReceiver br;
    public MyMusicService() {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(player1 != null)
        {
            player1.stop();
        }
        if(br != null)
        {
            unregisterReceiver(br);
        }
        Log.i("gina","onDestroy");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("gina","onStartCommand");
        player1 = MediaPlayer.create(this, R.raw.theshow);
        player1.setVolume(1,1);
        try {
          //    player1.prepare();
        } catch (Exception e)
        { e.printStackTrace();
        }
       // player1.start();

        br = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String cmd = intent.getStringExtra("COMMAND");
                if(cmd.equals("PLAY"))
                {
                    player1.start();
                    Log.i("gina","PLAY");
                }else if(cmd.equals("PAUSE"))
                {
                    player1.pause();
                    Log.i("gina","PAUSE");
                }
                else if(cmd.equals("STOP"))
                {
                    player1.stop();
                    player1 = MediaPlayer.create(MyMusicService.this, R.raw.theshow);
                    player1.setVolume(1,1);
                    try {
                        player1.prepare();
                    } catch (Exception e)
                    { e.printStackTrace();
                    }
                    Log.i("gina","STOP");

                }
            }
        };
        registerReceiver(br, new IntentFilter("com.playmusic"));
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
