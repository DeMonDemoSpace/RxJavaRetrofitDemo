package com.demon.rxjavaretrofitdemo.data;

import java.util.List;

/**
 * Created by DeMon on 2017/9/5.
 */

public class Movie {
    private String title;
    private List<Subjects> subjects;


    public String getTitle() {
        return title;
    }

    public List<Subjects> getSubjects() {
        return subjects;
    }
}
