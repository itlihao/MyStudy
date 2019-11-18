package com.lia.inject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.lia.inject.ioc.InjectTool;
import com.lia.inject.ioc.annotation.BindView;
import com.lia.inject.ioc.annotation.ContentView;
import com.lia.inject.ioc.annotation.OnClick;
import com.lia.inject.ioc.annotation.OnLongClick;

@ContentView(R.layout.activity_main)
public class MainActivity extends Activity {

    @BindView(R.id.btn1)
    Button button1;

    @BindView(R.id.btn2)
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        InjectTool.inject(this);

    }

    @OnClick(R.id.btn1)
    private void btn1Click() {
        Toast.makeText(this, "do click", Toast.LENGTH_SHORT).show();
    }

    @OnLongClick(R.id.btn2)
    private boolean btn2LongClick() {
        Toast.makeText(this, "do longClick", Toast.LENGTH_SHORT).show();
        return false;
    }
}
