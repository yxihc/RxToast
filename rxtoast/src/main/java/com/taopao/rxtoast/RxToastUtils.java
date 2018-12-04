package com.taopao.rxtoast;

import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @Author：淘跑
 * @Date: 2018/11/29 14:36
 * @Use： toast工具类
 */
public class RxToastUtils {
    private RxToastUtils() {
        throw new UnsupportedOperationException("You can't initialize me");
    }


    //******************************************初始化框架***************************************
    //static Application mApplication;
    static Context sContext = null;

    private static Context getContext() {
        if (sContext == null) {
            throw new IllegalStateException("RxToastUtils has not been initialized. Please add some code to the oncreate method of application : RxToastUtils.init(this);");
        }
        return sContext;
    }

    /**
     * 初始化RxToastUtils，在Application中初始化
     *
     * @param application 应用的上下文
     */
    public static void init(Application application) {
        sContext = application.getApplicationContext();
//        mApplication = application;
    }

    public static class Config {
        private Config() {

        }

        //        public static void Builder(Application application) {
//            return this;
//        }
        public Config init(Application application) {
            return this;
        }
    }

    //******************************************初始化框架***************************************


    //******************************************系统 Toast 替代方法(立即显示无需等待)***************************************
    //是否显示的短时长
    static boolean isShort = true;
    /*
     * Toast 替代方法 ：立即显示无需等待
     */
    private static Toast sToast;

    /**
     * Toast 替代方法 ：立即显示无需等待
     *
     * @param msg 显示内容
     */
    public static void show(String msg) {
        isShort = true;
        //如果是空 或者当前不是显示的短时长那就重新初始化
        if (sToast == null || !isShort) {
            sToast = Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT);
        } else {
            sToast.setText(msg);
        }
        sToast.show();
    }

    /**
     * Toast 替代方法 ：立即显示无需等待
     *
     * @param resId String资源ID
     */
    public static void show(int resId) {
        show(getContext().getString(resId));
    }

    /**
     * Toast 替代方法 ：立即显示无需等待
     *
     * @param resId String资源ID
     */
    public static void showLong(int resId) {
        showLong(getContext().getString(resId));
    }

    /**
     * Toast 替代方法 ：立即显示无需等待
     *
     * @param msg 要显示的字符串
     */
    private static void showLong(String msg) {
        isShort = false;
        if (sToast == null || isShort) {
            sToast = Toast.makeText(getContext(), msg, Toast.LENGTH_LONG);
        } else {
            sToast.setText(msg);
        }
        sToast.show();
    }


    //******************************************系统 Toast 替代方法(立即显示无需等待)***************************************//


    //******************************************系统 Toast 替代方法(需要等待)**********************************************//

    /**
     * 封装了Toast的方法 :需要等待
     */
    public static void showShort(String str) {
        Toast.makeText(getContext(), str, Toast.LENGTH_SHORT).show();
    }

    /**
     * 封装了Toast的方法 :需要等待
     */
    public static void showShort(int resId) {
        showShort(getContext().getString(resId));
    }

    /**
     * 封装了Toast的方法 :需要等待
     */
    public static void showLongWait(String str) {
        Toast.makeText(getContext(), str, Toast.LENGTH_LONG).show();
    }

    /**
     * 封装了Toast的方法 :需要等待
     */
    public static void showLongWait(int resId) {
        showLong(getContext().getString(resId));
    }
    //******************************************系统 Toast 替代方法(需要等待)****************************//


    //******************************************自定义背景**********************************************//
    private static Toast currentToast;
    private static final String TOAST_TYPEFACE = "sans-serif-condensed";

    @CheckResult
    public static Toast custom(@NonNull Context context, @NonNull String message, int duration, int gravity) {
        if (currentToast == null) {
            currentToast = new Toast(context);
        }
        final View toastLayout = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.toast_layout, null);
        final TextView toastTextView = (TextView) toastLayout.findViewById(R.id.toast_text);

        toastTextView.setText(message);
        toastTextView.setTypeface(Typeface.create(TOAST_TYPEFACE, Typeface.NORMAL));

        currentToast.setView(toastLayout);
        currentToast.setGravity(gravity, 0, 0);
        currentToast.setDuration(duration);
        return currentToast;
    }

    /**
     * 居中显示 黑色背景
     */
    public static void normal(@NonNull String message) {
        normal(message, Toast.LENGTH_SHORT);
    }

    /**
     * 居中显示 黑色背景
     *
     * @param message
     * @param duration
     */
    public static void normal(@NonNull String message, int duration) {
        if (message == null || message.isEmpty()) {
            return;
        }
        custom(getContext(), message, duration, Gravity.CENTER).show();
    }

    /**
     * 居中显示 黑色背景
     */
    public static void normal(int resId) {
        normal(getContext().getString(resId));
    }

    //******************************************自定义背景**********************************************//
    private static long mExitTime = 2000;

    /**
     * 双击退出
     *
     * @param message
     * @return
     */
    public static boolean doubleExitApp(String message) {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            normal(message);
            mExitTime = System.currentTimeMillis();
            return false;
        }
        return true;
    }

    /**
     * 双击退出
     *
     * @param resId
     * @return
     */
    public static boolean doubleExitApp(int resId) {
        return doubleExitApp(getContext().getString(resId));
    }

    /**
     * 双击退出
     */
    public static boolean doubleExitApp() {
        return doubleExitApp(R.string.exitapp_msg);
    }

}
