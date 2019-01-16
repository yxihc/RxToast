package com.taopao.rxtoastutils;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.SnackbarUtils;

public class SnackBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snack_bar);
    }

    public void click1(View view) {
        SnackbarUtils.with(findViewById(R.id.fl))
                .setMessage("sadsdsadas")
                .showError();
    }

}
