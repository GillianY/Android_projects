package adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.m26_sqlite.EditorActivity;
import com.example.administrator.m26_sqlite.R;

import java.util.ArrayList;

import datatclass.Pub;

/**
 * Created by Administrator on 2016/11/1.
 */

public class MyCellAdapter extends MyBaseAdapter<Pub> {

    public MyCellAdapter(Context context, ArrayList<Pub> dataList) {
        super(context, dataList);
    }

    public static class ViewHolder{
        private ImageView photo;
        private TextView name;
        private TextView address;
        private TextView phoneNUm;
        private ImageView call;
        private LinearLayout rootlayout;


        public LinearLayout getRootlayout() {
            return rootlayout;
        }

        public void setRootlayout(LinearLayout rootlayout) {
            this.rootlayout = rootlayout;
        }


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
    protected void rowSelected(Pub pub, int index) {
      //  Toast.makeText(context, "hello", Toast.LENGTH_SHORT).show();
        Intent it = new Intent();
        it.putExtra("SID",pub.getSid());
        it.setClass(context, EditorActivity.class);
        context.startActivity(it);

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

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
            viewHolder.setRootlayout((LinearLayout)convertView.findViewById(R.id.root_layout));
            convertView.setTag(viewHolder);
        }else
        {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        final Pub currentData = dataList.get(position);
        viewHolder.getName().setText(currentData.getName());
        viewHolder.getAddress().setText(currentData.getAddress());
        LinearLayout layout = viewHolder.getRootlayout();
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rowSelected(currentData ,position);
            }
        });

        byte[] blob = currentData.getPhotop();
        if(blob != null) {
            Bitmap bmp = BitmapFactory.decodeByteArray(blob, 0, blob.length);
            viewHolder.getPhoto().setImageBitmap(bmp);
        }
       // viewHolder.getPhoneNUm().setText(currentData.getPicture());

        return convertView;

    }
}
