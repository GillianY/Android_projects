package com.example.administrator.customadapter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {
	// 取得 ListView 元件
	ListView lv;
	// 定義陣列
	String[] data = { "Android 第一講", "Android 第二講", "Android 第三講",
			"Android 第四講", "Android 第五講", "Android 第六講", "Android 第七講",
			"Android 黑白講", "Android 隨便講", "Android 亂亂講" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		initHandler();
	}

	private void initHandler() {
		// TODO Auto-generated method stub
		ArrayList<String> list = new ArrayList<String>(data.length);  
		for (String s : data) {  
		    list.add(s);  
		}  
		MyAdapter adapter = new MyAdapter(this,list);
		lv.setAdapter(adapter);
	}

	private void initView() {
		// TODO Auto-generated method stub
		lv = (ListView) findViewById(R.id.listView1);
	}


}
