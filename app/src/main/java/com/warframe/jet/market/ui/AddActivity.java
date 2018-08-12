package com.warframe.jet.market.ui;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.warframe.jet.market.R;
import com.warframe.jet.market.date.Lost;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadFileListener;

/**
 * Created by jet on 2018-08-11.
 */

public class AddActivity extends BaseActivity implements View.OnClickListener{
    private EditText goods_name;
    private EditText goods_desc;
    private EditText goods_price;
    private Button goods_add;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        //初始化，设置监听
        findView();
    }


    private void findView() {
        goods_name = (EditText) findViewById(R.id.goods_name);
        goods_price = (EditText) findViewById(R.id.goods_price);
        goods_desc = (EditText) findViewById(R.id.goods_desc);
        goods_add = (Button) findViewById(R.id.goods_add);
        goods_add.setOnClickListener(this);
    }
    //点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.goods_add:
                String name = goods_name.getText().toString().trim();
                String price = goods_price.getText().toString().trim();
                String desc = goods_desc.getText().toString().trim();
                //不得为空
                if (!TextUtils.isEmpty(name)&!TextUtils.isEmpty(name)&!TextUtils.isEmpty(name)){
                    Lost lost = new Lost();
                    lost.setTitle(name);
                    lost.setDescribe(desc);
                    lost.setPhone(price);

                    lost.save(new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {
                            if (e == null){
                                Toast.makeText(AddActivity.this,"发布成功！期待PY交易吧",Toast.LENGTH_SHORT).show();
                                finish();
                            }else {
                                Toast.makeText(AddActivity.this,"发布失败！失败原因："+e.toString(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else {
                    Toast.makeText(this,"内容不得为空",Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }
}

