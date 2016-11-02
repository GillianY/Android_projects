package adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

public abstract class MyBaseAdapter<T> extends BaseAdapter{

	protected Context context;
	protected ArrayList<T> dataList;
	public void setDataList(ArrayList<T> dataList) {
		this.dataList = dataList;
		//notifyDataSetChanged();
	}

	protected LayoutInflater inflater;
	protected abstract void rowSelected(T song, int index);

	public MyBaseAdapter(Context context, ArrayList<T> dataList) {
		super();
		this.context = context;
		this.inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.dataList = dataList;
	}

	@Override
	public int getCount() {
		return dataList.size();
	}

	@Override
	public T getItem(int position) {
		return dataList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public ArrayList<T> getDataList() {
		return dataList;
	}

	

}
