package com.taopao.rxtoastutils;

import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

import com.taopao.rxtoast.RxToast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBtn1;
    private Button mBtn2;
    private Button mBtn3;
    private Button mBtn4;
    private Button mBtn5;
    private Button mBtn6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    @Override
    public void onBackPressed() {
        //再按一次返回
        if (RxToast.doubleExitApp()) {
            ActivityCompat.finishAfterTransition(this);
        }
    }

    /**
     * 系统替代方法
     */
    public void click1() {
        RxToast.showShort("这是一个系统的Toast");
    }

    /**
     * 美团样式
     */
    public void click2() {
        //这里是演示 推荐在application里初始化
        RxToast.init(getApplication())
                .setBackgroundColor("#A5000000")//35%的黑色透明度
                .setTextColor("#FFFFFF")
                .setGravity(Gravity.CENTER)
                .setCornerRadius(6)
                .setPadding(14, 14, 10, 10)
                .setMaxLines(2)
                .setTextSize(14)
                .setZ(30)
                .setMaxLines(3)
                .apply();
        RxToast.show("这是美团样式");
    }

    /**
     * 白色样式
     */
    public void click3() {
        //这里是演示 推荐在application里初始化
        RxToast.init(getApplication())
                .setBackgroundColor("#FFFFFF")
                .setTextColor("#333333")
                .setGravity(Gravity.CENTER)
                .setCornerRadius(6)
                .setPadding(24, 24, 16, 16)
                .setMaxLines(2)
                .setTextSize(14)
                .setZ(30)
                .setMaxLines(3)
                .apply();
        RxToast.show("这是白色样式");
    }

    /**
     * QQ 样式
     */
    public void click4() {
        //这里是演示 推荐在application里初始化
        RxToast.init(getApplication())
                .setBackgroundColor("#CC000000")
                .setTextColor("#FFFFFF")
                .setGravity(Gravity.CENTER)
                .setPadding(16, 16, 12, 12)
                .setMaxLines(2)
                .setTextSize(12)
                .setZ(30)
                .setMaxLines(3)
                .apply();
        RxToast.show("这是QQ样式");
    }

    /**
     * 自定义布局
     */
    public void click5() {
        //这里是演示 推荐在application里初始化
        RxToast.init(getApplication())
                .setGravity(Gravity.CENTER)
                .setCustomView(R.layout.toast_layout)
                .apply();
        RxToast.show("这是一个自定义布局");
    }

    int x = 0;

    /**
     * 显示当前的样式
     */
    public void click6() {
        //这里是演示 推荐在application里初始化
        RxToast.show("这是当前的样式,这是一个特别长的Toast，老司机开车了，滴~滴滴~ 滴~滴滴~ 滴~滴滴~ 滴~滴滴~ 滴~滴滴~ 滴~滴滴~ 滴~滴滴~ 滴~滴滴~ 滴~滴滴~ 滴~滴滴~ 滴~滴滴~ 点击次数" + x++);
    }

    private void initView() {
        mBtn1 = (Button) findViewById(R.id.btn_1);
        mBtn1.setOnClickListener(this);
        mBtn2 = (Button) findViewById(R.id.btn_2);
        mBtn2.setOnClickListener(this);
        mBtn3 = (Button) findViewById(R.id.btn_3);
        mBtn3.setOnClickListener(this);
        mBtn4 = (Button) findViewById(R.id.btn_4);
        mBtn4.setOnClickListener(this);
        mBtn5 = (Button) findViewById(R.id.btn_5);
        mBtn5.setOnClickListener(this);
        mBtn6 = (Button) findViewById(R.id.btn_6);
        mBtn6.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_1:
                click1();
                break;
            case R.id.btn_2:
                click2();
                break;
            case R.id.btn_3:
                click3();
                break;
            case R.id.btn_4:
                click4();
                break;
            case R.id.btn_5:
                click5();
                break;
            case R.id.btn_6:
                click6();
                break;
        }
    }
}
