package com.jackzhao.www.bottomactionbar.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jackzhao.www.bottomactionbar.R;
import com.jackzhao.www.bottomactionbar.utils.Common;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(3000);
                    Common.CommonStartActivity(getApplicationContext(), Home.class);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        thread.start();
    }
}
