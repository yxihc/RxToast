package com.taopao.rxtoastutils;

import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.taopao.rxtoast.RxToastUtils;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onBackPressed() {
        if (RxToastUtils.doubleExitApp()) {
            ActivityCompat.finishAfterTransition(this);
        }
    }
}
