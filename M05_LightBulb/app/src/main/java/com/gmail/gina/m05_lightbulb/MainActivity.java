package com.gmail.gina.m05_lightbulb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {
    ImageView img_on;
  //  ImageView img_off;
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
         sk_bar =(SeekBar)findViewById(R.id.br_opacity) ; //
         switch1 =(Switch)findViewById(R.id.switch1);

      //   sk_bar.setEnabled(false);
        // img_on.setVisibility(View.INVISIBLE); //View.GONE View.VISIBLE
        // 0 = transparent and 1 = visible   alpha: so 0.5 is half way visible



    }

    private void initHandler() {
       // power.setOnCheckedChangeListener
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 sk_bar.setEnabled(isChecked);
                 img_on.setVisibility(isChecked? View.VISIBLE: View.INVISIBLE);

            }
        });


        sk_bar.setOnSeekBarChangeListener( new SeekBar.OnSeekBarChangeListener(){
            @Override
                 public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    float pro =  progress/(float)sk_bar.getMax();
                img_on.setAlpha(pro);

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
