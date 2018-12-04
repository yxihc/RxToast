package com.taopao.rxtoastutils;

import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.taopao.rxtoast.RxToastUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_normal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    @Override
    public void onBackPressed() {
        if (RxToastUtils.doubleExitApp()) {
            ActivityCompat.finishAfterTransition(this);
        }
    }

    private void initView() {
        btn_normal = (Button) findViewById(R.id.btn_normal);
        btn_normal.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_normal:
                Toast.makeText(MainActivity.this, "显示5秒", 5).show();
                break;
        }
    }
}
