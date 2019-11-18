package com.lia.mystudy;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.lia.mystudy.databinding.ActivityTestBinding;
import com.lia.mystudy.model.UserInfo;

public class TestActivity extends AppCompatActivity {

    private UserInfo userInfo = new UserInfo();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_test);


        ActivityTestBinding testBinding = DataBindingUtil.setContentView(this, R.layout.activity_test);

        userInfo.name.set("Lia");
        userInfo.pwd.set("123");
        testBinding.setUser(userInfo);
    }


}
