package com.demon.rxjavaretrofitdemo.app;

import android.app.Application;
import android.content.Context;

/**
 * Created by DeMon on 2017/9/6.
 */

public class MyApplication extends Application{
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getContext() {
        return mContext;
    }
}
