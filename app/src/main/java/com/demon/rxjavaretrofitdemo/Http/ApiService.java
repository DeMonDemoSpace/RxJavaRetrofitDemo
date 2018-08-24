package com.demon.rxjavaretrofitdemo.Http;

import com.demon.rxjavaretrofitdemo.data.Movie;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by DeMon on 2017/9/6.
 */

public interface ApiService {
    @GET("top250")
    Observable<Movie> getTopMovie(@Query("start") int start, @Query("count") int count);
}
