package com.gmail.gina.m13_activitycommunication;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button btnphonecall ;
    private Button btnnext ;
    private Button btnqr ;
    private EditText etinput;
    private TextView tvoutput;

    private static final int QRCOde= 1;
    private static final int CAMERA = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initHandler();
    }

    private void initView() {
        btnphonecall = (Button) findViewById(R.id.btn_phonecall);
        btnnext = (Button) findViewById(R.id.btn_next);
        btnqr = (Button) findViewById(R.id.btn_QR);
        etinput = (EditText)findViewById(R.id.et_input);
        tvoutput = (TextView) findViewById(R.id.tv01);

    }

    @Override
    public void onBackPressed() {
        // super.onBackPressed();
        AlertDialog.Builder dialog = new AlertDialog.Builder(this); // activity
        dialog.setTitle("確定?");
        dialog.setMessage(" R U SURE ?");
        dialog.setPositiveButton("sure", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.super.onBackPressed();
                Log.i("gina","back");
            }
        });
        dialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.i("gina","do nothing");
                // do nothing
            }
        });
        dialog.show(); // !!!!!!!! don't forget

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode ==QRCOde)
        {
            if(resultCode==RESULT_OK)
            {
                String str = data.getStringExtra("MSG");
                tvoutput.setText(str);
            }

        }else if(requestCode ==CAMERA)
        {}
    }

    private void initHandler()
    {

        btnphonecall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                //intent.setAction(Intent.ACTION_DIAL);
                intent.setAction(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:0225149191"));
                startActivity(intent);

            }
        });

        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent();
                it.setClass(MainActivity.this, NextActivity.class) ;
                //case 1
                Bundle data = new Bundle();
                data.putString("Name",etinput.getText().toString());
                data.putString("Age","28");
                it.putExtras(data);

                //case2
                it.putExtra("New","Gina");
                it.putExtra("Age2","26");

                startActivity(it);
               // finish();  // finish old avtivity
            }
        });

        btnqr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Gina","test");
                Intent it = new Intent();
                it.setClass(MainActivity.this, NextActivity.class);
                startActivityForResult(it,QRCOde);

            }
        });



    }
}
