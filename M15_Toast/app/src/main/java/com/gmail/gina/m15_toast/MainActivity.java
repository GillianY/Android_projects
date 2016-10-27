package com.gmail.gina.m15_toast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Date;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {
    private Button btnToast ;
    private Button btncusPostion ;
    private Button btncusLayout ;
    private Button btnhardCoding ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initHandler();

    }

    private void initHandler() {
        btnToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,R.string.msg_toast,Toast.LENGTH_LONG).show();


            }
        });

        btncusPostion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Toast toast1 = Toast.makeText(MainActivity.this,"Custom posiosn",Toast.LENGTH_LONG);
                    toast1.setGravity(Gravity.CENTER,20,20);
                    toast1.show();
}});
        btncusLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = new Toast(MainActivity.this);
                toast.setGravity(Gravity.CENTER|Gravity.RIGHT,60,30);
                LayoutInflater inflat = getLayoutInflater();
                View view = inflat.inflate(R.layout.toast_custom,null);
                toast.setView(view);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.show();

            }
        });

        btnhardCoding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Toast toast =   Toast.makeText(MainActivity.this, "BY HARDCODING", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER|Gravity.LEFT,0,0);
                LinearLayout layout = (LinearLayout) toast.getView();
                ImageView img = new ImageView(MainActivity.this);
                img.setImageResource(R.mipmap.ic_launcher);
                layout.addView(img,0);
                toast.show();
            }
        });
    }

    private void initView() {
        btnToast = (Button) findViewById(R.id.btntoast);
        btncusPostion= (Button) findViewById(R.id.btncustomposition);
        btncusLayout= (Button) findViewById(R.id.btncustomlayout);
        btnhardCoding= (Button) findViewById(R.id.btnhardcoding);

    }


}
