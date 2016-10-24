package com.gmail.gina.m09_lifecycle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    public static final String TAG="lc";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Locale[] locales = Locale.getAvailableLocales();
        for(Locale loc : locales)
        {
            System.out.println(loc.getCountry()+"_"+loc.getLanguage()+"_"+loc.getDisplayCountry()+"_"+loc.getDisplayLanguage()+"__"+loc.getDisplayName());
        }

        Log.i(TAG,"onCreate.....");
        Log.d(TAG,"Debug.....");
        Log.e(TAG,"Error.....");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG,"onRestart.....");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG,"onStart.....");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG,"onResume.....");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG,"onPause.....");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG,"onStop.....");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"onDestroy.....");
    }
}
