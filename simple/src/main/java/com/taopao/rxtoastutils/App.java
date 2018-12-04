package com.taopao.rxtoastutils;

import android.app.Application;

import com.taopao.rxtoast.RxToastUtils;

/**
 * @Author：淘跑
 * @Date: 2018/11/29 15:48
 * @Use：
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        RxToastUtils.init(this);
    }
}
