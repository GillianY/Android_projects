package com.gmail.gina.m18_jason;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private static final String TAG="JSON";
    private TextView tvdispaly ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        readRawFile();
       // readAssetsFile();


    }

    private void initView() {
        tvdispaly = (TextView) findViewById(R.id.tv_display);
        tvdispaly.setMovementMethod(ScrollingMovementMethod.getInstance());
    }

    private void parseJSON(String s ) {
       try {
           JSONArray ja = new JSONArray(s);
           for (int i = 0; i < ja.length(); i++) {
               JSONObject jo = ja.getJSONObject(i);
                String id=  jo.getString("id");
                String name = jo.getString("cate_name");
                Log.i(TAG, id+":"+name);
               tvdispaly.setText(tvdispaly.getText()+"id:" + id +" name:"+name+"\n");
           }
       }catch(Exception e){
           e.printStackTrace();
       }
  }

    private void readAssetsFile() {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        try{
            br = new BufferedReader(new InputStreamReader(getAssets().open("abc.txt")));
            String read ="";
            while ((read = br.readLine()) != null )
            {
                  sb.append(read);
            }

        }catch (Exception e)
        {
            Log.e(TAG,e.getMessage());

        }finally{
        if(br !=null)
            {
                try{br.close();}
                catch(Exception e)
                {
                    Log.e(TAG,e.getMessage());
                }
            }
            Log.e(TAG,sb.toString());

        }


    }
    private void readRawFile()
    {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        try{
            br = new BufferedReader(new InputStreamReader(getResources().openRawResource(R.raw.myjson)));
            String read ="";
            while ((read = br.readLine()) != null )
            {
                sb.append(read);
            }

        }catch (Exception e)
        {
            Log.e(TAG,e.getMessage());

        }finally{


            if(br !=null)
            {
                try{br.close();}
                catch(Exception e)
                {
                    Log.e(TAG,e.getMessage());
                }
            }
            Log.e(TAG,sb.toString());
            for(int i=0; i<24; i++) {
                parseJSON(sb.toString());

            }

        }


    }
}
