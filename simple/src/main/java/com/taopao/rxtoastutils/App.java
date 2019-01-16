package com.taopao.rxtoastutils;

import android.app.Application;
import android.view.Gravity;

import com.blankj.utilcode.util.Utils;
import com.taopao.rxtoast.RxToast;

/**
 * @Author：淘跑
 * @Date: 2018/11/29 15:48
 * @Use：
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        RxToast.init(this)
                .setBackgroundColor("#A5000000")//背景颜色：35%的黑色透明度
                .setTextColor("#FFFFFF")//字体颜色
                .setGravity(Gravity.CENTER)//显示位置
                .setCornerRadius(6)//圆角大小  单位dp
                .setPadding(24, 24, 16, 16)//textview左右上下间距 单位dp
                .setMaxLines(2)//textview最大行数
                .setTextSize(14)//textview字体大小  单位dp
                .setZ(30)//Z轴的高度（阴影）
                .setLineSpacing(1.5f)//设置行间距
                .apply();//应用设置

        Utils.init(this);
    }
}
