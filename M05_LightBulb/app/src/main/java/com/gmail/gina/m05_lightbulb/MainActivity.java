package com.gmail.gina.m05_lightbulb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {
    ImageView img_on;
    ImageView img_off;
    SeekBar sk_bar ; //
    Switch switch1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initHandler();

    }

    private void initView() {
         img_on =(ImageView)findViewById(R.id.img_on);
         img_off =(ImageView)findViewById(R.id.img_off);
         sk_bar =(SeekBar)findViewById(R.id.br_opacity) ; //
         switch1 =(Switch)findViewById(R.id.switch1);

        sk_bar.setEnabled(false);
       // img_on.
        img_on.setVisibility(View.GONE); //View.GONE View.VISIBLE
        // 0 = transparent and 1 = visible   alpha: so 0.5 is half way visible



    }

    private void initHandler() {



        switch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img_on.setVisibility(View.VISIBLE);
                sk_bar.setEnabled(true);

            }
        });
        sk_bar.setOnSeekBarChangeListener( new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                } );
    }
}
