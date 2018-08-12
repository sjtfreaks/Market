package com.warframe.jet.market;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.warframe.jet.market.ui.AddActivity;
import com.warframe.jet.market.ui.LookActivity;
import com.warframe.jet.market.utils.StaticClass;

import cn.bmob.v3.Bmob;

public class MainActivity extends AppCompatActivity {
    private Button bt_add;
    private Button bt_look;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //连接Bmob id
        Bmob.initialize(this, StaticClass.BMOB_ID);
        findView();
        initListener();
    }
//监听事件
    private void initListener() {
        bt_add.setOnClickListener(new View.OnClickListener(){
            @Override //点击事件 添加
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });
        //以上等同 搜索
        bt_look.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LookActivity.class);
                startActivity(intent);
            }
        });
    }
    //与xml连接
    private void findView() {
        bt_add = (Button) findViewById(R.id.bt_add);
        bt_look = (Button) findViewById(R.id.bt_look);
    }
}
