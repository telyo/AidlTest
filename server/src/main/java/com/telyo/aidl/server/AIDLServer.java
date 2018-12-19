package com.telyo.aidl.server;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tanghuan on 2018/11/1.
 * Supported By 甜瓜移动.
 * Official Website: www.melonmobile.cn.
 *
 * @author tanghuan
 */
public class AIDLServer extends Service {
    private final String TAG = "Server";

    private List<People> peopleList = new ArrayList<>();
    public AIDLServer() {
        initDate();
    }

    private void initDate() {
        for (int i=0;i<5;i++){
            peopleList.add(new People("张三" + i,"1" + i));
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return stub;
    }

    private final PeopleController.Stub stub = new PeopleController.Stub() {
        @Override
        public int getPid() throws RemoteException {
            return Process.myPid();
        }

        @Override
        public List<People> getPeople() throws RemoteException {
            return peopleList;
        }

        @Override
        public void addPeople(People p) throws RemoteException {
            peopleList.add(p);
        }
    };
}
