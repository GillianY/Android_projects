package com.example.administrator.customadapter;
import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class MyAdapter extends MyBaseAdapter<String> {

	public MyAdapter(Context context, ArrayList<String> dataList) {
		super(context, dataList);
	}


	
	
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View view = inflater.inflate(R.layout.adapter_mycell, null);

		// 課程名稱
		TextView courseNameText = (TextView) view.findViewById(R.id.textView1);
		courseNameText.setText(dataList.get(position));

		LinearLayout row = (LinearLayout) view.findViewById(R.id.rowLayout);
		row.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				rowSelected(dataList.get(position), position);
			}
		});
		return view;
	}




	@Override
	protected void rowSelected(String course, int index) {
		// TODO Auto-generated method stub
		Toast.makeText(context, "U selected : "+course, Toast.LENGTH_LONG).show();
	}


	
}