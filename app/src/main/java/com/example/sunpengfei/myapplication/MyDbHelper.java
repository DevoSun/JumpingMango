
package com.example.sunpengfei.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sunpengfei.myapplication.db.DaoMaster;


/**
 * Created by sunpengfei on 16/3/28.
 */
public class MyDbHelper extends SQLiteOpenHelper {

    private final static String DB_NAME = "myTest.db";

    private final static int DB_VERSION = 1;

    public MyDbHelper(Context context) {
        this(context, DB_NAME, null, DB_VERSION);

    }

    public MyDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
            int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        DaoMaster.createAllTables(db,false);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
