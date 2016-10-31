package com.example.administrator.m19_animation;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private Button show;
    private View panel;
    private ImageButton imgBtn;
    private ImageView img ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initHandler();
    }

    private void initHandler() {
        show.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                v.setVisibility(View.GONE);
                panel.setVisibility(View.VISIBLE);
                Animation anmi = AnimationUtils.loadAnimation(MainActivity.this,R.anim.anim_show );
                panel.setAnimation(anmi);

                AnimationDrawable anim = (AnimationDrawable)img.getDrawable();
                anim.start();
            }
        });
        imgBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                panel.setVisibility(View.INVISIBLE);
                Animation anmi = AnimationUtils.loadAnimation(MainActivity.this,R.anim.anim_disappear);
                panel.setAnimation(anmi);
                show.setVisibility(View.VISIBLE);
            }
        });
    }

    private void initView() {
        // TODO Auto-generated method stub
        show = (Button) findViewById(R.id.btn_show);
        panel = findViewById(R.id.messagePanel);
        imgBtn = (ImageButton) findViewById(R.id.imageButton1);
        img = (ImageView) findViewById(R.id.imageView);

        panel.setVisibility(View.INVISIBLE);
    }

}
