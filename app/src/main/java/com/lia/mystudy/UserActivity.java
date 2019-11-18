package com.lia.mystudy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.lia.database.greenDao.db.UserDb;
import com.lia.mystudy.model.User;

import java.util.List;

public class UserActivity extends AppCompatActivity {

    private static final String TAG = UserActivity.class.getSimpleName();

    private Button mAdd;
    private Button mDelete;
    private Button mUpdate;
    private Button mQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        initView();
    }

    private void initView() {
        mAdd = findViewById(R.id.btn_add);
        mDelete = findViewById(R.id.btn_delete);
        mUpdate = findViewById(R.id.btn_update);
        mQuery = findViewById(R.id.btn_query);

        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                user.setName("Lia");
                user.setAge(28);
                user.setBirthday("1991-04-16");
                user.setAddr("南极洲");
                user.setId(10000001L);
                user.setPhone("18515478283");
                user.setUserId("101530123");
                UserDb.getInstance(UserActivity.this).insertUser(user);
            }
        });

        mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<User> users = UserDb.getInstance(UserActivity.this).queryAll();
                for (User user : users) {
                    Log.e(TAG, "查询结果 >> " + user.toString());
                }
            }
        });
    }
}
