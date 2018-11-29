package com.taopao.rxtoast;

import android.content.Context;
import android.support.annotation.NonNull;

/**
 * @Author：淘跑
 * @Date: 2018/11/29 14:37
 * @Use：
 */
public class RxToastConfig {

    private static Context sContext;

    public static Context getContext() {
        return sContext;
    }

    public RxToastConfig() {

    }

    public static void init(@NonNull Context context) {
        sContext = context;
    }

}
