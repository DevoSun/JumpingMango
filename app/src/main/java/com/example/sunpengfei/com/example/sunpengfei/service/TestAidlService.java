package com.example.sunpengfei.com.example.sunpengfei.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.example.sunpengfei.myapplication.IMyAidlInterface;

/**
 * Created by sunpengfei on 16/4/27.
 */
public class TestAidlService extends Service{

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    IMyAidlInterface.Stub binder = new IMyAidlInterface.Stub() {
        @Override
        public void add(int a, int b) throws RemoteException {
            Log.e("aaaaa",(a+b)+"");
        }
    };
}
