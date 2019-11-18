package com.lia.mvp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.lia.aop.annotation.ClickBehavior;
import com.lia.aop.annotation.LoginCheck;
import com.lia.mvp.base.BaseActivity;
import com.lia.mvp.test.TestContract;
import com.lia.mvp.test.TestPresenter;
import com.lia.networklib.base.UserInterface;

public class MainActivity extends BaseActivity<TestPresenter, TestContract.View> {
    private final String TAG = "MainActivity";

    Button mChat;
    Button mCont;
    Button mFind;
    Button mMine;
    Button mTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mChat = findViewById(R.id.bt_chat);
        mCont = findViewById(R.id.bt_cont);
        mFind = findViewById(R.id.bt_find);
        mMine = findViewById(R.id.bt_mine);
        mTest = findViewById(R.id.btn_test);

        mChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doChat();
            }
        });
        mCont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doCont();
            }
        });
        mFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doFind();
            }
        });
        mMine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doMine();
            }
        });

        mTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doTest();
            }
        });
    }

    @Override
    public TestContract.View getContract() {
        return new TestContract.View<UserInterface>() {
            @Override
            public void testResult(UserInterface intrrface) {
                if (intrrface != null) {
                    Toast.makeText(MainActivity.this, intrrface.toString(), Toast.LENGTH_LONG).show();
                    Log.e(TAG,  intrrface.toString());
                } else {
                    Toast.makeText(MainActivity.this, "获取数据失败", Toast.LENGTH_LONG).show();
                }
            }
        };
    }

    @Override
    protected TestPresenter getPersenter() {
        return new TestPresenter();
    }

    @ClickBehavior("我的")
    @LoginCheck
    private void doMine() {
        Log.e(TAG, "点击了我的");
    }

    @ClickBehavior("发现")
    @LoginCheck
    private void doFind() {
        Log.e(TAG, "点击了发现");
    }

    @ClickBehavior("联系人")
    @LoginCheck
    private void doCont() {
        Log.e(TAG, "点击了联系人");
    }

    @ClickBehavior("主页")
    private void doChat() {
        Log.e(TAG, "点击了主页");
    }


    @ClickBehavior("测试")
    private void doTest() {
        p.getContract().requestTest();
    }
}
