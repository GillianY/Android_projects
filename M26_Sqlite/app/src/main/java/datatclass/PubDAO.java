package datatclass;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/2.
 */

public class PubDAO {

    private static final String DB_NAME = "as_db.db";
    private static final String TAG = "SQLITE";
    private Context ctx;

    public PubDAO(Context ctx) {
        this.ctx = ctx;
    }

    private void copyDatabase(File dbFile) throws IOException {
        InputStream is = ctx.getAssets().open(DB_NAME);
        OutputStream os = new FileOutputStream(dbFile);

        byte[] buffer = new byte[1024];
        int read = is.read(buffer); //length
        while (read != -1) {
            os.write(buffer,0 ,read);
            read = is.read(buffer);
        }
        os.flush();
        os.close();
        is.close();
    }

    public SQLiteDatabase openReadDatabase() {
        File dbFile = ctx.getDatabasePath(DB_NAME);

        if (!dbFile.exists()) {
            try {
                File parentDir = new File(dbFile.getParent());
                if(!parentDir.exists()){
                    parentDir.mkdir();
                }
                copyDatabase(dbFile);
            } catch (IOException e) {
                throw new RuntimeException("Error creating source database", e);
            }
        }

        return SQLiteDatabase.openDatabase(dbFile.getPath(), null, SQLiteDatabase.OPEN_READONLY);
    }

    public SQLiteDatabase openWriteDatabase() {
        File dbFile = ctx.getDatabasePath(DB_NAME);

        if (!dbFile.exists()) {
            try {
                File parentDir = new File(dbFile.getParent());
                if(!parentDir.exists()){
                    parentDir.mkdir();
                }
                copyDatabase(dbFile);
            } catch (IOException e) {
                throw new RuntimeException("Error creating source database", e);
            }
        }

        return SQLiteDatabase.openDatabase(dbFile.getPath(), null, SQLiteDatabase.OPEN_READWRITE);
    }


    public ArrayList<Pub> getAllPubs(){
        SQLiteDatabase db = openReadDatabase();
        String sql = "select * from Pub order by sid desc ";
        Cursor c = db.rawQuery(sql,null);
        ArrayList<Pub> result = new ArrayList<Pub>();
        if(c.moveToFirst())
        {
            do{
                Pub p = new Pub();
                p.fromCursor(c);
                result.add(p);
            }while(c.moveToNext());
        }
        c.close();
        db.close();
       return result;
    }

    public ArrayList<Pub> getAllPubsByName(String name){
        SQLiteDatabase db = openReadDatabase();
        String sql = "select * from Pub WHERE Name='"+name +"' order by sid desc ";
        Log.i("Gina",sql);
        Cursor c = db.rawQuery(sql,null);
        ArrayList<Pub> result = new ArrayList<Pub>();
        if(c.moveToFirst())
        {
            do{
                Pub p = new Pub();
                p.fromCursor(c);
                result.add(p);
            }while(c.moveToNext());
        }
        c.close();
        db.close();
        return result;
    }

    public Pub getPubBySid(int sid){
        SQLiteDatabase db = openReadDatabase();
        String sql = "select * from Pub where sid="+sid;
        Cursor c = db.rawQuery(sql,null);
        Pub result = new Pub();
        if(c.moveToFirst())
        { result.fromCursor(c);
        }
        c.close();
        db.close();
        return result;
    }

    public void insert(Pub pub)
    {
        SQLiteDatabase db = openWriteDatabase();
        try{db.insert("Pub",null,pub.toContentValues());}
        catch (Exception e)
        {
            Log.e("SQL", "sql insert error");
        }
        db.close();
    }

    public void update(Pub pub)
    {
        SQLiteDatabase db = openWriteDatabase();
        try{
            int pub1 = db.update("Pub", pub.toContentValues(), "sid=?", new String[]{pub.getSid() + ""});
        }
        catch (Exception e)
        {
            Log.e("SQL", "sql update error");
        }
        db.close();
    }

    public void delete(Pub pub)
    {
        SQLiteDatabase db = openWriteDatabase();
        try{db.delete("Pub", "sid=?", new String[]{pub.getSid() + ""});}
        catch (Exception e)
        {
            Log.e("SQL", "sql delete error");
        }
        db.close();
    }


}
