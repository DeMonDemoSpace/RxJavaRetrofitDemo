package com.demon.rxjavaretrofitdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.demon.rxjavaretrofitdemo.Http.ApiMethods;
import com.demon.rxjavaretrofitdemo.Http.ApiService;
import com.demon.rxjavaretrofitdemo.data.Movie;
import com.demon.rxjavaretrofitdemo.data.Subjects;
import com.demon.rxjavaretrofitdemo.observer.MyObserver;
import com.demon.rxjavaretrofitdemo.observer.ObserverOnNextListener;
import com.demon.rxjavaretrofitdemo.progress.ProgressObserver;

import java.util.List;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //first();

        //second();

        //three();

        four();
    }

    private void four() {
        ObserverOnNextListener<Movie> listener = new ObserverOnNextListener<Movie>() {
            @Override
            public void onNext(Movie movie) {
                Log.d(TAG, "onNext: " + movie.getTitle());
                List<Subjects> list = movie.getSubjects();
                for (Subjects sub : list) {
                    Log.d(TAG, "onNext: " + sub.getId() + "," + sub.getYear() + "," + sub.getTitle());
                }
            }
        };
        ApiMethods.getTopMovie(new ProgressObserver<Movie>(this, listener), 0, 10);
    }

    /**
     * 重写Observer对象，实现onSubscribe，onNext，onError，onComplete的封装
     */
    private void three() {
        ObserverOnNextListener<Movie> listener = new ObserverOnNextListener<Movie>() {
            @Override
            public void onNext(Movie movie) {
                Log.d(TAG, "onNext: " + movie.getTitle());
                List<Subjects> list = movie.getSubjects();
                for (Subjects sub : list) {
                    Log.d(TAG, "onNext: " + sub.getId() + "," + sub.getYear() + "," + sub.getTitle());
                }
            }
        };
        ApiMethods.getTopMovie(new MyObserver<Movie>(this, listener), 0, 10);
    }

    /**
     * 1.封装Retrofit请求过程
     * 2.封装封装线程管理和订阅
     */
    private void second() {
        Observer<Movie> observer = new Observer<Movie>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe: ");
            }

            @Override
            public void onNext(Movie movie) {
                Log.d(TAG, "onNext: " + movie.getTitle());
                List<Subjects> list = movie.getSubjects();
                for (Subjects sub : list) {
                    Log.d(TAG, "onNext: " + sub.getId() + "," + sub.getYear() + "," + sub.getTitle());
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: Over!");
            }
        };
        ApiMethods.getTopMovie(observer, 0, 10);
    }

    /**
     * 最基本的RxJava+Retrofit
     */
    private void first() {
        String baseUrl = "https://api.douban.com/v2/movie/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                //请求的结果转为实体类
                .addConverterFactory(GsonConverterFactory.create())
                //适配RxJava2.0,RxJava1.x则为RxJavaCallAdapterFactory.create()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);

        apiService.getTopMovie(0, 10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Movie>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onNext(Movie movie) {
                        Log.d(TAG, "onNext: " + movie.getTitle());
                        List<Subjects> list = movie.getSubjects();
                        for (Subjects sub : list) {
                            Log.d(TAG, "onNext: " + sub.getId() + "," + sub.getYear() + "," + sub.getTitle());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: Over!");
                    }
                });
    }
}
