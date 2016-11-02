package datatclass;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * Created by Administrator on 2016/11/2.
 */

public class Pub {
    private int sid;
    private String name;
    private String address;
    private byte[] photo;
    private String startdate;

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public byte[] getPhotop() {
        return photo;
    }

    public void setPhotop(byte[] photop) {
        this.photo = photop;
    }

    public void fromCursor(Cursor c)
    {
        name = c.getString(c.getColumnIndex("name"));
        sid = c.getInt(c.getColumnIndex("sid"));
        address = c.getString(c.getColumnIndex("address"));
        photo = c.getBlob(c.getColumnIndex("photo"));
    }

    public ContentValues toContentValues() {
        ContentValues ret = new ContentValues();
        ret.put("name",getName());
        ret.put("address",getAddress());
        ret.put("photo",getPhotop());
        return ret;
    }
}
