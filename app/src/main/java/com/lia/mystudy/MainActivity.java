package com.lia.mystudy;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

public class MainActivity extends Activity {

    private MyBordcastReceiver myBordcastReceiver;

    @SuppressLint("HandlerLeak")
    Handler mHandle = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

        }
    };

    Handler mHandle1 = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            return false;
        }
    });

    HandlerThread handlerThread = new HandlerThread("HandlerThread");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView v = findViewById(R.id.tv_text);
        v.setText("12");

        mHandle.sendEmptyMessage(1);


        Intent intent = new Intent(this, TestActivity.class);
        startActivity(intent);

        myBordcastReceiver = new MyBordcastReceiver();
        IntentFilter intent1 = new IntentFilter();
        intent1.addAction("com.lia.Broadcast");

        registerReceiver(myBordcastReceiver, intent1);

        sendBroadcast(intent);

        EventBus.getDefault().register(this);
    }

    private class MyBordcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(myBordcastReceiver);
    }
}
