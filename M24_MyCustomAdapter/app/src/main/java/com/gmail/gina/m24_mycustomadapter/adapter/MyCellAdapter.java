package com.gmail.gina.m24_mycustomadapter.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gmail.gina.m24_mycustomadapter.R;
import com.gmail.gina.m24_mycustomadapter.data.User;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/1.
 */

public class MyCellAdapter extends MyBaseAdapter<User> {

    public MyCellAdapter(Context context, ArrayList<User> dataList) {
        super(context, dataList);
    }

    public static class ViewHolder{
        private ImageView photo;
        private TextView name;
        private TextView address;
        private TextView phoneNUm;
        private ImageView call;

        public ImageView getPhoto() {
            return photo;
        }

        public void setPhoto(ImageView photo) {
            this.photo = photo;
        }

        public TextView getName() {
            return name;
        }

        public void setName(TextView name) {
            this.name = name;
        }

        public TextView getAddress() {
            return address;
        }

        public void setAddress(TextView address) {
            this.address = address;
        }

        public TextView getPhoneNUm() {
            return phoneNUm;
        }

        public void setPhoneNUm(TextView phoneNUm) {
            this.phoneNUm = phoneNUm;
        }

        public ImageView getCall() {
            return call;
        }

        public void setCall(ImageView call) {
            this.call = call;
        }
    }

    @Override
    protected void rowSelected(User song, int index) {

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      ViewHolder viewHolder = null;
        if(convertView == null)
        {
            convertView = inflater.inflate(R.layout.adapter_mycell,null);
            viewHolder = new ViewHolder();
            viewHolder.setName((TextView)convertView.findViewById(R.id.tv_name));
            viewHolder.setAddress((TextView)convertView.findViewById(R.id.tv_address));
            viewHolder.setPhoneNUm((TextView)convertView.findViewById(R.id.tv_phone));
            viewHolder.setPhoto((ImageView) convertView.findViewById(R.id.iv_photo));
            viewHolder.setCall((ImageView) convertView.findViewById(R.id.iv_call));
            convertView.setTag(viewHolder);
        }else
        {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        User currentData = dataList.get(position);
        viewHolder.getName().setText(currentData.getName());
        viewHolder.getAddress().setText(currentData.getAddress());
        viewHolder.getPhoto().setImageResource(currentData.getPicture());
       // viewHolder.getPhoneNUm().setText(currentData.getPicture());

        return convertView;
    }
}
