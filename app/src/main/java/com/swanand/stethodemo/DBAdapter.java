package com.swanand.stethodemo;

/**
 * Created by swanand on 5/16/2016.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter  {

    static final String dbname="mydb.db";
    static final int databaseversion=6;
    static final String KEY_ROWID="_id";
    static final String KEY_FNAME ="firstname";
    static final String KEY_LNAME ="lastname";
    static final String KEY_CITY="city";
    static final String TAG="DBAdapter";
    static final String DATABASE_CREATE="create table user(_id integer primary key autoincrement,"+"firstname text not null,lastname text not null,city text not null);";
    static final String DATABASE_TABLE="user";
    static final String SelQuery="select * from user where name="+ KEY_FNAME +",password="+ KEY_CITY +"";
    final Context context;
    DatabaseHelper DBHelper;
    SQLiteDatabase sdb;
    public DBAdapter(Context ctx)
    {
        this.context=ctx;
        DBHelper=new DatabaseHelper(context);
    }

    private class DatabaseHelper extends SQLiteOpenHelper
    {

        public DatabaseHelper(Context c)
        {
            super(c,dbname,null,databaseversion);

        }

        @Override
        public void onCreate(SQLiteDatabase sdb) {
            // TODO Auto-generated method stub
            try {
                sdb.execSQL(DATABASE_CREATE);


            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase sdb, int oldVersion, int newVersion) {
            // TODO Auto-generated method stub

            Log.w(TAG,"Upgrading database from version"+oldVersion+"to"+newVersion+",which will destroy all old data");
            sdb.execSQL("drop table if exists user");
            onCreate(sdb);
            // TODO Auto-generated catch block

        }

    }
    public DBAdapter open() throws SQLException
    {
        sdb=DBHelper.getWritableDatabase();
        return this;
    }

    public void close()
    {
        DBHelper.close();
    }



    public long insertRecord(String a,String b,String c)
    {ContentValues initialValues=new ContentValues();
        initialValues.put(DBAdapter.KEY_FNAME,a);
        initialValues.put(DBAdapter.KEY_LNAME,b);
        initialValues.put(DBAdapter.KEY_CITY,c);
        return sdb.insert(DATABASE_TABLE,null,initialValues);

    }

    public Cursor checkLogin(String name,String password)
    {
        String[] columns={KEY_FNAME, KEY_CITY};
        Cursor csr=sdb.query(DATABASE_TABLE,columns,"name=? and password=?",new String[]{name,password},null,null,null);
        return csr;
    }
}
