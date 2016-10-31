package com.gmail.gina.m20_media_services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

public class MyMusicService extends Service {
    MediaPlayer player1;
    public MyMusicService() {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(player1 != null)
        {
            player1.stop();
        }
        Log.i("gina","onDestroy");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("gina","onStartCommand");
        player1 = MediaPlayer.create(this, R.raw.theshow);
        player1.setVolume(1,1);
        try {
              player1.prepare();
        } catch (Exception e)
        { e.printStackTrace();
        }
        player1.start();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
