package com.lia.eventbus;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EventBus.getInstance().register(this);

        startActivity(new Intent(this, SecondActivity.class));

    }

    // 将add方法放入到eventbus中
    // 将所有带有自定义注解的方法都放进去
    @Subscrible(threadMode = ThreadMode.MAIN)
    public void add(UserInfo userInfo) {
        Log.e("Success", userInfo.toString());
    }

    // Method 线程模式 方法中的参数


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
