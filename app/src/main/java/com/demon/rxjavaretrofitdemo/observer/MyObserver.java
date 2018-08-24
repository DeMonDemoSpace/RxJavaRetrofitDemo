package com.demon.rxjavaretrofitdemo.observer;

import android.content.Context;
import android.util.Log;


import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by DeMon on 2017/9/6.
 */

public class MyObserver<T> implements Observer<T> {
    private static final String TAG = "MyObserver";
    private ObserverOnNextListener listener;
    private Context context;

    public MyObserver(Context context, ObserverOnNextListener listener) {
        this.listener = listener;
        this.context = context;
    }

    @Override
    public void onSubscribe(Disposable d) {
        Log.d(TAG, "onSubscribe: ");
        //添加业务处理
    }

    @Override
    public void onNext(T t) {
        listener.onNext(t);
    }

    @Override
    public void onError(Throwable e) {
        Log.e(TAG, "onError: ", e);
        //添加业务处理
    }

    @Override
    public void onComplete() {
        Log.d(TAG, "onComplete: ");
        //添加业务处理
    }
}
