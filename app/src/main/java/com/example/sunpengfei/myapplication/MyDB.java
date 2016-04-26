package com.example.sunpengfei.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.sunpengfei.myapplication.db.DaoMaster;
import com.example.sunpengfei.myapplication.db.DaoSession;

/**
 * Created by sunpengfei on 16/4/14.
 */
public class MyDB {

    private static MyDB instance;

    private MyDbHelper myDbHelper;

    private SQLiteDatabase db;

    private DaoSession session;

    private MyDB(Context context){
        myDbHelper = new MyDbHelper(context);
        db = myDbHelper.getWritableDatabase();
        session = new DaoMaster(db).newSession();
    }

    public static MyDB getInstance(Context context){
        if(instance == null){
            instance = new MyDB(context);
        }
        return instance;
    }

    public DaoSession getSession(){
        return session;
    }

    public SQLiteDatabase getDb(){
        return db;
    }
}
