package com.demon.rxjavaretrofitdemo.Http;

import com.demon.rxjavaretrofitdemo.data.Movie;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by DeMon on 2017/9/6.
 */

public class ApiMethods {

    /**
     * 封装线程管理和订阅的过程
     */
    public static void ApiSubscribe(Observable observable, Observer observer) {
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    /**
     * 用于获取豆瓣电影Top250的数据
     *
     * @param observer 由调用者传过来的观察者对象
     * @param start    起始位置
     * @param count    获取长度
     */
    /*public static void getTopMovie(Observer<Movie> observer, int start, int count) {
        ApiSubscribe(Api.getApiService().getTopMovie(start, count), observer);
    }*/
    public static void getTopMovie(Observer<Movie> observer, int start, int count) {
        ApiSubscribe(ApiStrategy.getApiService().getTopMovie(start, count), observer);
    }

}
